package pocketruck.penny.pocketruck_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pocketruck.penny.pocketruck_android.api.RetrofitAPI;
import pocketruck.penny.pocketruck_android.model.Login;
import pocketruck.penny.pocketruck_android.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity {
    public static RetrofitAPI api ; //레트로핏

    private EditText pw, email;
    private TextView warnEmail,warnPw,gotoSignUp;
    private boolean[] emailOk = new boolean[1];
    private boolean[] pwOk = new boolean[1];
    private Button login;
    public String checkEmail,checkPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiBuilder();//레트로핏을 사용하기 위함
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText)findViewById(R.id.email);
        warnEmail = (TextView)findViewById(R.id.warningEmial);
        login = (Button)findViewById(R.id.loginBtn);

        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEmail = email.getText().toString();
                emailOk[0] = checkEmail(checkEmail);

                if(emailOk[0]){
                    warnEmail.setVisibility(View.INVISIBLE);
                } else{
                    warnEmail.setVisibility(View.VISIBLE);
                }

                loginButtonActivation(emailOk[0], pwOk[0]);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });

        pw = (EditText)findViewById(R.id.passWd);
        warnPw = (TextView)findViewById(R.id.warningPw);
        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPw = pw.getText().toString();
                pwOk[0] = checkPW(checkPw);

                if (pwOk[0]){
                    warnPw.setVisibility(View.INVISIBLE);
                }else{
                    warnEmail.setVisibility(View.VISIBLE);
                }

                loginButtonActivation(emailOk[0],pwOk[0]);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        gotoSignUp = (TextView)findViewById(R.id.signUp);
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    //email 유효성 검사
    public static boolean checkEmail(String email){

        String regex = "^[a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }

    //pw 유효성 검사
    public static boolean checkPW(String passwd){

        String regex = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+).{6,20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(passwd);
        boolean isNormal = m.matches();
        return isNormal;
    }

    //email&pw가 정규식에 맞으면 로그인 버튼 활성화 메소드
    public void loginButtonActivation(boolean email, boolean pw){
        if(email && pw){
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Login(checkEmail,checkPw);
                }
            });
        } else {
            login.setOnClickListener(null);
        }
    }

    public void Login(String username, String password){
        Login login = new Login(username, password);

        Call<User> userCall = api.login(login);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_SHORT).show();
                    loginSuccess();
                } else{
                    Toast.makeText(getApplicationContext(),"로그인 실패", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),"로그인 실패", LENGTH_SHORT).show();

            }
        });
    }

    public void apiBuilder(){
        Controller controller = Controller.getInstance();
        controller.retrofitService();
        api = Controller.getInstance().getRetrofitAPI();
    }

    public void loginSuccess(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }



}

