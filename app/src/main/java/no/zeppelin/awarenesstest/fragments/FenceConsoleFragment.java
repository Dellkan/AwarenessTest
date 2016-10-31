package no.zeppelin.awarenesstest.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dellkan.fragmentbootstrap.fragmentutils.ModelFragment;
import com.dellkan.robobinding.helpers.common.LayoutBuilder;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.Variables;
import no.zeppelin.awarenesstest.data.Console;
import no.zeppelin.awarenesstest.data.FenceEntry;

public class FenceConsoleFragment extends ModelFragment implements DialogInterface.OnShowListener {
	@Override
	public int getLayout() {
		return R.layout.fragment_console;
	}

	public static FenceConsoleFragment newInstance(FenceEntry entry) {
		FenceConsoleFragment fragment = new FenceConsoleFragment();

		fragment.setArguments(getProcessedArgs(entry.getConsole()));

		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return null;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new BottomSheetDialog(getContext(), getTheme());
	}

	@Override
	public void setupDialog(Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		dialog.setContentView(LayoutBuilder.getViewBinder(getContext()).inflateAndBind(R.layout.fragment_console, getModel().getPresentationModel()));

		dialog.setCanceledOnTouchOutside(true);
		dialog.setOnShowListener(this);

		BottomSheetBehavior.from((FrameLayout) dialog.findViewById(android.support.design.R.id.design_bottom_sheet)).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				if (newState == BottomSheetBehavior.STATE_HIDDEN) {
					dismiss();
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {

			}
		});
	}

	public void show() {
		show(MainActivity.getInstance().getSupportFragmentManager(), "FenceConsole");
	}

	public static void findAndDismiss() {
		FenceConsoleFragment fragment = (FenceConsoleFragment) MainActivity.getInstance().getSupportFragmentManager().findFragmentByTag("FenceConsole");
		if (fragment != null) {
			fragment.dismiss();
		}
	}

	@Override
	public void onShow(DialogInterface dialog) {
		BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
		BottomSheetBehavior.from((FrameLayout) bottomSheetDialog.findViewById(android.support.design.R.id.design_bottom_sheet)).setState(BottomSheetBehavior.STATE_EXPANDED);
	}
}
