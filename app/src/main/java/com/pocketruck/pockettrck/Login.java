package com.pocketruck.pockettrck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText pw = (EditText)findViewById(R.id.passWd);
        final EditText email = (EditText)findViewById(R.id.email);
        final TextView warnEmail = (TextView)findViewById(R.id.warningEmial);
        final TextView warnPw = (TextView)findViewById(R.id.warningPw);

        final boolean[] emailOk = new boolean[1];
        final boolean[] pwOk = new boolean[1];

        final Button btn = (Button)findViewById(R.id.loginBtn);
        final TextView subtn = (TextView)findViewById(R.id.signUp);

        //email값 받아와서 string으로 변환해서 checkEmail에 넣어서 검사하기
        email.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnEmail.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String chkEmail = email.getText().toString();
                emailOk[0] = checkEmail(chkEmail);
                if(emailOk[0]){//정규식과 일치할경우 경고메세지 삭제
                    warnEmail.setVisibility(View.INVISIBLE);
                }

                if(emailOk[0] && pwOk[0]){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    btn.setOnClickListener(null);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });

        //pw값 받아와서 string으로 변환해서 checkPW에 넣어서 검사하기
        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPw.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chkPw = pw.getText().toString();
                pwOk[0] = checkPW(chkPw);
                if (pwOk[0]){ //정규식과 일치할경우 경고메세지 삭제
                    warnPw.setVisibility(View.INVISIBLE);
                }

                if(emailOk[0] && pwOk[0]){
                    btn.setOnClickListener(new View.OnClickListener() {
                        String e = email.getText().toString();
                        String p = pw.getText().toString();
                        @Override
                        public void onClick(View v) {
                            Log.w("info",e+", "+p);

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    btn.setOnClickListener(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

        });

        subtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
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



}
