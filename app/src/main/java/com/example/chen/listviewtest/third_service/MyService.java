package com.example.chen.listviewtest.third_service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.chen.listviewtest.R;

public class MyService extends Service {
    public static final String TAG  = "MyService";
    private final String CHANNEL_ID = "MY_CHANNEL_ID";
    private final String name = "channel_name";
    private final int IMPORTANCE = NotificationManager.IMPORTANCE_LOW;
    public MyService() {
    }

    private static void gethaha() {

    }
    DownloadBinder mDownloadBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG,"startDownload");
        }

        public int getProgess() {
            Log.d(TAG,"getProgress");
            return 0;
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, IMPORTANCE);
        channel.setDescription("hahah channel");
        channel.setLightColor(Color.BLUE);
        channel.enableVibration(true);
        channel.enableLights(true);
        channel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});

        NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        Intent intent = new Intent(this,ThirdActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is a content title")
                .setContentText("hello")
                .setWhen(SystemClock.currentThreadTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setChannelId(CHANNEL_ID)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return mDownloadBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}
