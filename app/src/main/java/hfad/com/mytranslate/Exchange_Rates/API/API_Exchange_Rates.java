package hfad.com.mytranslate.Exchange_Rates.API;

import hfad.com.mytranslate.Exchange_Rates.Model.RootObject;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API_Exchange_Rates {

    @GET("daily_json.js")
    Observable<RootObject> getExchange_Rates();

}
