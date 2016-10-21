package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

public class FenceConfigureFragment extends ModelFragment {
    @Override
    public int getLayout() {
        return R.layout.fragment_fence_configure_simple;
    }

    public static FenceConfigureFragment newInstance(FenceEntry entry) {
        FenceConfigureFragment fragment = new FenceConfigureFragment();

        fragment.setArguments(getProcessedArgs(entry));

        return fragment;
    }
}
