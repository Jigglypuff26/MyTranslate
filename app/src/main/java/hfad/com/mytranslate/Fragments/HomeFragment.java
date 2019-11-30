package hfad.com.mytranslate.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import hfad.com.mytranslate.Exchange_Rates.Fragment.Exchange_Rates;
import hfad.com.mytranslate.Fragments.non_services.NonConnect;
import hfad.com.mytranslate.Fragments.non_services.NonGPS;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.storage.SharedPrefManager;
import hfad.com.mytranslate.weather.Common.Common;
import hfad.com.mytranslate.weather.Fragments.five_days;
import hfad.com.mytranslate.weather.Fragments.weather;

public class HomeFragment extends Fragment {

    //погода
    //получение гео данных
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    //переменная для onPause и onResume
    private boolean running = true;

    //display panel
    ProgressBar loading;
    LinearLayout scrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //получение гео данных для погоды
            check_GPS();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemview_weather = inflater.inflate(R.layout.home_fragment, container, false);
        scrollView = (LinearLayout)itemview_weather.findViewById(R.id.weather_all);
        loading = (ProgressBar)itemview_weather.findViewById(R.id.loading_all);
        return itemview_weather;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onPause() {
        super.onPause();
        running = false;
    }
    @Override
    public void onResume() {
        super.onResume();
        running = true;
    }
    private void check_GPS() {
        //Проверка интерната
            if (isConnected_GSP()) {
                if (isConnected()) {
                    Dexter_detected();
                } else {
                    non_connect(new NonConnect());
                }
            } else {
                non_connect(new NonGPS());
            }
    }

    private void Dexter_detected() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            buildLocationRequest();
                            buildLocationCallBack();
                            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
                            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }

                        if (report.isAnyPermissionPermanentlyDenied()){
                            return;
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Toast.makeText(getContext(), "Локация не найдена", Toast.LENGTH_SHORT).show();
                    }
                }).check();
    }

    //погода
    //получение гео данных (частота запроса данных)
    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //жеаемый интрвал для обновления место положения
        locationRequest.setInterval(10000);
        //самый быстрый интервал для обновленич
        locationRequest.setFastestInterval(5000);
        //погренгость
        locationRequest.setSmallestDisplacement(6000.0f);
    }
    //погода
    //получение гео данных (и присвоение  переменной current_location)
    private void buildLocationCallBack() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                     Common.current_location = locationResult.getLastLocation();
                     if (running) {
                         displayFragment_Praze_weather(new weather());
                         displayFragment_Praze_weather_5(new five_days());
                     }
                     Log.d("loca", Common.current_location.getLatitude()+ "/" + Common.current_location.getLongitude());
            }
        };
    }
    //GPS checked
    public boolean isConnected_GSP() {
        boolean GPS_connected = false;
            try {
                LocationManager lm = (LocationManager )getActivity().getSystemService(getContext().LOCATION_SERVICE);
                GPS_connected = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                return GPS_connected;
            } catch (Exception e) {
                Log.e("Connectivity Exception", e.getMessage());
            }
        return false;
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
        return false;
    }
    //Fragment ADD
    private void displayFragment_Praze_weather(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.fragment_weather_place, fragment)
                .commit();
    }
    private void displayFragment_Praze_weather_5(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.fragment_weather_place_5, fragment)
                .commit();
    }
    private void non_connect(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.relativeLayout, fragment)
                .commit();
    }
}