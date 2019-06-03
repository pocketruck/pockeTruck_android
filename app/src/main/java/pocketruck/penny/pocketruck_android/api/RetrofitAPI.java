package pocketruck.penny.pocketruck_android.api;

import pocketruck.penny.pocketruck_android.model.Login;
import pocketruck.penny.pocketruck_android.model.SignUp;

import pocketruck.penny.pocketruck_android.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    public static final String BaseURL = "http://6708786d.ngrok.io/";

    //회원가입
    @POST("accounts/signUp/")
    Call<SignUp> postSignUp(@Body SignUp signUp);

    //로그인
    @POST("accounts/login/")
    Call<User> login(@Body Login login);

}

