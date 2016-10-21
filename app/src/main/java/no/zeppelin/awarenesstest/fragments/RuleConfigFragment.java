package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelDialogFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.rules.Rule;

public class RuleConfigFragment extends ModelDialogFragment {
    @Override
    public int getLayout() {
        return ((Rule)getModel()).getLayout();
    }

    public static RuleConfigFragment newInstance(PresentationModelWrapper rule) {
        if (!(rule instanceof Rule)) {
            throw new UnsupportedOperationException("RuleConfigFragment requires the presentationModelWrapper to implement Rule interface.");
        }

        RuleConfigFragment fragment = new RuleConfigFragment();

        fragment.setArguments(getProcessedArgs(rule));

        return fragment;
    }

    public void show() {
        this.show(MainActivity.getInstance().getSupportFragmentManager(), "ConfigureRule");
    }

    public static void findAndDismiss() {
        RuleConfigFragment fragment = (RuleConfigFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("ConfigureRule");
        if (fragment != null) {
            fragment.dismiss();
        }
    }
}
