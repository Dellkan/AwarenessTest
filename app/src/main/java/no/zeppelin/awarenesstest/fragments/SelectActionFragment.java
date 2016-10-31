package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.data.SelectAction;

public class SelectActionFragment extends ModelFragment {
	@Override
	public int getLayout() {
		return R.layout.fragment_select_item;
	}

	public static SelectActionFragment newInstance(FenceEntry fenceEntry) {
		SelectActionFragment fragment = new SelectActionFragment();

		fragment.setArguments(getProcessedArgs(new SelectAction(fenceEntry)));

		return fragment;
	}

	public void show() {
		show(MainActivity.getInstance().getSupportFragmentManager(), "SelectAction");
	}

	public static void findAndDismiss() {
		SelectActionFragment fragment = (SelectActionFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("SelectAction");
		if (fragment != null) {
			fragment.dismiss();
		}
	}
}
