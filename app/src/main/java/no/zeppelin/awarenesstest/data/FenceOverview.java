package no.zeppelin.awarenesstest.data;

import android.support.annotation.NonNull;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.Variables;
import no.zeppelin.awarenesstest.fragments.FenceConfigureFragment;

@PresentationModel
public class FenceOverview extends PresentationModelWrapper {
    private FenceOverview() {

    }

    private static FenceOverview mInstance;
    public static FenceOverview getInstance() {
        if (mInstance == null) {
            mInstance = new FenceOverview();
        }

        return mInstance;
    }

    @ListItems
    ListContainer<FenceEntry> entries = new ListContainer<>();

    public void refreshFences() {
        FenceUpdateRequest.Builder fenceUpdateRequest = new FenceUpdateRequest.Builder();
        for (FenceEntry entry : entries.getItems()) {
            if (entry.toggle) {
                fenceUpdateRequest.addFence(entry.name, entry.getRootRule().createFence(), Variables.getConfiguredPendingIntent());
            } else {
                fenceUpdateRequest.removeFence(entry.name);
            }
        }
        Awareness.FenceApi
                .updateFences(Variables.getGoogleApiClient(), fenceUpdateRequest.build())
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        // console.addEntry("Fence added - " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()));
                    }
                });
    }

    @PresentationMethod
    public void createFence() {
        FenceEntry fenceEntry = new FenceEntry();
        entries.add(fenceEntry);
        fenceEntry.configure();
    }
}
