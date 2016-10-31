package no.zeppelin.awarenesstest;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.data.FenceOverview;

public class FenceReceiver extends BroadcastReceiver {
    @SuppressLint("DefaultLocale")
    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);

        FenceEntry entry = FenceOverview.getFenceEntry(fenceState.getFenceKey());
        if (entry != null) {
            entry.trigger(fenceState);
        }
    }
}
