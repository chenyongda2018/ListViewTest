package com.example.chen.listviewtest.seventh_animator;

import android.animation.ValueAnimator;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.TextView;

import com.example.chen.listviewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeventhActivity extends AppCompatActivity {

    @BindView(R.id.animator_progress_1)
    TextView mProgress1;
    @BindView(R.id.seventh_img_1)
    ImageView mImageView1;

    final int mTranslationX = 500;
    @BindView(R.id.spinner)
    AppCompatSpinner spinner;
    @BindView(R.id.seventh_start_animation)
    Button startBtn;


    @OnClick(R.id.seventh_start_animation)
    public void startAnimation() {
        mImageView1.animate().start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);
        ButterKnife.bind(this);


        mImageView1.animate().translationX(mTranslationX)
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mProgress1.setText(String.valueOf(mImageView1.getTranslationX() / mTranslationX * 100) + "%");
                    }
                })
                .setDuration(5000);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case  0 :
                        mImageView1.animate().setInterpolator(new AccelerateDecelerateInterpolator());
                        break;
                    case  1 :
                        mImageView1.animate().setInterpolator(new LinearInterpolator());
                        break;
                    case  2 :
                        mImageView1.animate().setInterpolator(new AccelerateInterpolator());
                        break;
                    case  3 :
                        mImageView1.animate().setInterpolator(new DecelerateInterpolator());
                        break;
                    case  4 :
                        mImageView1.animate().setInterpolator(new AnticipateInterpolator());
                        break;
                    case  5 :
                        mImageView1.animate().setInterpolator(new OvershootInterpolator());
                        break;
                    case  6 :
                        mImageView1.animate().setInterpolator(new AnticipateOvershootInterpolator());
                        break;
                    case  7 :
                        mImageView1.animate().setInterpolator(new BounceInterpolator());
                        break;
                    case  8 :
                        mImageView1.animate().setInterpolator(new CycleInterpolator(0.5f));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
