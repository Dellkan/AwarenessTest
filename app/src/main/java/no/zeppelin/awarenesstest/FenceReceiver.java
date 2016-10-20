package no.zeppelin.awarenesstest;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import no.zeppelin.awarenesstest.data.FenceRegister;

public class FenceReceiver extends BroadcastReceiver {
    @SuppressLint("DefaultLocale")
    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);

        ActivityRecognitionResult recognitionResult = ActivityRecognitionResult.extractResult(intent);

        String extended = "";
        if (recognitionResult != null) {
            for (DetectedActivity activity : recognitionResult.getProbableActivities()) {
                if (extended.length() > 0) {
                    extended += "\n";
                }
                extended += String.format("%s(%d) -> Confidence: %d",
                        Variables.convertActivity(activity.getType()),
                        activity.getType(),
                        activity.getConfidence()
                );
            }
        }

        FenceRegister.getInstance().getConsole().addEntry(
            String.format(
                "%s updated: %d (%s) -> %d (%s)",
                fenceState.getFenceKey(),
                fenceState.getCurrentState(),
                Variables.convertState(fenceState.getCurrentState()),
                fenceState.getPreviousState(),
                Variables.convertState(fenceState.getPreviousState())
            ),
            extended
        );
    }
}
