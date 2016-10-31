package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.BeaconFence;
import com.google.android.gms.awareness.state.BeaconState;

import java.util.List;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class BeaconRule extends BaseRule {
    @GetSet String beaconNamespace = "";
    @GetSet String beaconType = "";

    public BeaconRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        //noinspection MissingPermission
        return BeaconFence.found(BeaconState.TypeFilter.with(beaconNamespace, beaconType));
    }

    @Override
    public int getLayout() {
        return R.layout.rule_config_beacon;
    }

    @Override
    public String getRuleTitle() {
        return String.format("I'm close to beacon %s:%s", beaconNamespace, beaconType);
    }
}
