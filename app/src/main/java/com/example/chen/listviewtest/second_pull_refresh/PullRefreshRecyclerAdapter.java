package com.example.chen.listviewtest.second_pull_refresh;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chen.listviewtest.R;

import java.util.List;

public class PullRefreshRecyclerAdapter extends RecyclerView.Adapter {

    private List<String> mDatas;

    private final int ITEM_TYPE = 1; //普通item的类型

    private final int FOOT_TYPE = 2; //底部item布局的类型

    public final int LOADING = 1;   //状态 : 正在加载

    public final int LOADING_COMPLETE = 2;  //状态 : 加载完成

    public final int LOADIND_END = 3;      //状态 : 没有更多数据

    private int loadState = LOADING_COMPLETE;  //由我们上滑加载时改变的变量,默认加载完成


    public PullRefreshRecyclerAdapter(List<String> datas) {
        mDatas = datas;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ITEM_TYPE) {

            View view = inflater.inflate(R.layout.second_layout_item, parent, false);
            return new NormalVH(view);
        } else if (viewType == FOOT_TYPE) {
            View view = inflater.inflate(R.layout.second_item_footer_view, parent, false);
            return new FooterVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalVH) {

            NormalVH vh = (NormalVH) holder;
            vh.mTextView.setText(mDatas.get(position));

        } else if(holder instanceof  FooterVH) {

            FooterVH vh = (FooterVH)holder;
            switch (loadState) {
                case LOADING :
                    vh.mProgressBar.setVisibility(View.VISIBLE);
                    vh.loadindgTv.setVisibility(View.VISIBLE);
                    vh.loadingEnd.setVisibility(View.GONE);
                    break;
                case LOADING_COMPLETE :
                    vh.mProgressBar.setVisibility(View.INVISIBLE);
                    vh.loadindgTv.setVisibility(View.INVISIBLE);
                    vh.loadingEnd.setVisibility(View.GONE);
                    break;
                case LOADIND_END:
                    vh.mProgressBar.setVisibility(View.GONE);
                    vh.loadindgTv.setVisibility(View.GONE);
                    vh.loadingEnd.setVisibility(View.VISIBLE);
                    break;

                default:break;
            }
        }
    }

    class NormalVH extends RecyclerView.ViewHolder {

        TextView mTextView;

        public NormalVH(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.second_item_normal_text);
        }


    }

    class FooterVH extends RecyclerView.ViewHolder {

        ProgressBar mProgressBar;
        TextView loadindgTv;
        TextView loadingEnd;

        public FooterVH(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.second_item_footer_progress_bar);
            loadindgTv = (TextView) itemView.findViewById(R.id.second_item_footer_loading);
            loadingEnd = (TextView) itemView.findViewById(R.id.second_item_footer_load_end);
        }


    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) return FOOT_TYPE;
        return ITEM_TYPE;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }

    public int getLoadState() {
        return loadState;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}
