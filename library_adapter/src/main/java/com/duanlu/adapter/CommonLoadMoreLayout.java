package com.duanlu.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/********************************
 * @name CommonLoadMoreLayout
 * @author 段露
 * @createDate 2019/3/23  18:05.
 * @updateDate 2019/3/23  18:05.
 * @version V1.0.0
 * @describe 默认加载更多视图.
 ********************************/
public class CommonLoadMoreLayout extends LinearLayout implements LoadMoreHelp.OnLoadMoreStatusLayoutCallback, View.OnClickListener {

    private View mLoadingView;
    private TextView mTvMessage;

    private LoadMoreHelp mLoadMoreHelp;

    public CommonLoadMoreLayout(RecyclerView recyclerView, int pageNo, int pageSize, LoadMoreHelp.OnLoadMoreListener listener) {
        super(recyclerView.getContext());
        initView(recyclerView.getContext());
        bindRecyclerView(recyclerView, pageNo, pageSize, listener);
    }

    public CommonLoadMoreLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(this.getContext(), R.layout.adapter_common_load_more_layout, this);
        this.mLoadingView = this.findViewById(R.id.loading_view);
        this.mTvMessage = this.findViewById(R.id.tv_load_more_message);

        mTvMessage.setOnClickListener(this);
    }

    public void bindRecyclerView(RecyclerView recyclerView, int pageNo, int pageSize, LoadMoreHelp.OnLoadMoreListener listener) {
        mLoadMoreHelp = new LoadMoreHelp(recyclerView, pageNo, pageSize, listener);
        mLoadMoreHelp.setOnLoadMoreStatusLayoutCallback(this);
    }

    public void setTriggerLoadMoreSize(int triggerLoadMoreSize) {

    }

    public void setLoadMoreSuccess(boolean isEmptyData, boolean isLastPage) {
        if (null != mLoadMoreHelp) mLoadMoreHelp.setLoadMoreSuccess(isEmptyData, isLastPage);
    }

    public int getNextPageNo() {
        return null != mLoadMoreHelp ? mLoadMoreHelp.getNextPageNo() : 0;
    }

    public void setLoadMoreError() {
        if (null != mLoadMoreHelp) mLoadMoreHelp.setLoadMoreError();
    }

    public void resetNextPageNo() {
        if (null != mLoadMoreHelp) mLoadMoreHelp.resetNextPageNo();
    }

    @Override
    public void showLoadingLayout() {
        mLoadingView.setVisibility(View.VISIBLE);
        mTvMessage.setVisibility(View.VISIBLE);
        mTvMessage.setText("加载更多");
    }

    @Override
    public void showWaitLoadLayout() {
        mLoadingView.setVisibility(View.GONE);
        mTvMessage.setVisibility(View.VISIBLE);
        mTvMessage.setText("点击加载更多");
    }

    @Override
    public void showLoadErrorLayout() {
        mLoadingView.setVisibility(View.GONE);
        mTvMessage.setVisibility(View.VISIBLE);
        mTvMessage.setText("加载更多失败");
    }

    @Override
    public void showLastPageLayout() {
        mTvMessage.setText("没有更多内容了");
        mLoadingView.setVisibility(View.GONE);
        mTvMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int status = mLoadMoreHelp.getStatus();
        if (LoadMoreHelp.STATUS_WAIT_LOAD == status) {//不自动加载时点击加载.
            mLoadMoreHelp.requestLoadMore(LoadMoreHelp.STATUS_WAIT_LOAD);
        } else if (LoadMoreHelp.STATUS_ERROR == status) {//加载失败重新加载.
            mLoadMoreHelp.requestLoadMore(LoadMoreHelp.STATUS_ERROR);
        }
    }

}
