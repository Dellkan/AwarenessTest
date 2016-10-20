package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.dellkan.robobinding.helpers.modelgen.Stringify;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.LocationFence;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class LocationRule extends BaseRule {
    @GetSet
    @Stringify
    double latitude;

    @GetSet
    @Stringify
    double longitude;

    @GetSet
    @Stringify
    double radius;

    @GetSet
    @Stringify
    long dwellTime;

    public LocationRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        //noinspection MissingPermission
        return LocationFence.in(latitude, longitude, radius, dwellTime);
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
