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
import no.zeppelin.awarenesstest.data.rules.BaseRule;
import no.zeppelin.awarenesstest.fragments.SelectRuleFragment;

@PresentationModel
public class SelectRule extends PresentationModelWrapper implements Serializable {
	FenceEntry mFenceEntry;
	@ListItems
	ListContainer<SelectableRule> availableItems = new ListContainer<>();

	public SelectRule(FenceEntry fenceEntry) {
		this.mFenceEntry = fenceEntry;
		availableItems.add(new SelectableRule(R.drawable.ic_action_activity, R.string.rule_name_activity) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.ACTIVITY);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_beacon, R.string.rule_name_beacon) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.BEACON);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_headphones, R.string.rule_name_headphone) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.HEADPHONE);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_location, R.string.rule_name_location) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.LOCATION);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_place, R.string.rule_name_place) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.PLACE);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_time, R.string.rule_name_time) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.TIME);
			}
		});
		availableItems.add(new SelectableRule(R.drawable.ic_action_weather, R.string.rule_name_weather) {
			@Override
			void createItem() {
				mFenceEntry.getRootRule().createChild(BaseRule.RULE_TYPE.WEATHER);
			}
		});
	}

	@ItemPresentationModel
	static abstract class SelectableRule extends PresentationModelWrapper implements Serializable {
		@Get
		@DrawableRes int icon;
		@Get
		@StringRes int name;

		abstract void createItem();

		public SelectableRule(@DrawableRes int icon, @StringRes int title) {
			this.icon = icon;
			this.name = title;
		}

		@PresentationMethod
		public void onSelect() {
			SelectRuleFragment.findAndDismiss();
			createItem();
		}
	}
}
