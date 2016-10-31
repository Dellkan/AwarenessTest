package no.zeppelin.awarenesstest.data;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dellkan.fragmentbootstrap.fragmentutils.LifecycleDelegateViewmodel;
import com.dellkan.robobinding.helpers.common.viewattributes.View.InitEvent;
import com.dellkan.robobinding.helpers.modelgen.PresentationMethod;
import com.google.android.gms.maps.MapView;

import java.lang.ref.WeakReference;

public abstract class MapLifecycleInjector extends LifecycleDelegateViewmodel {
	WeakReference<MapView> mapView = new WeakReference<>(null);

	@PresentationMethod
	public void init(InitEvent event) {
		if (event.getView() instanceof MapView) {
			mapView = new WeakReference<>((MapView) event.getView());
			mapView.get().onCreate(null);
		}
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		if (mapView.get() != null) {
			mapView.get().onCreate(savedInstanceState);
		}
	}

	@Override
	public void onStart() {
		if (mapView.get() != null) {
			mapView.get().onStart();
		}
	}

	@Override
	public void onResume() {
		if (mapView.get() != null) {
			mapView.get().onResume();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (mapView.get() != null) {
			mapView.get().onSaveInstanceState(outState);
		}
	}

	@Override
	public void onPause() {
		if (mapView.get() != null) {
			mapView.get().onPause();
		}
	}

	@Override
	public void onStop() {
		if (mapView.get() != null) {
			mapView.get().onStop();
		}
	}

	@Override
	public void onLowMemory() {
		if (mapView.get() != null) {
			mapView.get().onLowMemory();
		}
	}

	@Override
	public void onDestroy() {
		if (mapView.get() != null) {
			mapView.get().onDestroy();
		}
	}
}
