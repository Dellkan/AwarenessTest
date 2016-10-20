package no.zeppelin.awarenesstest.data;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.IncludeModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.DetectedActivityResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.DetectedActivity;

import java.io.Serializable;

import no.zeppelin.awarenesstest.Variables;

@PresentationModel
public class SnapshotIndex extends PresentationModelWrapper implements Serializable {
    private static SnapshotIndex mInstance;

    @IncludeModel
    Console console = new Console();

    private SnapshotIndex() {

    }

    public static SnapshotIndex getInstance() {
        if (mInstance == null) {
            mInstance = new SnapshotIndex();
        }
        return mInstance;
    }

    public Console getConsole() {
        return console;
    }

    @PresentationMethod
    public String getButtonText() {
        return "Fetch new snapshot";
    }

    @PresentationMethod
    void toggleTracking() {
        Awareness.SnapshotApi.getDetectedActivity(Variables.getGoogleApiClient()).setResultCallback(new ResultCallback<DetectedActivityResult>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResult(@NonNull DetectedActivityResult detectedActivityResult) {
                String extended = "";
                for (DetectedActivity activity : detectedActivityResult.getActivityRecognitionResult().getProbableActivities()) {
                    if (extended.length() > 0) {
                        extended += "\n";
                    }
                    extended += String.format("%s(%d) -> Confidence: %d",
                            Variables.convertActivity(activity.getType()),
                            activity.getType(),
                            activity.getConfidence()
                    );
                }


                DetectedActivity mostProbableActivity = detectedActivityResult.getActivityRecognitionResult().getMostProbableActivity();
                console.addEntry(mostProbableActivity.toString(), extended);
            }
        });
    }
}
