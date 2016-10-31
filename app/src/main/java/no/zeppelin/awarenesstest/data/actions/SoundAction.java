package no.zeppelin.awarenesstest.data.actions;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.FenceEntry;

public class SoundAction extends BaseAction {
	public SoundAction(FenceEntry entry) {
		super(entry);
	}

	@Override
	public void trigger() {
		try {
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone sound = RingtoneManager.getRingtone(MainActivity.getInstance(), notification);
			sound.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getLayout() {
		return 0;
	}
}
