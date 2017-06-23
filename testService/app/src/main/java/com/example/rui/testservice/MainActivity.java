package com.example.rui.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "TestService";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
            Log.w(TAG, "onServiceConnected" + " @threadid=" + Thread.currentThread().getId());
            Log.w(TAG, "factorial of 4 is " + binder.calcFactorial(4));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setClickListener(R.id.btn_start);
        setClickListener(R.id.btn_stop);
        setClickListener(R.id.btn_bind);
        setClickListener(R.id.btn_unbind);
        setClickListener(R.id.btn_foo);
        setClickListener(R.id.btn_baz);

        Log.w(TAG, "onCreate" + " @threadid=" + Thread.currentThread().getId());
    }

    @Override
    protected void onDestroy() {
        Log.w(TAG, "MainActivity.onDestroy");
        super.onDestroy();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent intent = new Intent(this, MyStartedService.class);
                startService(intent);
                break;
            case R.id.btn_stop:
                Intent stopIntent = new Intent(this, MyStartedService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind:
                Intent bindIntent = new Intent(this, MyBoundService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                break;
            case R.id.btn_foo:
                MyIntentService.startActionFoo(this, "", "");
                break;
            case R.id.btn_baz:
                MyIntentService.startActionBaz(this, "", "");
                break;
            default:
                break;
        }
    }

    private void setClickListener(int id) {
        ((Button)findViewById(id)).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
