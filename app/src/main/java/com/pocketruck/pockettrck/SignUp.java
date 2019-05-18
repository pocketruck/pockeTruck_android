package com.pocketruck.pockettrck;

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText email = (EditText)findViewById(R.id.suEmail);
        final EditText pw = (EditText)findViewById(R.id.suPassWd);
        final EditText pwCk = (EditText)findViewById(R.id.suPassWdChk);
        final CheckBox agree = (CheckBox)findViewById(R.id.agree);
        final TextView warnEmail = (TextView)findViewById(R.id.warningEmial);
        final TextView warnPw = (TextView)findViewById(R.id.warningPw);
        final TextView warnPwChk = (TextView)findViewById(R.id.warningPwChk);

        final boolean[] emailOk = new boolean[1];
        final boolean[] pwOk = new boolean[1];
        final boolean[] pwOkCk = new boolean[1];
        final boolean[] agreeOk = new boolean[1];

        final TextView login = (TextView) findViewById(R.id.logIn);
        final Button btn = (Button)findViewById(R.id.signupBtn);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnEmail.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chkEmail = email.getText().toString();
                emailOk[0] = checkEmail(chkEmail);


                if (emailOk[0]){
                    warnEmail.setVisibility(View.INVISIBLE);

                }
                if(emailOk[0] && pwOk[0] && pwOkCk[0] && agreeOk[0]){

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

        pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPw.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String chkPw = pw.getText().toString();
                pwOk[0] = checkPW(chkPw);

                if (pwOk[0]){
                    warnPw.setVisibility(View.INVISIBLE);

                }

                if(emailOk[0] && pwOk[0] && pwOkCk[0] && agreeOk[0]){

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
        });

        pwCk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                warnPwChk.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String chkPwOk = pwCk.getText().toString();
                pwOkCk[0] = chkPwOk.equals(pw.getText().toString());

                if (pwOkCk[0]){
                    warnPwChk.setVisibility(View.INVISIBLE);

                }
                if(emailOk[0] && pwOk[0] && pwOkCk[0] && agreeOk[0]){
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
        });

        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                agreeOk[0] = agree.isChecked();

                if(emailOk[0] && pwOk[0] && pwOkCk[0] && agreeOk[0]){
                    btn.setOnClickListener(new View.OnClickListener() {
                        String e = email.getText().toString();
                        String p = pw.getText().toString();
                        String pc = pwCk.getText().toString();

                        @Override
                        public void onClick(View v) {
                            Log.w("info",e+", "+p+", " + pc);

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    btn.setOnClickListener(null);
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
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
