package no.zeppelin.awarenesstest.data.rules;

import android.support.annotation.Nullable;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.google.android.gms.awareness.fence.AwarenessFence;

public interface Rule {
    AwarenessFence createFence();
    PresentationModelWrapper getModel();
    @Nullable GroupItem getParent();
    void setParent(GroupItem rule);
    String getRuleTitle();
}
