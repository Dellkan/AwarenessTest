package no.zeppelin.awarenesstest.data.rules;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.google.android.gms.awareness.fence.AwarenessFence;

public interface Rule {
    AwarenessFence createFence();
    @LayoutRes int getLayout();
    @Nullable GroupItem getParent();
    void setParent(GroupItem rule);
    String getRuleTitle();
}
