package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.actions.Action;
import no.zeppelin.awarenesstest.data.actions.BaseAction;

public class ActionConfigFragment extends ModelFragment {
    @Override
    public int getLayout() {
        return ((Action)getModel()).getLayout();
    }

    public static ActionConfigFragment newInstance(BaseAction rule) {
        ActionConfigFragment fragment = new ActionConfigFragment();

        fragment.setArguments(getProcessedArgs(rule));

        return fragment;
    }

    public void show() {
        this.show(MainActivity.getInstance().getSupportFragmentManager(), "ConfigureAction");
    }

    public static void findAndDismiss() {
        ActionConfigFragment fragment = (ActionConfigFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("ConfigureAction");
        if (fragment != null) {
            fragment.dismiss();
        }
    }
}
