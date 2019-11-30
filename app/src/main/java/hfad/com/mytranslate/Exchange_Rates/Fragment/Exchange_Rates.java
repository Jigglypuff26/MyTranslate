package hfad.com.mytranslate.Exchange_Rates.Fragment;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import hfad.com.mytranslate.Exchange_Rates.API.API_Exchange_Rates;
import hfad.com.mytranslate.Exchange_Rates.Model.RootObject;
import hfad.com.mytranslate.Exchange_Rates.Retrof.RetrofitClient;
import hfad.com.mytranslate.Fragments.non_services.service_trobl;
import hfad.com.mytranslate.R;
import hfad.com.mytranslate.weather.Fragments.weather;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Exchange_Rates extends Fragment implements View.OnClickListener {

    private EditText ER_RU, ER_EUR, ER_RU_try, ER_TRY, ER_RU_usd, ER_USD, ER_RU_gbp, ER_GBP;
    private Double Value_EUR, Value_TRY, Value_USD ,Value_GBP;
    private EditText ER_TRY_rub, ER_RUB_TRY_result, ER_USD_try, ER_USD_TRY_result, ER_TRY_eur, ER_EUR_TRY_result, ER_GBP_try, ER_GBP_TRY_result;

    ScrollView scrollView;
    ProgressBar load;

    CompositeDisposable compositeDisposable;
    API_Exchange_Rates mService;


    static weather instance;

    public static weather getInstance() {
        if (instance ==  null) {
            instance = new weather();
        }
        return instance;
    }

    public Exchange_Rates() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(API_Exchange_Rates.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View Itemview =  inflater.inflate(R.layout.fragment_exchange__rates, container, false);

       //Курс рубля
        ER_RU = (EditText)Itemview.findViewById(R.id.Exchange_Rates_RUB);
        ER_EUR = (EditText)Itemview.findViewById(R.id.Exchange_Rates_EUR);
        ER_RU_try = (EditText)Itemview.findViewById(R.id.Exchange_Rates_RUS_try);
        ER_TRY = (EditText)Itemview.findViewById(R.id.Exchange_Rates_TRY);
        ER_RU_usd = (EditText)Itemview.findViewById(R.id.Exchange_Rates_RUS_usd);
        ER_USD = (EditText)Itemview.findViewById(R.id.Exchange_Rates_USD);
        ER_RU_gbp = (EditText)Itemview.findViewById(R.id.Exchange_Rates_RUS_gbp);
        ER_GBP = (EditText)Itemview.findViewById(R.id.Exchange_Rates_GBP);
        Itemview.findViewById(R.id.Exchange_Rates_RUB).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_EUR).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_RUS_try).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_TRY).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_RUS_usd).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_USD).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_RUS_gbp).setOnClickListener(this);
        Itemview.findViewById(R.id.Exchange_Rates_GBP).setOnClickListener(this);

        //Курс лиры
        ER_TRY_rub = (EditText)Itemview.findViewById(R.id.ER_TRY_rub);
        ER_RUB_TRY_result = (EditText)Itemview.findViewById(R.id.ER_RUB_TRY_result);
        ER_USD_try = (EditText)Itemview.findViewById(R.id.ER_USD_try);
        ER_USD_TRY_result = (EditText)Itemview.findViewById(R.id.ER_USD_TRY_result);
        ER_TRY_eur = (EditText)Itemview.findViewById(R.id.ER_TRY_eur);
        ER_EUR_TRY_result = (EditText)Itemview.findViewById(R.id.ER_EUR_TRY_result);
        ER_GBP_try = (EditText)Itemview.findViewById(R.id.ER_GBP_try);
        ER_GBP_TRY_result = (EditText)Itemview.findViewById(R.id.ER_GBP_TRY_result);
        Itemview.findViewById(R.id.ER_TRY_rub).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_RUB_TRY_result).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_USD_try).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_USD_TRY_result).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_TRY_eur).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_EUR_TRY_result).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_GBP_try).setOnClickListener(this);
        Itemview.findViewById(R.id.ER_GBP_TRY_result).setOnClickListener(this);

        scrollView = (ScrollView) Itemview.findViewById(R.id.gone_ER);
        load = (ProgressBar)Itemview.findViewById(R.id.loading_ER);

        if (isConnectedToInternet()) {
            ADD_valus();
        } else {
            Itemview =  inflater.inflate(R.layout.fragment_non_connect, container, false);
        }
        return Itemview;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void ADD_valus() {
        compositeDisposable.add(mService.getExchange_Rates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RootObject>() {
                    @Override
                    public void accept(RootObject rootObject) throws Exception {
//                        ER_EUR.append(new StringBuilder(String.valueOf(rootObject.getValute().getEUR().getNominal())));
                        Value_EUR = Double.valueOf(String.valueOf(rootObject.getValute().getEUR().getValue()));
//                        ER_TRY.append(new StringBuilder(String.valueOf(rootObject.getValute().getTRY().getNominal())));
                        Value_TRY = Double.valueOf(String.valueOf(rootObject.getValute().getTRY().getValue()));
//                        ER_USD.append(new StringBuilder(String.valueOf(rootObject.getValute().getUSD().getNominal())));
                        Value_USD = Double.valueOf(String.valueOf(rootObject.getValute().getUSD().getValue()));
//                        ER_GBP.append(new StringBuilder(String.valueOf(rootObject.getValute().getGBP().getNominal())));
                        Value_GBP = Double.valueOf(String.valueOf(rootObject.getValute().getGBP().getValue()));

                        //display panel
                        scrollView.setVisibility(View.VISIBLE);
                        load.setVisibility(View.GONE);

                    }
                }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Валюта", "Ошибка API: " + throwable.getMessage());
                                non_connect(new service_trobl());
                            }
                        }
                ));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Exchange_Rates_EUR:
                if(ER_EUR.length() != 0) {
                    Exchange_Rates_RUS();
                } else {
                    ER_EUR.setText(String.valueOf(1));
                    Exchange_Rates_RUS();
                }
                break;
            case R.id.Exchange_Rates_RUB:
                if(ER_RU.length()  != 0) {
                    Exchange_Rates_EUR();
                } else {
                    ER_RU.setText(String.valueOf(1));
                    Exchange_Rates_EUR();
                }
                break;
            case R.id.Exchange_Rates_TRY:
                if(ER_TRY.length() != 0) {
                    Exchange_Rates_RUS_try();
                } else {
                    ER_TRY.setText(String.valueOf(1));
                    Exchange_Rates_RUS_try();
                }
                break;
            case R.id.Exchange_Rates_RUS_try:
                if(ER_RU_try.length() != 0) {
                    Exchange_Rates_TRY();
                } else {
                    ER_RU_try.setText(String.valueOf(1));
                    Exchange_Rates_TRY();
                }
                break;
            case R.id.Exchange_Rates_USD:
                if(ER_USD.length() != 0) {
                    Exchange_Rates_RUS_usd();
                } else {
                    ER_USD.setText(String.valueOf(1));
                    Exchange_Rates_RUS_usd();
                }
                break;
            case R.id.Exchange_Rates_RUS_usd:
                if(ER_RU_usd.length() != 0) {
                    Exchange_Rates_USD();
                } else {
                    ER_RU_usd.setText(String.valueOf(1));
                    Exchange_Rates_USD();
                }
                break;
            case R.id.Exchange_Rates_GBP:
                if(ER_GBP.length() != 0) {
                    Exchange_Rates_RUS_gbp();
                } else {
                    ER_GBP.setText(String.valueOf(1));
                    Exchange_Rates_RUS_gbp();
                }
                break;
            case R.id.Exchange_Rates_RUS_gbp:
                if(ER_RU_gbp.length() != 0) {
                    Exchange_Rates_GBP();
                } else {
                    ER_RU_gbp.setText(String.valueOf(1));
                    Exchange_Rates_GBP();
                }
                break;
            case R.id.ER_TRY_rub:
                if(ER_TRY_rub.length() != 0) {
                    ER_rub_TRY();
                } else {
                    ER_TRY_rub.setText(String.valueOf(1));
                    ER_rub_TRY();
                }
                break;
            case R.id.ER_RUB_TRY_result:
                if(ER_RUB_TRY_result.length() != 0) {
                    ER_RUB_try();
                } else {
                    ER_RUB_TRY_result.setText(String.valueOf(1));
                    ER_RUB_try();
                }
                break;
            case R.id.ER_USD_try:
                if(ER_USD_try.length() != 0) {
                    ER_usd_TRY();
                } else {
                    ER_USD_try.setText(String.valueOf(1));
                    ER_usd_TRY();
                }
                break;
            case R.id.ER_USD_TRY_result:
                if(ER_USD_TRY_result.length() != 0) {
                    ER_USD_try();
                } else {
                    ER_USD_TRY_result.setText(String.valueOf(1));
                    ER_USD_try();
                }
                break;
            case R.id.ER_TRY_eur:
                if(ER_TRY_eur.length() != 0) {
                    ER_eur_TRY();
                } else {
                    ER_TRY_eur.setText(String.valueOf(1));
                    ER_eur_TRY();
                }
                break;
            case R.id.ER_EUR_TRY_result:
                if(ER_EUR_TRY_result.length() != 0) {
                    ER_EUR_try();
                } else {
                    ER_EUR_TRY_result.setText(String.valueOf(1));
                    ER_EUR_try();
                }
                break;
            case R.id.ER_GBP_try:
                if(ER_GBP_try.length() != 0) {
                    ER_gbp_TRY();
                } else {
                    ER_GBP_try.setText(String.valueOf(1));
                    ER_gbp_TRY();
                }
                break;
            case R.id.ER_GBP_TRY_result:
                if(ER_GBP_TRY_result.length() != 0) {
                    ER_GBP_try();
                } else {
                    ER_GBP_TRY_result.setText(String.valueOf(1));
                    ER_GBP_try();
                }
                break;
        }
    }

    //TRY
    private void  Exchange_Rates_RUS_try() {
        double RUS_ER = Double.parseDouble(ER_TRY.getText().toString());
        double result_rus = RUS_ER*Value_TRY;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_RU_try.setText(String.valueOf(result_rus));
//        Exchange_Rates_Val.setText(new DecimalFormat("##.##").format(result_rus));
    }

    private void  Exchange_Rates_TRY() {
        double RUS_ER = Double.parseDouble(ER_RU_try.getText().toString());
        double result_rus = RUS_ER/Value_TRY;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_TRY.setText(String.valueOf(result_rus));
//        Exchange_Rates_RU.setText(new DecimalFormat("##.##").format(result_rus));
    }

    //EUR
    private void  Exchange_Rates_RUS() {
        double RUS_ER = Double.parseDouble(ER_EUR.getText().toString());
        double result_rus = RUS_ER*Value_EUR;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_RU.setText(String.valueOf(result_rus));
//        Exchange_Rates_Val.setText(new DecimalFormat("##.##").format(result_rus));
    }

    private void  Exchange_Rates_EUR() {
        double RUS_ER = Double.parseDouble(ER_RU.getText().toString());
        double result_rus = RUS_ER/Value_EUR;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_EUR.setText(String.valueOf(result_rus));
//        Exchange_Rates_RU.setText(new DecimalFormat("##.##").format(result_rus));
    }

    //USD
    private void  Exchange_Rates_RUS_usd() {
        double RUS_ER = Double.parseDouble(ER_USD.getText().toString());
        double result_rus = RUS_ER*Value_USD;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_RU_usd.setText(String.valueOf(result_rus));
//        Exchange_Rates_Val.setText(new DecimalFormat("##.##").format(result_rus));
    }

    private void  Exchange_Rates_USD() {
        double RUS_ER = Double.parseDouble(ER_RU_usd.getText().toString());
        double result_rus = RUS_ER/Value_USD;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_USD.setText(String.valueOf(result_rus));
//        Exchange_Rates_RU.setText(new DecimalFormat("##.##").format(result_rus));
    }

    //USD
    private void  Exchange_Rates_RUS_gbp() {
        double RUS_ER = Double.parseDouble(ER_GBP.getText().toString());
        double result_rus = RUS_ER*Value_GBP;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_RU_gbp.setText(String.valueOf(result_rus));
//        Exchange_Rates_Val.setText(new DecimalFormat("##.##").format(result_rus));
    }

    private void  Exchange_Rates_GBP() {
        double RUS_ER = Double.parseDouble(ER_RU_gbp.getText().toString());
        double result_rus = RUS_ER/Value_GBP;
        result_rus = Math.round(result_rus * 1000.00) / 1000.00;
        ER_GBP.setText(String.valueOf(result_rus));
//        Exchange_Rates_RU.setText(new DecimalFormat("##.##").format(result_rus));
    }

    //RUB - TRY
    private void ER_RUB_try() {
        double Enter_data = Double.parseDouble(ER_RUB_TRY_result.getText().toString());
        double result = Enter_data*Value_TRY;
        result = Math.round(result * 1000.00) / 1000.00;
        ER_TRY_rub.setText(String.valueOf(result));
    }
    private void ER_rub_TRY() {
        double Enter_data = Double.parseDouble(ER_TRY_rub.getText().toString());
        double result = Enter_data/Value_TRY;
        result = Math.round(result * 1000.00) / 1000.00;
        ER_RUB_TRY_result.setText(String.valueOf(result));
    }

    //USD - TRY
    private void ER_USD_try() {
        double Enter_data = Double.parseDouble(ER_USD_TRY_result.getText().toString());
        double result = Enter_data/(Value_USD/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_USD_try.setText(String.valueOf(result));
    }
    private void ER_usd_TRY() {
        double Enter_data = Double.parseDouble(ER_USD_try.getText().toString());
        double result = Enter_data*(Value_USD/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_USD_TRY_result.setText(String.valueOf(result));
    }

    //EUR - TRY
    private void ER_EUR_try() {
        double Enter_data = Double.parseDouble(ER_EUR_TRY_result.getText().toString());
        double result = Enter_data/(Value_EUR/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_TRY_eur.setText(String.valueOf(result));
    }
    private void ER_eur_TRY() {
        double Enter_data = Double.parseDouble(ER_TRY_eur.getText().toString());
        double result = Enter_data*(Value_EUR/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_EUR_TRY_result.setText(String.valueOf(result));
    }

    //EUR - TRY
    private void ER_GBP_try() {
        double Enter_data = Double.parseDouble(ER_GBP_TRY_result.getText().toString());
        double result = Enter_data/(Value_GBP/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_GBP_try.setText(String.valueOf(result));
    }
    private void ER_gbp_TRY() {
        double Enter_data = Double.parseDouble(ER_GBP_try.getText().toString());
        double result = Enter_data*(Value_GBP/Value_TRY);
        result = Math.round(result * 1000.00) / 1000.00;
        ER_GBP_TRY_result.setText(String.valueOf(result));
    }

    //обработка обрыва сети
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }

    //Ошибка сервиса
    private void non_connect(Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.relativeLayout, fragment)
                .commit();
    }
}
