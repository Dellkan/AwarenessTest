package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.state.HeadphoneState;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class HeadphoneRule extends BaseRule {
    public HeadphoneRule(FenceEntry fence) {
        super(fence);
    }

    public enum HEADPHONE_TYPES {
        PLUGGED_IN(HeadphoneState.PLUGGED_IN),
        PLUGGED_OUT(HeadphoneState.UNPLUGGED);

        private int index;
        public int getIndex() {
            return index;
        }

        HEADPHONE_TYPES(int index) {
            this.index = index;
        }
    }

    private HEADPHONE_TYPES fenceType = HEADPHONE_TYPES.PLUGGED_IN;

    @Override
    public AwarenessFence createFence() {
        return HeadphoneFence.during(fenceType.getIndex());
    }

    @Override
    public int getLayout() {
        return 0;
    }
}
