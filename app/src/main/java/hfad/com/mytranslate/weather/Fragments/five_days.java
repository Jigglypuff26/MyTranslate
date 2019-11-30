package hfad.com.mytranslate.weather.Fragments;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import hfad.com.mytranslate.Fragments.non_services.service_trobl;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.weather.Common.Common;
import hfad.com.mytranslate.weather.Models.Five_Days.Weather_5_Result;
import hfad.com.mytranslate.weather.RetrofitClient.IOpenWeatherMap;
import hfad.com.mytranslate.weather.RetrofitClient.RetrofitClient;
import hfad.com.mytranslate.weather.adapter.Weather_Adapter_five;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class five_days extends Fragment {

    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;

    TextView txt_city_name, txt_geo_coord;
    RecyclerView recyclerView;

    static  five_days instance;

    public static five_days getInstance() {
        if(instance == null)
            instance = new five_days();
        return instance;
    }

    public five_days() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_five_days, container, false);
        txt_city_name = (TextView) itemView.findViewById(R.id.txt_city_name_5);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview_five);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        check_SERVICE_location();
        return itemView;
    }

    //проверка сервисов
    private void check_SERVICE_location() {

        //Проверка интерната
        if (isConnected()) {
            //Проверка GPS
            if (Common.current_location != null) {
                getFiveDaysInfo();
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


    private void getFiveDaysInfo() {
        compositeDisposable.add(mService.getWeatherByLatLng_5(
                String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Weather_5_Result>() {
                    @Override
                    public void accept(Weather_5_Result weather_5_result) throws Exception {

                        displayFievWeather(weather_5_result);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        non_connect(new service_trobl());
                        Log.d("ERROR_5_days", ""+throwable.getMessage());
                    }
                })
        );
    }

    private void displayFievWeather(Weather_5_Result weather_5_result) {
        txt_city_name.setText(new StringBuilder(weather_5_result.city.name));
        Weather_Adapter_five adapter = new Weather_Adapter_five(getContext(),weather_5_result);
        recyclerView.setAdapter(adapter);
    }

    //Ошибка сервиса
    private void non_connect(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.relativeLayout, fragment)
                .commit();
    }

}
