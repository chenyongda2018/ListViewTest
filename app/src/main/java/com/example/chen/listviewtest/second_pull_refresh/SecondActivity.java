package com.example.chen.listviewtest.second_pull_refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chen.listviewtest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 上拉加载
 */
public class SecondActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwpLayout;
    RecyclerView mRecyclerView;
    PullRefreshRecyclerAdapter mRecyclerAdapter;
    List<String> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mSwpLayout = (SwipeRefreshLayout) findViewById(R.id.second_swipr_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.second_recycler_view);
        initData();// 加载初始数据

        mRecyclerAdapter  = new PullRefreshRecyclerAdapter(mDatas);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //下拉刷新
        mSwpLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                changeData();
                mSwpLayout.setRefreshing(false);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });

        mRecyclerView.addOnScrollListener(new ScrollListener() {
            @Override
            public void loadMore() {
                mRecyclerAdapter.setLoadState(mRecyclerAdapter.LOADING);

                if (mDatas.size() < 52) { //如果小于52条 代表没加载完
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                    mRecyclerAdapter.setLoadState(mRecyclerAdapter.LOADING_COMPLETE);
                                }
                            });
                        }
                    }, 3000);
                } else {
                    mRecyclerAdapter.setLoadState(mRecyclerAdapter.LOADIND_END);
                }
            }
        });



    }

    private void initData() {
        for (int i = 0; i < 26; i++) {
            mDatas.add(String.valueOf(i));
        }
    }
    private void changeData() {
        for (int i = 0; i <mDatas.size() ; i++) {

            mDatas.set(i,String.valueOf(i)+" 已更新" );
        }
    }
}
