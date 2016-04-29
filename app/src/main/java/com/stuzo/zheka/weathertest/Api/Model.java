package com.stuzo.zheka.weathertest.Api;

import com.stuzo.zheka.weathertest.Api.ModelDTO.Weather;
import com.stuzo.zheka.weathertest.Api.ModelDTO.WeatherDTO;

import rx.Observable;

/**
 * Created by Zheka on 27.04.16.
 */
public interface Model {
    Observable<WeatherDTO> getWeather(Double lat, Double lon, String units);
}
