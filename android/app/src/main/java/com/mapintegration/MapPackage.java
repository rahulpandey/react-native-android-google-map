package com.mapintegration;

import android.view.View;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.google.android.gms.maps.GoogleMapOptions;
import java.util.Collections;
import java.util.List;

public class MapPackage implements ReactPackage, MapListener {

  private final ReactMapViewManager reactMapViewManager;

  MapPackage(GoogleMapOptions googleMapOptions) {
    reactMapViewManager = new ReactMapViewManager(googleMapOptions, this);
  }

  @Override public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.singletonList(reactMapViewManager);
  }

  @Override public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    MapModule mapModule = new MapModule(reactContext, reactMapViewManager);
    return Collections.singletonList(mapModule);
  }

  @Override public void onMapReady(View googleMapView) {
    WritableMap event = Arguments.createMap();
    ReactContext reactContext = (ReactContext) googleMapView.getContext();
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit("onMapReady", event);
  }
}
