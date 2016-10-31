package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class WeatherRule extends BaseRule {
    public WeatherRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        return null;
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public String getRuleTitle() {
        return null;
    }
}
