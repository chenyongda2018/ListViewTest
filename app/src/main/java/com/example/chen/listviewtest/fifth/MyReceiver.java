package com.example.chen.listviewtest.fifth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.chen.listviewtest.MainActivity;

/**
 * 开机广播
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "收到了~", Toast.LENGTH_SHORT).show();
    }
}
