package com.duanlu.adapter.listener;

import android.text.Editable;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name OnTextWatcher.
 * @author 段露.
 * @createDate 2020/5/11 14:28.
 * @updateDate 2020/5/11 14:28.
 * @version V1.0.0
 * @describe TextView TextWatcher.
 ********************************/
public interface OnTextWatcher {

    void beforeTextChanged(RvBaseViewHolder holder, int adapterPosition, CharSequence s, int start, int count, int after);

    void onTextChanged(RvBaseViewHolder holder, int adapterPosition, CharSequence s, int start, int before, int count);

    void afterTextChanged(RvBaseViewHolder holder, int adapterPosition, Editable s);
}
