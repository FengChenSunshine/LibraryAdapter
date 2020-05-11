package com.duanlu.adapter.listener;

import android.view.View;

import androidx.annotation.NonNull;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name OnLongClickListener.
 * @author 段露.
 * @createDate 2020/5/11 14:22.
 * @updateDate 2020/5/11 14:22.
 * @version V1.0.0
 * @describe 长按事件监听器.
 ********************************/
public interface OnLongClickListener {

    boolean onLongClick(@NonNull RvBaseViewHolder holder, @NonNull View view, int adapterPosition);
}
