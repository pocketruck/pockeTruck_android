package pocketruck.penny.pocketruck_android.api;

import pocketruck.penny.pocketruck_android.model.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    public static final String BaseURL = "http://c257d131.ngrok.io/";

    @POST("accounts/signUp/")
    Call<SignUp> postSignUp(@Body SignUp signUp);

}

