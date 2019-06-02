package pocketruck.penny.pocketruck_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends Activity {
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);

        } catch (InterruptedException ie){
            ie.printStackTrace();
        }
        //인터넷 연결 확인
        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"인터넷 연결을 확인해 주세요", Toast.LENGTH_SHORT).show();
            this.finishAffinity();
        }

    }
}
