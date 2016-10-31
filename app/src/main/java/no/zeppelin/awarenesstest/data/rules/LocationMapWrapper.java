package no.zeppelin.awarenesstest.data.rules;

import com.dellkan.robobinding.helpers.common.model.IHasDynamicLayout;
import com.dellkan.robobinding.helpers.modelgen.IncludeModel;
import com.dellkan.robobinding.helpers.modelgen.PresentationModel;

import java.io.Serializable;

import no.zeppelin.awarenesstest.data.MapLifecycleInjector;

@PresentationModel
public class LocationMapWrapper extends MapLifecycleInjector implements IHasDynamicLayout, Serializable {
	@IncludeModel
	LocationRule locationRule;

	public LocationMapWrapper(LocationRule locationRule) {
		this.locationRule = locationRule;
	}

	@Override
	public int getLayout() {
		return locationRule.getLayout();
	}
}
