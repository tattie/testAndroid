package com.example.rui.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyStartedService extends Service {

    public static final String TAG = "TestService";
    public MyStartedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.w(TAG, "onCreate in MyStartedService" + " @threadid=" + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "onStartCommand" + " @threadid=" + Thread.currentThread().getId());

        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 5;
                while (count > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.w(TAG, "Sleeping in MyStartedService ...");
                    count--;
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "onDestroy in MyStartedService" + " @threadid=" + Thread.currentThread().getId());
    }
}
