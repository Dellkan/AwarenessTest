package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.state.HeadphoneState;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.EnumListItem;
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

    @ListItems
    ListContainer<EnumListItem<HEADPHONE_TYPES>> headphoneTrigger = new ListContainer<>(EnumListItem.getList(HEADPHONE_TYPES.values()));

    @Override
    public AwarenessFence createFence() {
        return HeadphoneFence.during(headphoneTrigger.getSelectedItem().getEnum().getIndex());
    }

    @Override
    public int getLayout() {
        return R.layout.rule_config_headphone;
    }

    @Override
    public String getRuleTitle() {
        return String.format("the headphone is %s", headphoneTrigger.getSelectedItem().name());
    }
}
