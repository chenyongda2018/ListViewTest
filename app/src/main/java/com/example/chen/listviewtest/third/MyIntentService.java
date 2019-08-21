package com.example.chen.listviewtest.third;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";



    public MyIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"thread id is"+ Thread.currentThread().getId());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }
}
