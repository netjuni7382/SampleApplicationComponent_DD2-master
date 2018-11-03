package com.begentgroup.sampleapplicationcomponent;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    public static final String ACTION_MOD_TEN_ZERO = "com.begentgroup.sampleapplicationcomponent.action.MOD_TEN_ZERO";

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static final String TAG = "MyService";

    boolean isRunning = false;
    int count = 0;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    Log.i(TAG, "count : " + count);
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count % 10 == 0) {
                        Intent intent = new Intent(ACTION_MOD_TEN_ZERO);
                        intent.putExtra("count", count);
//                        sendBroadcast(intent);
                        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                            @Override
                            public void onReceive(Context context, Intent intent) {
                                int code = getResultCode();
                                if (code == Activity.RESULT_CANCELED) {
                                    Toast.makeText(context, "activity not processed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, null, Activity.RESULT_CANCELED, null, null);
                    }
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(mReceiver, filter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i(TAG, "Screen Off");
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        int count = intent.getIntExtra("count", 0);
        this.count = count;
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        isRunning = false;

        unregisterReceiver(mReceiver);
    }
}
