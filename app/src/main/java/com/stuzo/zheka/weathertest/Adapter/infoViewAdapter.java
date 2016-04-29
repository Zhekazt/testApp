package com.stuzo.zheka.weathertest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.stuzo.zheka.weathertest.Const;
import com.stuzo.zheka.weathertest.MapsActivity;
import com.stuzo.zheka.weathertest.Model.Weather;
import com.stuzo.zheka.weathertest.R;

/**
 * Created by Zheka on 27.04.16.
 */
public class infoViewAdapter implements GoogleMap.InfoWindowAdapter {

    private View view;
    private Weather mWeather;

    public infoViewAdapter(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.infowindowlayout, null);

    }

    @Override
    public View getInfoContents(final Marker marker) {
        if (MapsActivity.getMarker() != null
                && MapsActivity.getMarker().isInfoWindowShown()) {
            MapsActivity.getMarker().hideInfoWindow();
            MapsActivity.getMarker().showInfoWindow();
        }
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {

        TextView locationName = (TextView) view.findViewById(R.id.locationName);
        TextView sky = (TextView) view.findViewById(R.id.sky);
        TextView wind = (TextView) view.findViewById(R.id.wind);
        TextView temp = (TextView) view.findViewById(R.id.temp);
        ImageView ico = (ImageView) view.findViewById(R.id.icon);


        locationName.setText(mWeather.getGeoName());
        sky.setText(mWeather.getDescription());
        wind.setText(String.format("wind: %s %s", mWeather.getWindSpeed(), Const.UNITS.equalsIgnoreCase("metric")? "meter/sec": "miles/hour"));
        temp.setText(String.format("temp: %s °%s", mWeather.getTemp(), Const.UNITS.equalsIgnoreCase("metric")? "C": "F"));
        ico.setImageBitmap(mWeather.getIconBitmap());
        return view;
    }

    public void update(Weather weather) {
        this.mWeather = weather;
    }
}
