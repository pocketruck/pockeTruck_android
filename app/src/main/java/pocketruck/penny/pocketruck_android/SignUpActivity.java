package pocketruck.penny.pocketruck_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pocketruck.penny.pocketruck_android.R;

import pocketruck.penny.pocketruck_android.api.RetrofitAPI;
import pocketruck.penny.pocketruck_android.model.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    private RetrofitAPI api ; //레트로핏



    private EditText email, pw, pwCheck;
    private TextView warnEmail, warnPw, warnPwCheck, gotoLogin;
    private Button signUpButton;
    private CheckBox agree;
    private String getEmail,getPw, getPwCheck;


    private boolean[] emailOk = new boolean[1];
    private boolean[] pwOk = new boolean[1];
    private boolean[] pwCheckOk = new boolean[1];
    private boolean[] agreeOk = new boolean[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiBuilder();//레트로핏을 사용하기 위함

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText)findViewById(R.id.suEmail);
        warnEmail = (TextView)findViewById(R.id.warningEmial);
        signUpButton = (Button)findViewById(R.id.signupBtn);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getEmail = email.getText().toString();
                emailOk[0] = checkEmail(getEmail);

                if (emailOk[0]){
                    warnEmail.setVisibility(View.INVISIBLE);
                }else{
                    warnEmail.setVisibility(View.VISIBLE);
                }

                signUpButtonActivate(emailOk[0],pwOk[0],pwCheckOk[0],agreeOk[0]);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        pw = (EditText)findViewById(R.id.suPassWd);
        warnPw = (TextView)findViewById(R.id.warningPw);
        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getPw = pw.getText().toString();
                pwOk[0] = checkPW(getPw);

                if (pwOk[0]){
                    warnPw.setVisibility(View.INVISIBLE);
                }else {
                    warnPw.setVisibility(View.VISIBLE);
                }

                signUpButtonActivate(emailOk[0],pwOk[0],pwCheckOk[0],agreeOk[0]);

            }
        });

        pwCheck = (EditText)findViewById(R.id.suPassWdChk);
        warnPwCheck = (TextView)findViewById(R.id.warningPwChk);
        pwCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getPwCheck = pwCheck.getText().toString();
                pwCheckOk[0] = getPwCheck.equals(pw.getText().toString());

                if (pwCheckOk[0]){
                    warnPwCheck.setVisibility(View.INVISIBLE);

                }else {
                    warnPwCheck.setVisibility(View.VISIBLE);
                }

                signUpButtonActivate(emailOk[0],pwOk[0],pwCheckOk[0],agreeOk[0]);

            }
        });

        agree = (CheckBox)findViewById(R.id.agree);
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                agreeOk[0] = agree.isChecked();

                signUpButtonActivate(emailOk[0],pwOk[0],pwCheckOk[0],agreeOk[0]);
            }
        });

        gotoLogin = (TextView) findViewById(R.id.logIn);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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

    public void signUpButtonActivate(boolean mail, boolean password, boolean pwCheck, boolean agree){
        if(mail && password && pwCheck && agree){
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = email.getText().toString();
                    String password = pw.getText().toString();

                    Log.d("회원가입","username : "+username+" password : "+password);

                    signUp(username,password);
                }
            });
        } else {
            signUpButton.setOnClickListener(null);
        }
    }



    public void signUp(String username , String password){

        api = new RetrofitAPI() {
            @Override
            public Call<SignUp> postSignUp(SignUp signUp) {
                return null;
            }
        };

        //회원가입
        SignUp signUp = new SignUp();

        signUp.setUsername(username);
        signUp.setPassword(password);

        Call<SignUp> signUpCall = api.postSignUp(signUp);

        signUpCall.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"회원가입 성공", Toast.LENGTH_SHORT).show();
                    Login();
                } else{
                    Toast.makeText(getApplicationContext(),"회원가입 실패", LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(),"회원가입 실패", LENGTH_SHORT).show();

            }
        });
    }

    public void apiBuilder(){
        Controller controller = Controller.getInstance();
        controller.retrofitService();
        api = Controller.getInstance().getRetrofitAPI();
    }

    public void Login(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
