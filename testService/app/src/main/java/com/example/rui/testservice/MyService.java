package com.example.rui.testservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Service run on the UI thread!!!
 */
public class MyService extends Service {

    private MyBinder mBinder = new MyBinder();

    public static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate" + " @threadid=" + Thread.currentThread().getId());

        Intent notiIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notiIntent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Title")
                .setContentText("this is content")
                .setSmallIcon(R.drawable.abc_ic_commit_search_api_mtrl_alpha)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, noti);
    }

    /**
     * If start and then bind service, you need to call both stop and unbind to let
     * service call the onDestroy() callback.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.d(TAG, "onBind");
        return mBinder;
    }

    class MyBinder extends Binder {

        public long calcFactorial(int n) {
            if (n <= 1) return 1;
            return n * calcFactorial(n-1);
        }
    }
}
