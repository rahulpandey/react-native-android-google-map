package com.mapintegration;

import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

public class GoogleMapView extends MapView implements OnMapReadyCallback {

  private MapListener listener;
  private GoogleMap googleMap;

  //Just for ignoring line warning
  private GoogleMapView(Context context) {
    super(context);
  }

  public GoogleMapView(ThemedReactContext reactContext, GoogleMapOptions googleMapOptions,
      MapListener listener) {
    super(reactContext, googleMapOptions);
    this.listener = listener;
    super.onCreate(null);
    super.getMapAsync(this);
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    this.googleMap = googleMap;
    listener.onMapReady(this);
  }

  public GoogleMap getGoogleMap() {
    return googleMap;
  }
}
