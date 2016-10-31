package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.EnumListItem;
import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class ActivityRule extends BaseRule {
    public ActivityRule(FenceEntry fence) {
        super(fence);
    }

    public enum ACTIVITY_TYPES {
        IN_VEHICLE(DetectedActivityFence.IN_VEHICLE, R.string.activity_vehicle),
        ON_BICYCLE(DetectedActivityFence.ON_BICYCLE, R.string.activity_bicycle),
        ON_FOOT(DetectedActivityFence.ON_FOOT, R.string.activity_on_foot),
        STILL(DetectedActivityFence.STILL, R.string.activity_still),
        // UNKNOWN(DetectedActivityFence.UNKNOWN, R.string.activity_unknown),
        TILTING(DetectedActivityFence.TILTING, R.string.activity_tilting),
        WALKING(DetectedActivityFence.WALKING, R.string.activity_walking),
        RUNNING(DetectedActivityFence.RUNNING, R.string.activity_running);

        private int index;
        private int human_readable;
        public int getIndex() {
            return index;
        }
        public int getHumanReadableString() {
            return human_readable;
        }
        ACTIVITY_TYPES(int index, int human_readable) {
            this.index = index;
            this.human_readable = human_readable;
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
        return "I'm currently " + MainActivity.getInstance().getString(activityRules.getSelectedItem().getEnum().getHumanReadableString());
    }
}
