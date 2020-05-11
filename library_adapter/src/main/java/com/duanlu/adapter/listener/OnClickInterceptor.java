package com.duanlu.adapter.listener;

import android.view.View;

import com.duanlu.adapter.RvBaseViewHolder;

/********************************
 * @name OnItemClickInterceptor.
 * @author 段露.
 * @createDate 2020/5/11 14:30.
 * @updateDate 2020/5/11 14:30.
 * @version V1.0.0
 * @describe {@link OnClickListener} {@link OnLongClickListener} 事件拦截器.
 ********************************/
public interface OnClickInterceptor {

    boolean onItemClickInterceptor(RvBaseViewHolder holder, int adapterPosition, View view);
}
