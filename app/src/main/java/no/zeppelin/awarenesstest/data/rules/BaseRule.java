package no.zeppelin.awarenesstest.data.rules;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.google.android.gms.awareness.fence.AwarenessFence;

import java.io.Serializable;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.FenceEntry;

@ItemPresentationModel
public abstract class BaseRule extends PresentationModelWrapper implements Rule, Serializable {
    private boolean not;

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

    @PresentationMethod
    public void createChild() {

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
        DialogFragment fragment = (DialogFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("Test");
        fragment.getDialog().dismiss();
    }
}
