package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import no.zeppelin.awarenesstest.data.FenceEntry;

@PresentationModel
public class PlaceRule extends BaseRule {
    public PlaceRule(FenceEntry fence) {
        super(fence);
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
