package no.zeppelin.awarenesstest.adapters;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dellkan.ContextFragment;

import java.util.ArrayList;
import java.util.List;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceOverview;
import no.zeppelin.awarenesstest.fragments.FenceFragment;
import no.zeppelin.awarenesstest.fragments.FenceOverviewFragment;
import no.zeppelin.awarenesstest.fragments.SnapshotFragment;

public class AwarenessFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<TabEntry> tabs = new ArrayList<>();

    public AwarenessFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs.add(new TabEntry(FenceFragment.class, R.string.tab_fence));
        tabs.add(new TabEntry(SnapshotFragment.class, R.string.tab_snapshot));
        tabs.add(new TabEntry(FenceOverviewFragment.class, R.string.tab_fence_overview));
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MainActivity.getInstance().getString(tabs.get(position).title);
    }

    public static class TabEntry {
        private @StringRes int title;
        private Class<? extends Fragment> fragmentClass;

        public TabEntry(Class<? extends Fragment> fragmentClass, @StringRes int title) {
            this.fragmentClass = fragmentClass;
            this.title = title;
        }

        public Fragment getFragment() {
            try {
                return this.fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
