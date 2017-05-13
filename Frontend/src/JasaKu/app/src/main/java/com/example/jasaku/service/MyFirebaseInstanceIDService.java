package com.example.jasaku.service;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by light on 13/05/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG ="MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: "+refreshedToken);
        Toast.makeText(getApplicationContext(),"Refreshed Token: "+refreshedToken,Toast.LENGTH_LONG).show();

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken){

    }
}
