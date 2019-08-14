package com.example.chen.listviewtest.second;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * 下滑加载的滑动监听器
 */
public abstract class ScrollListener extends RecyclerView.OnScrollListener {

    private boolean isSlidingUp = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if(newState == RecyclerView.SCROLL_STATE_IDLE) {
            int lastVisionItemPosition = manager.findLastVisibleItemPosition();
            int itemCount = manager.getItemCount();

            if(lastVisionItemPosition == itemCount -1 && isSlidingUp) {
                loadMore();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingUp = dy > 0; // dy > 0说明在向上滑动
    }


    public abstract void loadMore() ;
}
