package com.example.jasaku.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by light on 13/05/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    private static final String TAG="MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    }
}
