package no.zeppelin.awarenesstest.data.actions;

import android.support.annotation.LayoutRes;

public interface Action {
	void trigger();
	@LayoutRes
	int getLayout();
	void save();
	void delete();
	String getActionTitle();
}
