package com.stuzo.zheka.weathertest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.stuzo.zheka.weathertest.Adapter.infoViewAdapter;
import com.stuzo.zheka.weathertest.Model.Weather;
import com.stuzo.zheka.weathertest.Presenter.weatherPresenter;

public class MapsActivity extends FragmentActivity implements com.stuzo.zheka.weathertest.View.View, OnMapReadyCallback {

    private GoogleMap mMap;
    private static Marker marker;
    private infoViewAdapter adapter;
    private weatherPresenter presenter;
    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (isNetworkAvailable(this)) {
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(this);
            presenter = new weatherPresenter(this);
            mContext = getApplicationContext();
            adapter = new infoViewAdapter(getLayoutInflater());
        } else {
            showError(this.getString(R.string.error_internet_connetcion));
        }
    }

    public static Marker getMarker() {
        return marker;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null) marker.remove();
                if (isNetworkAvailable(getContext())) {

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    marker = mMap.addMarker(markerOptions);
                    mMap.setInfoWindowAdapter(adapter);
                    presenter.getWeather(latLng.latitude, latLng.longitude, Const.UNITS);
                } else {
                    showError(getContext().getString(R.string.error_internet_connetcion));
                }
            }
        });
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWeather(Weather weather) {
        if (weather != null) {
            adapter.update(weather);
        }
    }


    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
            return false;
        }
        return false;
    }

}
