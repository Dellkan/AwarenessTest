package no.zeppelin.awarenesstest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.github.clans.fab.FloatingActionMenu;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

public class FenceConfigureFragment extends ModelFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fence_entry, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showConsole) {
            FenceConsoleFragment.newInstance((FenceEntry) getModel()).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (view != null) {
            FloatingActionMenu menu = ((FloatingActionMenu) view.findViewById(R.id.fabMenu));
            menu.setClosedOnTouchOutside(true);

            if (getModel().isListsEmpty()) {
                menu.open(true);
            }
        }

        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_fence_configure_simple;
    }

    public static FenceConfigureFragment newInstance(FenceEntry entry) {
        FenceConfigureFragment fragment = new FenceConfigureFragment();

        fragment.setArguments(getProcessedArgs(entry));

        return fragment;
    }

    @Override
    public FenceEntry getModel() {
        return (FenceEntry) super.getModel();
    }
}
