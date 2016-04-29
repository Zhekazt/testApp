package com.stuzo.zheka.weathertest.Api;

import com.stuzo.zheka.weathertest.Api.ModelDTO.WeatherDTO;
import com.stuzo.zheka.weathertest.Const;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Zheka on 27.04.16.
 */
public interface ApiInterface {

    @GET("/data/2.5/weather?APPID="+ Const.APPID)
    Observable<WeatherDTO> getWeatherByLatLon(@Query("lat") Double lat, @Query("lon") Double lon, @Query("units") String units);
}
