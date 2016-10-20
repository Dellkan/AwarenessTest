package no.zeppelin.awarenesstest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.fragmentbootstrap.fragmentutils.OverlayFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.adapters.AwarenessFragmentPagerAdapter;
import no.zeppelin.awarenesstest.data.AwarenessIndex;

public class AwarenessFragment extends OverlayFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Get pager, and set up limit
        ViewPager tabPager = (ViewPager) view.findViewById(R.id.pager);
        tabPager.setOffscreenPageLimit(1);

        // Get adapter, and initialize it
        AwarenessFragmentPagerAdapter tabAdapter = new AwarenessFragmentPagerAdapter(getChildFragmentManager());
        tabPager.setAdapter(tabAdapter);

        // Attach tabs to pager
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(tabPager);

        return view;
    }
}
