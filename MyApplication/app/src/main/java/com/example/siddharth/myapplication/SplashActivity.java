package com.example.siddharth.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity
{

    Intent objIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run()
            {
                // This method will be executed once the timer is over
                if(isNetworkAvailable())
                {
                    /*Check if student is already register then do not go to franchise login page*/
                    SharedPreferences prefs = getApplicationContext().getSharedPreferences(getString(R.string.preferences), 0);
                    String strFranId = prefs.getString(getString( R.string.shared_preferences_franchisee_id), "0");

                    if(0 ==strFranId.compareToIgnoreCase("0")) {
                        objIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(objIntent);
                    }
                    else {
                        //objIntent = new Intent(SplashActivity.this, RegistrationFormActivity.class);
                        objIntent = new Intent(SplashActivity.this, RegistrationFormActivity.class);
                        startActivity(objIntent);
                    }

                }
                else
                {
                    objIntent = new Intent(SplashActivity.this, InterNetNotConnetedActivity.class);
                    startActivity(objIntent);
                }
                finish();
            }
        }, 1000);
    }

    private boolean isNetworkAvailable()
    {
        boolean bRet = false;
        try
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            bRet = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (Exception e)
        {
            Log.v("connectivity", e.toString());
        }
        return bRet;
    }
}
