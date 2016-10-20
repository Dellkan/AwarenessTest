package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;

import java.util.ArrayList;
import java.util.List;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.EnumListItem;
import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.fragments.RuleConfigFragment;

@PresentationModel
public class GroupItem extends BaseRule {
    public GroupItem(FenceEntry fence) {
        super(fence);
    }

    enum Type {
        AND, OR
    }

    Type type = Type.AND;
    List<BaseRule> rules = new ArrayList<>();

    @ListItems
    ListContainer<EnumListItem<Type>> types = new ListContainer<>(EnumListItem.getList(Type.values()));

    @ListItems
    ListContainer<BaseRule> allChildrenRules = new ListContainer<>(getAllSubRules());

    public List<BaseRule> getAllSubRules() {
        List<BaseRule> resolvedRules = new ArrayList<>();

        for (BaseRule rule : rules) {
            if (rule instanceof GroupItem) {
                resolvedRules.addAll(((GroupItem) rule).getAllSubRules());
            } else {
                resolvedRules.add(rule);
            }
        }

        return resolvedRules;
    }

    @Override
    public AwarenessFence createFence() {
        List<AwarenessFence> fences = new ArrayList<>();
        for (Rule rule : rules) {
            fences.add(rule.createFence());
        }
        switch (type) {
            case AND:
                return AwarenessFence.and(fences);
            case OR:
                return AwarenessFence.or(fences);
        }
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.rule_config_group;
    }

    @PresentationMethod
    public void createChild() {
        BaseRule rule = new ActivityRule(getFence());
        rule.setParent(this);
        RuleConfigFragment.newInstance(rule).show(MainActivity.getInstance().getSupportFragmentManager(), "Test");
    }

    public void addRule(BaseRule rule) {
        this.rules.add(rule);
        refresh("allChildrenRules");
        this.getFence().refreshRules();
    }

    @Override
    public String getRuleTitle() {
        return types.getSelectedItem().getEnum().name();
    }
}
