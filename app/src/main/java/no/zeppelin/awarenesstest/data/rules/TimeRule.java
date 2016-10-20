package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.TimeFence;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class TimeRule extends BaseRule {
    @GetSet
    Date start;

    @GetSet
    Date end;

    public TimeRule(FenceEntry fence) {
        super(fence);
    }

    @Override
    public AwarenessFence createFence() {
        return TimeFence.inDailyInterval(TimeZone.getDefault(), start.getTime(), end.getTime());
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
