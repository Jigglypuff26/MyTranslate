package hfad.com.mytranslate.weather.Fragments;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hfad.com.mytranslate.Fragments.non_services.NonConnect;
import hfad.com.mytranslate.Fragments.non_services.service_trobl;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.weather.Common.Common;
import hfad.com.mytranslate.weather.Models.WeatherResult;
import hfad.com.mytranslate.weather.RetrofitClient.IOpenWeatherMap;
import hfad.com.mytranslate.weather.RetrofitClient.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class weather extends Fragment {

    ImageView img_weather;
    TextView txt_city_name, txt_temperature, txt_description, txt_data_time, txt_wind, txt_pressure, txt_humidity, txt_sunrise, txt_sunset, txt_geo_coords;
    LinearLayout weather_1day;
    ProgressBar load;

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    static weather instance;

    public static weather getInstance() {
        if (instance ==  null) {
            instance = new weather();
        }
        return instance;
    }


    public weather() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View itemViev = inflater.inflate(R.layout.fragment_weather, container, false);

        //вставка переменных по ID
        img_weather = (ImageView) itemViev.findViewById(R.id.img_weather);
        txt_city_name = (TextView) itemViev.findViewById(R.id.txt_city_name);
        txt_temperature = (TextView) itemViev.findViewById(R.id.txt_temperature);
        txt_description = (TextView) itemViev.findViewById(R.id.txt_description);
        txt_data_time = (TextView) itemViev.findViewById(R.id.txt_data_time);
        txt_wind = (TextView) itemViev.findViewById(R.id.txt_wind);
        txt_pressure = (TextView) itemViev.findViewById(R.id.txt_pressure);
        txt_humidity = (TextView) itemViev.findViewById(R.id.txt_humidity);
        txt_sunrise = (TextView) itemViev.findViewById(R.id.txt_sunrise);
        txt_sunset = (TextView) itemViev.findViewById(R.id.txt_sunset);
        txt_geo_coords = (TextView) itemViev.findViewById(R.id.txt_geo_coords);

        weather_1day = (LinearLayout)itemViev.findViewById(R.id.weather_panel);
        load = (ProgressBar)itemViev.findViewById(R.id.loading_f);

           //проверка сервисов
        check_SERVICE_location();

        return itemViev;
    }

    //проверка сервисов
    private void check_SERVICE_location() {
        //Проверка интерната
        if (isConnected()) {
            //Проверка GPS
            if (Common.current_location != null) {
                getWeatherUnformation();
            } else {
                Toast.makeText(getActivity(), "место положение не опреоделено", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "нет соединения с интернетом", Toast.LENGTH_SHORT).show();
        }
    }

    //Проверка соединения с интернетом
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    //Присвоение данных  переменным
    private void getWeatherUnformation() {

                compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                        String.valueOf(Common.current_location.getLongitude()),
                        Common.APP_ID,
                        "metric")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {

                        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                        .append(".png").toString()).into(img_weather);

                        //загружаемая ниформация
                        txt_city_name.setText(weatherResult.getName());
                        txt_description.setText(new StringBuilder("Погода в ")
                                .append(weatherResult.getName()).toString());
                        txt_temperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp()))
                                .append(" °C ").toString());
                        txt_data_time.setText(Common.convertUnixDate(weatherResult.getDt()));
                        txt_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure()))
                                .append(" hpa ").toString());
                        txt_humidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity()))
                                .append(" % ").toString());
                        txt_sunrise.setText(Common.convertUnixHour(weatherResult.getSys().getSunrise()));
                        txt_sunset.setText(Common.convertUnixHour(weatherResult.getSys().getSunset()));
                        txt_geo_coords.setText(new StringBuilder("[").append(weatherResult.getCoord().toString()).append("]").toString());
                        txt_wind.setText(new StringBuilder("Скорость: ").append(String.valueOf(weatherResult.getWind().getSpeed()))
                                .append(" m/s ").toString());

                        //display panel
                        weather_1day.setVisibility(View.VISIBLE);
                        load.setVisibility(View.GONE);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        non_connect(new service_trobl());
                        Log.d("Главная Погода", "Ошибка API: " + throwable.getMessage());
                    }
                })
        );
    }
    //Ошибка сервиса
    private void non_connect(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.relativeLayout, fragment)
                .commit();
    }
}

