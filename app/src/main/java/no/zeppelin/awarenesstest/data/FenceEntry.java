package no.zeppelin.awarenesstest.data;

import android.annotation.SuppressLint;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.DependsOnStateOf;
import com.dellkan.robobinding.helpers.modelgen.Get;
import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.IncludeModel;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.dellkan.robobinding.helpers.modelgen.TwoStateGetSet;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceState;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.Variables;
import no.zeppelin.awarenesstest.data.actions.Action;
import no.zeppelin.awarenesstest.data.actions.BaseAction;
import no.zeppelin.awarenesstest.data.rules.BaseRule;
import no.zeppelin.awarenesstest.data.rules.GroupItem;
import no.zeppelin.awarenesstest.data.rules.Rule;
import no.zeppelin.awarenesstest.fragments.FenceConfigureFragment;
import no.zeppelin.awarenesstest.fragments.FenceCreateFragment;
import no.zeppelin.awarenesstest.fragments.SelectActionFragment;
import no.zeppelin.awarenesstest.fragments.SelectRuleFragment;

@ItemPresentationModel
@PresentationModel
public class FenceEntry extends PresentationModelWrapper implements Serializable {
    @TwoStateGetSet
    boolean toggle = false;

    @GetSet
    String name;

    public FenceEntry(String name) {
        this.name = name;
    }

    private Date lastTrigger;
    @PresentationMethod
    public String getLastTrigger() {
        if (lastTrigger == null) {
            return "Last trigger: N/A";
        }
        return String.format("Last trigger: %s", DateFormat.getDateTimeInstance().format(lastTrigger));
    }

    @SuppressLint("DefaultLocale")
    public void trigger(FenceState fenceState) {
        getConsole().addEntry(
                String.format(
                        "%s updated: %d (%s) -> %d (%s)",
                        fenceState.getFenceKey(),
                        fenceState.getPreviousState(),
                        Variables.convertState(fenceState.getPreviousState()),
                        fenceState.getCurrentState(),
                        Variables.convertState(fenceState.getCurrentState())
                )
        );
        if (fenceState.getCurrentState() == FenceState.TRUE) {
            lastTrigger = new Date();
            for (Action action : actions) {
                action.trigger();
            }
        }
    }

    private GroupItem rootRule = new GroupItem(this);

    @ListItems
    ListContainer<BaseRule> rules = new ListContainer<>();

    @ListItems
    ListContainer<BaseAction> actions = new ListContainer<>();

    @DependsOnStateOf({"rules", "actions"})
    public boolean isListsEmpty() {
        return rules.isEmpty() && actions.isEmpty();
    }

    @DependsOnStateOf({"rules", "actions"})
    public boolean isListsNotEmpty() {
        return !rules.isEmpty() || !actions.isEmpty();
    }

    public void addAction(BaseAction action) {
        actions.add(action);
        refresh();
    }

    public void removeAction(BaseAction action) {
        actions.remove(action);
        refresh();
    }

    @IncludeModel(prefix="console")
    Console console = new Console();

    public Console getConsole() {
        return console;
    }

    public GroupItem getRootRule() {
        return rootRule;
    }

    public void refreshRules() {
        rules.clear();
        rules.addAll(rootRule.getAllSubRules());
        refresh();
    }

    @PresentationMethod
    public void configure() {
        refreshRules();
        MainActivity.swapFragment(FenceConfigureFragment.newInstance(this));
    }

    @PresentationMethod
    public void toggle() {
        toggle = !toggle;

        FenceOverview.getInstance().refreshFences();
        console.addEntry("Fence toggled " + (toggle ? "on" : "off"));

        refresh();
    }

    @PresentationMethod
    public void save() {
        FenceOverview.getInstance().entries.add(this);

        FenceCreateFragment.findAndDismiss();

        configure();
    }

    @PresentationMethod
    public void delete() {
        toggle = false;
        FenceOverview.getInstance().refreshFences();
        FenceOverview.getInstance().entries.remove(this);
        FenceOverview.getInstance().refresh();
    }

    @PresentationMethod
    public void createActivityRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.ACTIVITY);
    }

    @PresentationMethod
    public void createRule() {
        SelectRuleFragment.newInstance(this).show();
    }

    @PresentationMethod
    public void createAction() {
        SelectActionFragment.newInstance(this).show();
    }
}
