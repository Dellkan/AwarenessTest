package no.zeppelin.awarenesstest.data;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.Get;
import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.IncludeModel;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.dellkan.robobinding.helpers.modelgen.TwoStateGetSet;
import com.google.android.gms.awareness.fence.AwarenessFence;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.rules.BaseRule;
import no.zeppelin.awarenesstest.data.rules.GroupItem;
import no.zeppelin.awarenesstest.data.rules.Rule;
import no.zeppelin.awarenesstest.fragments.FenceConfigureFragment;

@ItemPresentationModel
@PresentationModel
public class FenceEntry extends PresentationModelWrapper implements Serializable {
    @TwoStateGetSet
    boolean toggle = false;

    @GetSet
    String name;

    private Date lastTrigger;
    @PresentationMethod
    public String getLastTrigger() {
        return DateFormat.getDateTimeInstance().format(lastTrigger);
    }

    private GroupItem rootRule = new GroupItem(this);

    @ListItems
    ListContainer<BaseRule> rules = new ListContainer<>();

    @IncludeModel
    Console console = new Console();

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

        refresh();
    }

    @PresentationMethod
    public void createActivityRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.ACTIVITY);
    }

    @PresentationMethod
    public void createBeaconRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.BEACON);
    }

    @PresentationMethod
    public void createHeadphoneRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.HEADPHONE);
    }

    @PresentationMethod
    public void createLocationRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.LOCATION);
    }

    @PresentationMethod
    public void createPlaceRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.PLACE);
    }

    @PresentationMethod
    public void createTimeRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.TIME);
    }

    @PresentationMethod
    public void createWeatherRule() {
        rootRule.createChild(BaseRule.RULE_TYPE.WEATHER);
    }
}
