package no.zeppelin.awarenesstest.data.actions;

import com.dellkan.robobinding.helpers.model.PresentationModelWrapper;
import com.dellkan.robobinding.helpers.modelgen.ItemPresentationModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;

import java.io.Serializable;

import no.zeppelin.awarenesstest.MainActivity;
import no.zeppelin.awarenesstest.data.FenceEntry;
import no.zeppelin.awarenesstest.fragments.ActionConfigFragment;
import no.zeppelin.awarenesstest.fragments.RuleConfigFragment;

@ItemPresentationModel
public abstract class BaseAction extends PresentationModelWrapper implements Action, Serializable {
	private FenceEntry entry;
	private boolean saved = false;

	public BaseAction(FenceEntry entry) {
		this.entry = entry;
	}

	@PresentationMethod
	public void configure() {
		ActionConfigFragment.newInstance(this).show();
	}

	@Override
	@PresentationMethod
	public void save() {
		if (!saved) {
			entry.addAction(this);
			saved = true;
		}
	}

	@Override
	@PresentationMethod
	public void delete() {
		entry.removeAction(this);
	}

	@Override
	@PresentationMethod
	public String getActionTitle() {
		return this.toString();
	}
}
