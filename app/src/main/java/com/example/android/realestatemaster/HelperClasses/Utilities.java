package com.example.android.realestatemaster.HelperClasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by 100043392 on 27-Oct-18.
 */

public class Utilities {
    public static boolean isWifiEnabled(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {

            return true;
        }
        else{
            return false;
        }  }
        public static void showToast(Context context, String message){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
}
