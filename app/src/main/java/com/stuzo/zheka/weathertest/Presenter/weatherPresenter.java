package com.stuzo.zheka.weathertest.Presenter;

/**
 * Created by Zheka on 27.04.16.
 */


import com.stuzo.zheka.weathertest.Adapter.Mapper.weatherMapper;
import com.stuzo.zheka.weathertest.Api.Model;
import com.stuzo.zheka.weathertest.MapsActivity;
import com.stuzo.zheka.weathertest.Model.Weather;
import com.stuzo.zheka.weathertest.Api.ModelDTO.WeatherDTO;
import com.stuzo.zheka.weathertest.Api.ModelImpl;
import com.stuzo.zheka.weathertest.R;
import com.stuzo.zheka.weathertest.View.View;

import java.net.UnknownHostException;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public class weatherPresenter {

    private Model model = new ModelImpl();
    private weatherMapper mapper = new weatherMapper();

    private View view;
    private Subscription subscription = Subscriptions.empty();

    public weatherPresenter(View view) {
        this.view = view;
    }


    public void getWeather(Double lat, Double lon, String metric){
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getWeather( lat,  lon,  metric)
                .map(mapper)
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if( e.getClass() == UnknownHostException.class) {
                            view.showError(MapsActivity.getContext().getString(R.string.error_internet_connetcion));
                        } else{
                            view.showError(e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Weather data) {
                        if (data != null) {
                            view.showWeather(data);
                        }
                    }
                });
    }
}
