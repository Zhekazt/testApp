package com.stuzo.zheka.weathertest.Model;

/**
 * Created by Zheka on 27.04.16.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.stuzo.zheka.weathertest.Const;
import com.stuzo.zheka.weathertest.MapsActivity;
import com.stuzo.zheka.weathertest.R;

import java.util.concurrent.ExecutionException;

import lombok.Getter;
import lombok.Setter;


public class Weather {
    @Getter @Setter private String geoName = "NA";
    @Getter @Setter private String description= "NA";
    @Getter @Setter private Integer humidity = 0;
    @Getter @Setter private Double pressure = 0.0;
    @Getter @Setter private Double temp = 0.0;
    @Getter @Setter private Double windSpeed = 0.0;
    @Getter @Setter private String icon = "";
    @Getter @Setter private Bitmap iconBitmap;


    public Weather(String geoName, String description,Integer humidity,Double pressure, Double temp, Double windSpeed, String icon) {
        this.geoName = geoName;
        this.description = description;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.icon = icon;
        if (icon.length()>0) {
            loadImage(Const.BASE_URL + "img/w/" + icon);
        } else {
            setDefaultImage();
        }

    }
    private void loadImage(final String url) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    iconBitmap = Glide
                            .with(MapsActivity.getContext())
                            .load(url)
                            .asBitmap()
                            .into(-1, -1)
                            .get();
                } catch (final ExecutionException | InterruptedException e) {
                    setDefaultImage();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void dummy) {
                if (null != iconBitmap) {
                    Log.d("Loader", "Image loaded");
                    MapsActivity.getMarker().showInfoWindow();
                };
            }
        }.execute();
    }

    private void setDefaultImage(){
        iconBitmap =  BitmapFactory.decodeResource(MapsActivity.getContext().getResources(), R.drawable.default_icon);
    }
}
