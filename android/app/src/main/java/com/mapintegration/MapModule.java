package com.mapintegration;

import android.os.Looper;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class MapModule extends ReactContextBaseJavaModule {

  private android.os.Handler mHandler;

  MapModule(ReactApplicationContext reactContext, ReactMapViewManager mapViewManager) {
    super(reactContext);
    this.mHandler = new MarkerMapHandler(Looper.getMainLooper(), mapViewManager);
  }

  @ReactMethod public void addMarker(ReadableMap map) {
    mHandler.obtainMessage(MarkerMapHandler.ADD_MARKER, map).sendToTarget();
  }

  @ReactMethod public void removeMarker(ReadableMap map) {
    mHandler.obtainMessage(MarkerMapHandler.REMOVE_MARKER, map).sendToTarget();
  }

  @Override public String getName() {
    return "MapEvent";
  }
}
