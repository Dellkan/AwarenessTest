package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.SnapshotIndex;

public class SnapshotFragment extends ModelFragment {
    @Override
    public int getLayout() {
        return R.layout.fragment_console;
    }

    @Override
    public PresentationModelWrapper getModel() {
        return SnapshotIndex.getInstance();
    }
}
