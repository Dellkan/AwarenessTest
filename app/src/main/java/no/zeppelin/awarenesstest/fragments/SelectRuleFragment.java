package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.data.SelectRule;

public class SelectRuleFragment extends ModelFragment {
	@Override
	public int getLayout() {
		return R.layout.fragment_select_item;
	}

	public static SelectRuleFragment newInstance(FenceEntry fenceEntry) {
		SelectRuleFragment fragment = new SelectRuleFragment();

		fragment.setArguments(getProcessedArgs(new SelectRule(fenceEntry)));

		return fragment;
	}

	public void show() {
		show(MainActivity.getInstance().getSupportFragmentManager(), "SelectRule");
	}

	public static void findAndDismiss() {
		SelectRuleFragment fragment = (SelectRuleFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("SelectRule");
		if (fragment != null) {
			fragment.dismiss();
		}
	}
}
