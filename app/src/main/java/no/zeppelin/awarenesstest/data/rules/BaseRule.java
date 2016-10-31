package no.zeppelin.awarenesstest.data.rules;

import android.support.annotation.Nullable;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.common.model.IHasDynamicLayout;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.google.android.gms.awareness.fence.AwarenessFence;

import java.io.Serializable;

import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.fragments.RuleConfigFragment;

@ItemPresentationModel
public abstract class BaseRule extends PresentationModelWrapper implements Rule, IHasDynamicLayout, Serializable {
    public enum RULE_TYPE {
        ACTIVITY, BEACON, HEADPHONE,
        LOCATION, PLACE, TIME, WEATHER
    }

    FenceEntry fence;
    public BaseRule(FenceEntry fence) {
        this.fence = fence;
    }

    public FenceEntry getFence() {
        return this.fence;
    }

    @Override
    public AwarenessFence createFence() {
        throw new UnsupportedOperationException(this.getClass() + " not supported as part of FenceRegister API");
    }

    private GroupItem parent;

    @Nullable
    @Override
    public GroupItem getParent() {
        return parent;
    }

    @Override
    public void setParent(GroupItem rule) {
        this.parent = rule;
    }

    @Override
    @PresentationMethod
    public String getRuleTitle() {
        return "BaseRule";
    }

    boolean saved = false;

    @PresentationMethod
    public void save() {
        if (parent != null) {
            if (!saved) {
                parent.addRule(this);
                saved = true;
            }
            dismissConfig();
            fence.refresh();
        }
    }

    private void dismissConfig() {
        RuleConfigFragment.findAndDismiss();
    }

    @PresentationMethod
    public void configure() {
        RuleConfigFragment.newInstance(this.getModel()).show();
    }

    @Override
    public PresentationModelWrapper getModel() {
        return this;
    }

    @PresentationMethod
    public void delete() {
        parent.removeRule(this);
    }
}
