package com.example.chen.listviewtest.sixth_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.chen.listviewtest.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SixthActivity extends AppCompatActivity {


    public static final String TAG = "log_sixthActivity";
    @BindView(R.id.sixth_activity_btn)
    Button sixthActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        Log.d("hhhhh","userid : SixthActivity " + UserManager.sUserId);
        ButterKnife.bind(this);
        Log.d(TAG,"onCreate~");
        //OneFragment oneFragment = new OneFragment();
        //Bundle bundle = new Bundle();
        //bundle.putString(oneFragment.EXTRA_MSG,"hello");
        //
        //oneFragment.setArguments(bundle);
        //FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        //
        //ft.add(R.id.sixth_container,oneFragment);
        //ft.commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart~");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart~");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume~");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause~");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop~");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy~");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG,"onRestoreInstanceState~");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState~");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG,"onNewIntent~");
    }

    @OnClick(R.id.sixth_activity_btn)
    public void onViewClicked() {
        startActivity(new Intent(this,SixthActivity.class));
    }
}
