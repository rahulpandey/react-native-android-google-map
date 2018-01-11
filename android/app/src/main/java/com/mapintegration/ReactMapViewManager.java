package com.mapintegration;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;

public class ReactMapViewManager extends SimpleViewManager<GoogleMapView>
    implements LifecycleEventListener {
  private static final String REACT_CLASS = "MapView";

  private GoogleMapView googleMapView;
  private GoogleMapOptions googleMapOptions;
  private MapListener listener;

  ReactMapViewManager(GoogleMapOptions googleMapOptions, MapListener listener) {
    this.googleMapOptions = googleMapOptions;
    this.listener = listener;
  }

  @Override public String getName() {
    return REACT_CLASS;
  }

  @Override protected GoogleMapView createViewInstance(ThemedReactContext reactContext) {
    reactContext.addLifecycleEventListener(this);
    googleMapView = new GoogleMapView(reactContext, googleMapOptions, listener);
    return googleMapView;
  }

  public GoogleMap getGoogleMap() {
    return googleMapView.getGoogleMap();
  }

  @Override public void onHostResume() {
    googleMapView.onResume();
  }

  @Override public void onHostPause() {
    googleMapView.onPause();
  }

  @Override public void onHostDestroy() {
    googleMapView.onDestroy();
  }
}
