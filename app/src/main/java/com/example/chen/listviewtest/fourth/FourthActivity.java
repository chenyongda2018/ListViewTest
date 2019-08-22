package com.example.chen.listviewtest.fourth;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chen.listviewtest.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class FourthActivity extends AppCompatActivity {


    public static final String TAG = "FourthActivity";
    private OkHttpClient mClient;

    @BindView(R.id.four_get_btn)
    Button mGetBtn;

    @BindView(R.id.four_post_btn)
    Button mPostBtn;


    @BindView(R.id.four_response_tv)
    TextView mResponse;
    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET :
                    String response =(String) msg.obj;
                    mResponse.setText(response);
                    break;
                default:break;
                
            }
        }
    };
    private static final int GET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        ButterKnife.bind(this);
        mClient = new OkHttpClient.Builder().build();

        mGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    private Message msg;

                    @Override
                    public void run() {
                        try {
                            String response = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                            Log.d(TAG, response);
                            msg = Message.obtain();
                            msg.what =GET;
                            msg.obj = response;
                            mHandler.sendMessage(msg);
                        } catch (IOException e) {
                            return;
                        }
                    }
                }).start();
            }
        });


        mPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }


    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = mClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
