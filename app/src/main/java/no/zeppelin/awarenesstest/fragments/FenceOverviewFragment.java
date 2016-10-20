package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceOverview;

public class FenceOverviewFragment extends ModelFragment {
    @Override
    public int getLayout() {
        return R.layout.fragment_fence_overview;
    }

    @Override
    public PresentationModelWrapper getModel() {
        return FenceOverview.getInstance();
    }
}
