package com.example.android.pendomoviz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.android.pendomoviz.NetworkConnection.App;
import com.example.android.pendomoviz.activity.MainActivity;

public class InternetConnector_Receiver extends BroadcastReceiver {

    public  InternetConnector_Receiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {


        try {

            boolean isVisible = App.isActivityVisible();// Check if

            Log.i("Activity is Visible ", "Is activity visible : " + isVisible);

            // If it is visible then trigger the task else do nothing
            if (isVisible == true) {

                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager
                        .getActiveNetworkInfo();

                // Check internet connection and load movies based on the user's preferences
                if (networkInfo != null && networkInfo.isConnected()) {

                    new MainActivity().loadMoviesFromSharedPreferences(true);

                } else {
                   Toast.makeText(context, "Internet connection disconnected", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
