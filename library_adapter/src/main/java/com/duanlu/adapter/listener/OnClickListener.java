package com.duanlu.adapter.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name OnClickListener.
 * @author 段露.
 * @createDate 2020/5/11 14:20.
 * @updateDate 2020/5/11 14:20.
 * @version V1.0.0
 * @describe 点击事件监听器.
 ********************************/
public interface OnClickListener {

    void onClick(@NonNull RvBaseViewHolder holder, @NonNull View view, int adapterPosition);
}
