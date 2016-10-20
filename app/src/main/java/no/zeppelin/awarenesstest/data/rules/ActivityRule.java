package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.EnumListItem;
import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class ActivityRule extends BaseRule {
    public ActivityRule(FenceEntry fence) {
        super(fence);
    }

    public enum ACTIVITY_TYPES {
        IN_VEHICLE(DetectedActivityFence.IN_VEHICLE),
        ON_BICYCLE(DetectedActivityFence.ON_BICYCLE),
        ON_FOOT(DetectedActivityFence.ON_FOOT),
        STILL(DetectedActivityFence.STILL),
        UNKNOWN(DetectedActivityFence.UNKNOWN),
        TILTING(DetectedActivityFence.TILTING),
        WALKING(DetectedActivityFence.WALKING),
        RUNNING(DetectedActivityFence.RUNNING);

        private int index;
        public int getIndex() {
            return index;
        }

        ACTIVITY_TYPES(int index) {
            this.index = index;
        }
    }

    @ListItems
    ListContainer<EnumListItem<ACTIVITY_TYPES>> activityRules = new ListContainer<>(EnumListItem.getList(ACTIVITY_TYPES.values()));

    @Override
    public AwarenessFence createFence() {
        return DetectedActivityFence.during(activityRules.getSelectedItem().getEnum().getIndex());
    }

    @Override
    public int getLayout() {
        return R.layout.rule_config_activity;
    }

    @Override
    public String getRuleTitle() {
        return "ActivityRule: " + activityRules.getSelectedItem().name();
    }
}
