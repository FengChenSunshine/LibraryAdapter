package com.duanlu.adapter.listener;

import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name OnCheckedChangeListener.
 * @author 段露.
 * @createDate 2020/5/11 14:26.
 * @updateDate 2020/5/11 14:26.
 * @version V1.0.0
 * @describe CheckBox、RadioButton选中状态改变监听器.
 ********************************/
public interface OnCheckedChangeListener {

    void onCheckedChanged(@NonNull RvBaseViewHolder holder, @NonNull CompoundButton view, boolean checked, int adapterPosition);
}
