package com.example.chen.listviewtest.fifth_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chen.listviewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FifthActivity extends AppCompatActivity {


    private IntentFilter mIntentFilter;

    private NetWorkReceiver mNetWorkReceiver;

    @BindView(R.id.fifth_send_broadcast)
    Button mSendBCBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        ButterKnife.bind(this);
        mSendBCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.chen.listviewtest.MY_BOADCAST");
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
            }
        });
        //mIntentFilter = new IntentFilter();
        //mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //mNetWorkReceiver = new NetWorkReceiver();
        //registerReceiver(mNetWorkReceiver,mIntentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(mNetWorkReceiver);
    }

    class NetWorkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changed ~", Toast.LENGTH_SHORT).show();
        }
    }
}
