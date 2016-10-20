package no.zeppelin.awarenesstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dellkan.ContextFragment;
import com.dellkan.fragmentbootstrap.FBActivity;

import no.zeppelin.awarenesstest.fragments.AwarenessFragment;

public class MainActivity extends FBActivity<AwarenessFragment> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().add(new ContextFragment(), "contextFragment").commit();
    }

    @Override
    public AwarenessFragment getMainFragment() {
        return new AwarenessFragment();
    }

    @Override
    public Class<AwarenessFragment> getMainFragmentClass() {
        return AwarenessFragment.class;
    }

    @Nullable
    @Override
    public Fragment getMenuFragment() {
        return null;
    }
}
