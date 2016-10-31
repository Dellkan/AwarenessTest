package no.zeppelin.awarenesstest.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.dellkan.robobinding.helpers.model.ListContainer;
import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.Get;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.ListItems;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import java.io.Serializable;

import no.zeppelin.awarenesstest.R;
import no.zeppelin.awarenesstest.data.actions.BaseAction;
import no.zeppelin.awarenesstest.data.actions.NotificationAction;
import no.zeppelin.awarenesstest.data.actions.SoundAction;
import no.zeppelin.awarenesstest.fragments.ActionConfigFragment;
import no.zeppelin.awarenesstest.fragments.SelectActionFragment;

@PresentationModel
public class SelectAction extends PresentationModelWrapper implements Serializable {
	FenceEntry mFenceEntry;
	@ListItems
	ListContainer<SelectableAction> availableItems = new ListContainer<>();

	public SelectAction(FenceEntry fenceEntry) {
		this.mFenceEntry = fenceEntry;
		availableItems.add(new SelectableAction(R.drawable.ic_action_notification, R.string.action_name_notification) {
			@Override
			void createItem() {
				new NotificationAction(mFenceEntry).configure();
			}
		});
		availableItems.add(new SelectableAction(R.drawable.ic_action_sound, R.string.action_name_sound) {
			@Override
			void createItem() {
				new SoundAction(mFenceEntry).configure();
			}
		});
	}

	@ItemPresentationModel
	static abstract class SelectableAction extends PresentationModelWrapper implements Serializable {
		@Get
		@DrawableRes int icon;
		@Get
		@StringRes int name;

		abstract void createItem();

		public SelectableAction(@DrawableRes int icon, @StringRes int title) {
			this.icon = icon;
			this.name = title;
		}

		@PresentationMethod
		public void onSelect() {
			SelectActionFragment.findAndDismiss();
			createItem();
		}
	}
}
