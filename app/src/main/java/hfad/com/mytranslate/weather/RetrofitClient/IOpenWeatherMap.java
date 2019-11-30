package hfad.com.mytranslate.weather.RetrofitClient;

import hfad.com.mytranslate.weather.Models.Five_Days.Weather_5_Result;
import io.reactivex.Observable;
import hfad.com.mytranslate.weather.Models.WeatherResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {

    @GET("weather")
    Observable<WeatherResult> getWeatherByLatLng(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("appid") String appid,
                                                 @Query("units") String unit);

    @GET("forecast")
    Observable<Weather_5_Result> getWeatherByLatLng_5(@Query("lat") String lat,
                                                      @Query("lon") String lon,
                                                      @Query("appid") String appid,
                                                      @Query("units") String unit);
}
