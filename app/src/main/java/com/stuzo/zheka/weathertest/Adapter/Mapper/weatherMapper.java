package com.stuzo.zheka.weathertest.Adapter.Mapper;

import com.stuzo.zheka.weathertest.Api.ModelDTO.WeatherDTO;
import com.stuzo.zheka.weathertest.Model.Weather;

import rx.functions.Func1;

/**
 * Created by Zheka on 27.04.16.
 */
public class weatherMapper  implements Func1<WeatherDTO, Weather> {

    public weatherMapper() {

    }

    @Override
    public Weather call(WeatherDTO input) {
        if (input == null) {
            return null;
        }
            return new Weather(input.getName(),
                    input.getWeather().get(0).getDescription(),
                    input.getMain().getHumidity(),
                    input.getMain().getPressure(),
                    input.getMain().getTemp(),
                    input.getWind().getSpeed(),
                    input.getWeather().get(0).getIcon()
            );
        }

}