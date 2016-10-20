package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.BeaconFence;
import com.google.android.gms.awareness.state.BeaconState;

import java.util.List;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class BeaconRule extends BaseRule {
    List<BeaconState.TypeFilter> typeFilter;

    public BeaconRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        //noinspection MissingPermission
        return BeaconFence.near(typeFilter);
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
