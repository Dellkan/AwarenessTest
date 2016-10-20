package no.zeppelin.awarenesstest;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;

import com.dellkan.ContextFragment;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.awareness.fence.TimeFence;
import com.google.android.gms.common.api.GoogleApiClient;

public final class Variables {
    private static final String FENCE_RECEIVER_ACTION = "doStuff";
    private static GoogleApiClient googleApiClient;
    private static PendingIntent mPendingIntent;
    private Variables() {

    }

    public static GoogleApiClient getGoogleApiClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(ContextFragment.getStaticContext()).addApi(Awareness.API).build();
            googleApiClient.connect();
        }
        return googleApiClient;
    }

    public static PendingIntent getConfiguredPendingIntent() {
        if (mPendingIntent == null) {
            Intent intent = new Intent(FENCE_RECEIVER_ACTION);
            mPendingIntent = PendingIntent.getBroadcast(ContextFragment.getStaticContext(), 0, intent, 0);
            FenceReceiver receiver = new FenceReceiver();
            ContextFragment.getStaticContext().registerReceiver(receiver, new IntentFilter(FENCE_RECEIVER_ACTION));
        }
        return mPendingIntent;
    }

    public static String convertState(int index) {
        switch (index) {
            case FenceState.UNKNOWN:
                return "UNKNOWN";
            case FenceState.FALSE:
                return "FALSE";
            case FenceState.TRUE:
                return "TRUE";
        }

        return "OUT_OF_BOUNDS";
    }

    public static String convertActivity(int index) {
        switch (index) {
            case DetectedActivityFence.IN_VEHICLE:
                return "IN_VEHICLE";
            case DetectedActivityFence.ON_BICYCLE:
                return "ON_BICYCLE";
            case DetectedActivityFence.ON_FOOT:
                return "ON_FOOT";
            case DetectedActivityFence.STILL:
                return "STILL";
            case DetectedActivityFence.UNKNOWN:
                return "UNKNOWN";
            case DetectedActivityFence.TILTING:
                return "TILTING";
            case DetectedActivityFence.WALKING:
                return "WALKING";
            case DetectedActivityFence.RUNNING:
                return "RUNNING";
        }
        return "OUT_OF_BOUNDS";
    }
}
