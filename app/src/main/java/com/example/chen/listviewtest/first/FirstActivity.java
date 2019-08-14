package com.example.chen.listviewtest.first;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.chen.listviewtest.R;
import com.example.chen.listviewtest.bean.Images;

public class FirstActivity extends AppCompatActivity {

    boolean mTouchEvent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ListView listView = findViewById(R.id.activity_first_list_view);
        ImageAdapter adapter = new ImageAdapter(this,0, Images.imageUrls);
        View headerView = getLayoutInflater().inflate(R.layout.header_view,listView,false);
        listView.addHeaderView(headerView);
        listView.addFooterView(headerView);
        listView.setAdapter(adapter);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTouchEvent = super.onTouchEvent(event);
        Log.d("First touch event", mTouchEvent+"");
        return mTouchEvent;
    }


}
