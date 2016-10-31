package no.zeppelin.awarenesstest.data.actions;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.dellkan.robobinding.helpers.modelgen.GetSet;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import java.io.Serializable;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.FenceEntry;

import static android.content.Context.NOTIFICATION_SERVICE;

@PresentationModel
public class NotificationAction extends BaseAction implements Serializable {
	@GetSet
	boolean highPriority = false;

	@GetSet
	String notificationText;

	public NotificationAction(FenceEntry entry) {
		super(entry);
	}

	@Override
	public void trigger() {
		Context context = MainActivity.getInstance();
		NotificationCompat.Builder builder =
				new NotificationCompat.Builder(context)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle(context.getString(R.string.app_name))
						.setContentText(notificationText)
						.setAutoCancel(true);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			builder.setDefaults(highPriority ? Notification.PRIORITY_HIGH : Notification.PRIORITY_LOW);
		}

		// Sets an ID for the notification
		int mNotificationId = 001;
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr = (NotificationManager) MainActivity.getInstance().getSystemService(NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, builder.build());

	}

	@Override
	public int getLayout() {
		return R.layout.action_notification;
	}

	@Override
	public String getActionTitle() {
		return String.format("Send notification \"%s\"", notificationText);
	}
}
