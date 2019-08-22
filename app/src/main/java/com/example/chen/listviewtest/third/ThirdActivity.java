package com.example.chen.listviewtest.third;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.chen.listviewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThirdActivity extends AppCompatActivity {

    @BindView(R.id.third_act_start_service)
    Button mStartBtn;

    @BindView(R.id.third_act_stop_service)
    Button mStopBtn;

    @BindView(R.id.third_act_bind_service)
    Button mBindBtn;

    @BindView(R.id.third_act_unbind_service)
    Button mUnbindBtn;

    @BindView(R.id.third_start_intent_service)
    Button mStartIntentService;

    private MyService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.third_act_start_service)
    public void startService() {
        Intent service = new Intent(this, MyService.class);
        startService(service);
    }

    @OnClick(R.id.third_act_stop_service)
    public void stopService() {
        Intent service = new Intent(this, MyService.class);
        stopService(service);
    }

    @OnClick(R.id.third_act_bind_service)
    public void bindService() {
        Intent service = new Intent(this, MyService.class);
        bindService(service, mConnection, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.third_act_unbind_service)
    public void unbindService() {
        unbindService(mConnection);
    }

    @OnClick(R.id.third_start_intent_service)
    public void setStartIntentService() {
        Log.d("MyIntentService", "thread id is" + Thread.currentThread().getId());
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }


}
