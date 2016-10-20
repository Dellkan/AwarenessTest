package no.zeppelin.awarenesstest.data;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;

import com.dellkan.ContextFragment;
import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.IncludeModel;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.Serializable;

import no.zeppelin.awarenesstest.FenceReceiver;
import no.zeppelin.awarenesstest.Variables;

@PresentationModel
public class FenceRegister extends PresentationModelWrapper implements Serializable {
    private static FenceRegister mInstance;
    private static GoogleApiClient googleApiClient;
    private boolean toggle = false;

    @IncludeModel
    Console console = new Console();

    private FenceRegister() {

    }

    public static FenceRegister getInstance() {
        if (mInstance == null) {
            mInstance = new FenceRegister();
        }
        return mInstance;
    }

    public Console getConsole() {
        return console;
    }

    @PresentationMethod
    public String getButtonText() {
        return toggle ? "Stop tracking" : "Start tracking";
    }

    private void init() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(ContextFragment.getStaticContext()).addApi(Awareness.API).build();
            googleApiClient.connect();
        }
    }

    @PresentationMethod
    public void toggleTracking() {
        init();
        if (!toggle) {
            AwarenessFence fence = AwarenessFence.or(
                    DetectedActivityFence.starting(
                            DetectedActivityFence.IN_VEHICLE,
                            DetectedActivityFence.ON_BICYCLE,
                            DetectedActivityFence.ON_FOOT,
                            DetectedActivityFence.STILL,
                            DetectedActivityFence.UNKNOWN,
                            DetectedActivityFence.TILTING,
                            DetectedActivityFence.WALKING,
                            DetectedActivityFence.RUNNING
                    ),
                    DetectedActivityFence.stopping(
                            DetectedActivityFence.IN_VEHICLE,
                            DetectedActivityFence.ON_BICYCLE,
                            DetectedActivityFence.ON_FOOT,
                            DetectedActivityFence.STILL,
                            DetectedActivityFence.UNKNOWN,
                            DetectedActivityFence.TILTING,
                            DetectedActivityFence.WALKING,
                            DetectedActivityFence.RUNNING
                    ),
                    DetectedActivityFence.during(
                            DetectedActivityFence.IN_VEHICLE,
                            DetectedActivityFence.ON_BICYCLE,
                            DetectedActivityFence.ON_FOOT,
                            DetectedActivityFence.STILL,
                            DetectedActivityFence.UNKNOWN,
                            DetectedActivityFence.TILTING,
                            DetectedActivityFence.WALKING,
                            DetectedActivityFence.RUNNING
                    )
            );

            FenceUpdateRequest fenceUpdateRequest = new FenceUpdateRequest.Builder()
                    .addFence("vehicle",
                            DetectedActivityFence.starting(DetectedActivityFence.IN_VEHICLE)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.IN_VEHICLE)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("bicycle",
                            DetectedActivityFence.starting(DetectedActivityFence.ON_BICYCLE)
                            // , DetectedActivityFence.stopping(DetectedActivityFence.ON_BICYCLE)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("onfoot",
                            DetectedActivityFence.starting(DetectedActivityFence.ON_FOOT)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.ON_FOOT)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("still",
                            DetectedActivityFence.starting(DetectedActivityFence.STILL)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.STILL)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("unknown",
                            DetectedActivityFence.starting(DetectedActivityFence.UNKNOWN)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.UNKNOWN)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("tilting",
                            DetectedActivityFence.starting(DetectedActivityFence.TILTING)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.TILTING)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("walking",
                            DetectedActivityFence.starting(DetectedActivityFence.WALKING)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.WALKING)
                            , Variables.getConfiguredPendingIntent())
                    .addFence("running",
                            DetectedActivityFence.starting(DetectedActivityFence.RUNNING)
                            //, DetectedActivityFence.stopping(DetectedActivityFence.RUNNING)
                            , Variables.getConfiguredPendingIntent())
                    .build();

            Awareness.FenceApi
                    .updateFences(googleApiClient, fenceUpdateRequest)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            console.addEntry("Fence added - " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()));
                        }
                    });
        } else {
            Awareness.FenceApi
                    .updateFences(googleApiClient, new FenceUpdateRequest.Builder().removeFence("myTest").build())
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            console.addEntry("Fence removed - " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()));
                        }
                    });
            ;
        }
        toggle = !toggle;
        refresh("buttonText");
    }
}
