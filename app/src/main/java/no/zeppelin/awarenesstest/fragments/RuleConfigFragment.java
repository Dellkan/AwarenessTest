package no.zeppelin.awarenesstest.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.common.model.IHasDynamicLayout;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.rules.BaseRule;
import no.zeppelin.awarenesstest.data.rules.Rule;

public class RuleConfigFragment extends ModelFragment {
    @Override
    public int getLayout() {
        return ((IHasDynamicLayout)getModel()).getLayout();
    }

    public static RuleConfigFragment newInstance(BaseRule rule) {
        return newInstance((PresentationModelWrapper) rule);
    }

    public static RuleConfigFragment newInstance(PresentationModelWrapper rule) {
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

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
