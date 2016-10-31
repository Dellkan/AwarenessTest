package no.zeppelin.awarenesstest.data;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.widget.Toast;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.Serializable;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.Variables;
import no.zeppelin.awarenesstest.fragments.FenceCreateFragment;

@PresentationModel
public class FenceOverview extends PresentationModelWrapper implements Serializable {
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
                        if (status.isCanceled()) {
                            Toast.makeText(MainActivity.getInstance(), String.format("Got canceled: %s", status.getStatusMessage()), Toast.LENGTH_SHORT).show();
                        } else if (status.isInterrupted()) {
                            Toast.makeText(MainActivity.getInstance(), String.format("Got interrupted: %s", status.getStatusMessage()), Toast.LENGTH_SHORT).show();
                        } else if (status.isSuccess()) {
                            Toast.makeText(MainActivity.getInstance(), String.format("Success: %s", status.getStatusMessage()), Toast.LENGTH_SHORT).show();
                        }
                        // console.addEntry("Fence added - " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()));
                    }
                });
        refresh();
    }

    public static FenceEntry getFenceEntry(String name) {
        for (FenceEntry fence : getInstance().entries) {
            if (fence.name.equals(name)) {
                return fence;
            }
        }
        return null;
    }

    @PresentationMethod
    public void createFence() {
        FenceCreateFragment.newInstance().show();
    }
}
