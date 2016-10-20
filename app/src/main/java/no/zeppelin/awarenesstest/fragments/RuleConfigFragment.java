package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelDialogFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

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
}
