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

import pocketruck.penny.pocketruck_android.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText pw, email;
    private TextView warnEmail,warnPw,gotoSignUp;
    private boolean[] emailOk = new boolean[1];
    private boolean[] pwOk = new boolean[1];
    private Button login;
    private String checkEmail,checkPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            login.setOnClickListener(null);
        }
    }



}

