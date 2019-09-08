package com.example.chen.listviewtest.eight_handlerThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.listviewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EighthActivity extends Activity {

    @BindView(R.id.eight_msg_tv)
    TextView eightMsgTv;
    @BindView(R.id.eight_send_1)
    Button eightSend1;
    @BindView(R.id.eight_send_msg_2)
    Button eightSendMsg2;


    private final int UPDATE_MSG_1 = 1;
    private final int UPDATE_MSG_2 = 2;


    private Handler mMainHandler = new Handler();
    Handler mThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);
        ButterKnife.bind(this);

        HandlerThread handlerThread = new HandlerThread("chenyongda");
        handlerThread.start();
        mThreadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_MSG_1:
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                eightMsgTv.setText("我变了");
                            }
                        });
                        break;
                    case UPDATE_MSG_2:
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mMainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                eightMsgTv.setText("我又变了");
                            }
                        });
                        break;
                }
            }
        };

    }


    @OnClick({R.id.eight_send_1, R.id.eight_send_msg_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eight_send_1:
                Message msg1 = new Message();
                msg1.what = UPDATE_MSG_1;
                mThreadHandler.sendMessage(msg1);
                break;
            case R.id.eight_send_msg_2:
                Message msg2 = new Message();
                msg2.what = UPDATE_MSG_2;
                mThreadHandler.sendMessage(msg2);
                break;
        }
    }
}
