package com.stuzo.zheka.weathertest.Api;

import com.stuzo.zheka.weathertest.Api.ModelDTO.Weather;
import com.stuzo.zheka.weathertest.Api.ModelDTO.WeatherDTO;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zheka on 27.04.16.
 */
public class ModelImpl implements Model {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<WeatherDTO> getWeather(Double lat, Double lon, String units) {
        return apiInterface
                .getWeatherByLatLon(lat,lon,units)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
