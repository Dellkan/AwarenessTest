package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;

import java.util.ArrayList;
import java.util.List;

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

    public void addRule(BaseRule rule) {
        this.rules.add(rule);
        refresh("allChildrenRules");
        this.getFence().refreshRules();
    }

    public void removeRule(BaseRule rule) {
        this.rules.remove(rule);
        refresh("allChildrenRules");
        this.getFence().refreshRules();
    }

    @Override
    public String getRuleTitle() {
        return types.getSelectedItem().getEnum().name();
    }

    @PresentationMethod
    public void createChild(RULE_TYPE type) {
        BaseRule rule = null;

        switch (type) {
            case ACTIVITY:
                rule = new ActivityRule(this.getFence());
                break;
            case BEACON:
                rule = new BeaconRule(this.getFence());
                break;
            case HEADPHONE:
                rule = new HeadphoneRule(this.getFence());
                break;
            case LOCATION:
                rule = new LocationRule(this.getFence());
                break;
            case PLACE:
                rule = new PlaceRule(this.getFence());
                break;
            case TIME:
                rule = new TimeRule(this.getFence());
                break;
            case WEATHER:
                rule = new WeatherRule(this.getFence());
                break;
        }

        if (rule != null) {
            rule.setParent(this);
            rule.configure();
        }
    }
}
