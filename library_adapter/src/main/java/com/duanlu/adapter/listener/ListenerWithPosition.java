package com.duanlu.adapter.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name ListenerWithPosition
 * @author 段露
 * @createDate 2019/3/6  16:57.
 * @updateDate 2019/3/6  16:57.
 * @version V1.0.0
 * @describe RecyclerView视图内使用的一些监听器.
 ********************************/
public class ListenerWithPosition implements CompoundButton.OnCheckedChangeListener, View.OnLongClickListener, View.OnClickListener, TextWatcher {

    private RvBaseViewHolder mHolder;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private OnCheckedChangeListener mCheckChangeListener;
    private OnTextWatcher mOnTextChange;
    private OnClickInterceptor mOnClickInterceptor;

    public ListenerWithPosition(RvBaseViewHolder holder) {
        this.mHolder = holder;
    }

    public ListenerWithPosition(RvBaseViewHolder holder, OnClickInterceptor onClickInterceptor) {
        this.mHolder = holder;
        this.mOnClickInterceptor = onClickInterceptor;
    }

    public void onClick(View v) {
        if (this.mOnClickListener != null && !this.checkIsInterceptor(v)) {
            this.mOnClickListener.onClick(this.mHolder, v, getContentAdapterPosition());
        }
    }

    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public boolean onLongClick(View v) {
        return (this.mOnLongClickListener != null
                && !this.checkIsInterceptor(v))
                && this.mOnLongClickListener.onLongClick(this.mHolder, v, getContentAdapterPosition());
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (this.mCheckChangeListener != null) {
            this.mCheckChangeListener.onCheckedChanged(this.mHolder, buttonView, isChecked, getContentAdapterPosition());
        }
    }

    public void setCheckChangeListener(OnCheckedChangeListener mCheckChangeListener) {
        this.mCheckChangeListener = mCheckChangeListener;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (this.mOnTextChange != null) {
            this.mOnTextChange.beforeTextChanged(this.mHolder, getContentAdapterPosition(), s, start, count, after);
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (this.mOnTextChange != null) {
            this.mOnTextChange.onTextChanged(this.mHolder, getContentAdapterPosition(), s, start, before, count);
        }
    }

    public void afterTextChanged(Editable s) {
        if (this.mOnTextChange != null) {
            this.mOnTextChange.afterTextChanged(this.mHolder, getContentAdapterPosition(), s);
        }
    }

    public void addTextChangedListener(OnTextWatcher mOnTextChange) {
        this.mOnTextChange = mOnTextChange;
    }

    private boolean checkIsInterceptor(View v) {
        return null != this.mOnClickInterceptor
                && this.mOnClickInterceptor.onItemClickInterceptor(this.mHolder, getContentAdapterPosition(), v);
    }

    public int getContentAdapterPosition() {
        return mHolder.getContentAdapterPosition();
    }

}

