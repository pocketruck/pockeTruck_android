package com.pocketruck.pockettrck;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;
    private final int FRAGMENT4 = 4;

    private Button tab1, tab2, tab3, tab4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯에 대한 참조
        tab1 = (Button)findViewById(R.id.tab1);
        tab2 = (Button)findViewById(R.id.tab2);
        tab3 = (Button)findViewById(R.id.tab3);
        tab4 = (Button)findViewById(R.id.tab4);

        //탭 버튼에 대한 리스너 연결
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);

        //어떤 프레그먼트를 프레임레이아웃에 띄울것인가?
        callFragment(FRAGMENT1);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tab1 :
                callFragment(FRAGMENT1);
                break;

            case R.id.tab2 :
                callFragment(FRAGMENT2);
                break;

            case R.id.tab3 :
                callFragment(FRAGMENT3);
                break;

            case R.id.tab4 :
                callFragment(FRAGMENT4);
                break;
        }

    }

    private void callFragment(int fragment_no){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no){
            case 1:
                tab1 fragment1 = new tab1();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case 2:
                tab2 fragment2 = new tab2();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case 3:
                tab3 fragment3 = new tab3();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;

            case 4:
                tab4 fragment4 = new tab4();
                transaction.replace(R.id.fragment_container, fragment4);
                transaction.commit();
                break;
        }

    }
}
