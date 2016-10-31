package no.zeppelin.awarenesstest.fragments;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

public class FenceCreateFragment extends ModelFragment {
	@Override
	public int getLayout() {
		return R.layout.fence_create;
	}

	@Override
	public PresentationModelWrapper getModel() {
		return new FenceEntry("New fence");

	}

	public static FenceCreateFragment newInstance() {
		FenceCreateFragment fragment = new FenceCreateFragment();

		return fragment;
	}

	public void show() {
		show(MainActivity.getInstance().getSupportFragmentManager(), "CreateFence");
	}

	public static void findAndDismiss() {
		FenceCreateFragment fragment = (FenceCreateFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("CreateFence");
		if (fragment != null) {
			fragment.dismiss();
		}
	}
}
