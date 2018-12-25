package com.example.siddharth.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection extends BroadcastReceiver
{
    ConnectivityManager objConnectivityManager;
    NetworkInfo objNetworkInfo;
    boolean bIsInternetConnected = false;
    static Context context;
    private static InternetConnection instance = new InternetConnection();

    public static InternetConnection getInstance(Context ctx)
    {
        context = ctx.getApplicationContext();
        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*objConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();
        if (objNetworkInfo != null && objNetworkInfo.isConnected())
            bIsInternetConnected = true;
        else
            bIsInternetConnected = false;*/


    }
  /*  public void GetActiveNetworkInfo() {

    }*/

    public boolean IsInterNetConnected()
    {
        try
        {
            objConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            objNetworkInfo = objConnectivityManager.getActiveNetworkInfo();
            bIsInternetConnected = objNetworkInfo != null && objNetworkInfo.isAvailable() && objNetworkInfo.isConnected();
            return bIsInternetConnected;


        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            //Log.v("connectivity", e.toString());
        }
        return bIsInternetConnected;
    }
}
