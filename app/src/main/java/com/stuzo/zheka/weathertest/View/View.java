package com.stuzo.zheka.weathertest.View;

import com.stuzo.zheka.weathertest.Model.Weather;

/**
 * Created by Zheka on 27.04.16.
 */
public interface View {
    void showError(String error);
    void showWeather(Weather weather);
}
