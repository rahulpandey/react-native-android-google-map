package com.mapintegration;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MarkerMapHandler extends Handler {
  public static final int ADD_MARKER = 1;
  public static final int REMOVE_MARKER = 2;
  private ReactMapViewManager mViewManager;
  private MarkerManager mMarkerManager;

  MarkerMapHandler(Looper mainLooper, ReactMapViewManager mViewManager) {
    super(mainLooper);
    this.mViewManager = mViewManager;
    this.mMarkerManager = new MarkerManager();
  }

  @Override public void handleMessage(Message msg) {
    if (Objects.deepEquals(msg.what, ADD_MARKER)) {
      onAddMarker((ReadableMap) msg.obj);
    }
    if (Objects.deepEquals(msg.what, REMOVE_MARKER)) {

      removeMarker((ReadableMap) msg.obj);
    }
  }

  private void removeMarker(ReadableMap map) {
    double latitude = map.getDouble("latitude");
    double longitude = map.getDouble("longitude");
    LatLng latLng = new LatLng(latitude, longitude);
    Objects.requireNonNull(mMarkerManager).removeMarker(latLng);
  }

  private void onAddMarker(ReadableMap map) {
    double latitude = map.getDouble("latitude");
    double longitude = map.getDouble("longitude");
    boolean zoomToLatLong = map.getBoolean("zoomToLatLong");

    LatLng latLng = new LatLng(latitude, longitude);
    GoogleMap mGoogleMap = mViewManager.getGoogleMap();
    if (zoomToLatLong) {
      mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
    }
    MarkerOptions mumbai = new MarkerOptions().position(latLng)
        .title("Mumbai")
        .icon(BitmapDescriptorFactory.defaultMarker())
        .anchor(1f, 2f);
    Marker marker = mGoogleMap.addMarker(mumbai);
    mMarkerManager.addMarker(latLng, marker);
  }

  private static class MarkerManager {
    private Map<LatLng, Marker> markers;

    private MarkerManager() {
      markers = new HashMap<>();
    }

    private void addMarker(LatLng mLatLng, Marker marker) {
      markers.put(mLatLng, marker);
    }

    private void removeMarker(LatLng latLng) {
      if (markers.containsKey(latLng)) {
        Marker marker = markers.get(latLng);
        Objects.requireNonNull(marker).remove();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          markers.remove(latLng, marker);
        } else {
          markers.remove(latLng);
        }
      }
    }
  }
}
