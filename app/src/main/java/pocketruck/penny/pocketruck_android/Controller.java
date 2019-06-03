package pocketruck.penny.pocketruck_android;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pocketruck.penny.pocketruck_android.api.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pocketruck.penny.pocketruck_android.api.RetrofitAPI.BaseURL;


public class Controller {
    private static Controller controller = new Controller();

    public  static Controller getInstance() {
        return controller;
    }

    private Controller(){
    }

    private RetrofitAPI retrofitAPI;


    public RetrofitAPI getRetrofitAPI(){
        return retrofitAPI;
    }

    public void retrofitService(){
        if (retrofitAPI == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())//파싱등록
                    .build();

            retrofitAPI = retrofit.create(RetrofitAPI.class);
        }

    }

}
/*
 * https://newland435.tistory.com/25
 * 참고했음
 * */