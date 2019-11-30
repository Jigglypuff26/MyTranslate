package hfad.com.mytranslate.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    private static final String AUTH = "Basic " + Base64.encodeToString(("Max26:123456").getBytes(), Base64.NO_WRAP);

//    private static final String BASE_URL = "http://192.168.10.100/hhh/public/";
    private static final String BASE_URL = "http://turkinfoapp.ru/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    // передача данных файлу PHP  на сервер для дальнейшей обработки
    private RetrofitClient() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request original = chain.request();
//
//                                Request.Builder requestBuilder = original.newBuilder()
//                                        .addHeader("Authorization", AUTH)
//                                        .method(original.method(), original.body());
//
//                                Request request = requestBuilder.build();
//                                return chain.proceed(request);
//                            }
//                        }
//                ).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getApi() {
        return retrofit.create(API.class);
    }

}
