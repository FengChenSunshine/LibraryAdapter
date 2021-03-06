package com.duanlu.adapter;

import android.os.Build;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.duanlu.adapter.listener.ListenerWithPosition;
import com.duanlu.adapter.listener.OnCheckedChangeListener;
import com.duanlu.adapter.listener.OnClickInterceptor;
import com.duanlu.adapter.listener.OnClickListener;
import com.duanlu.adapter.listener.OnLongClickListener;

/********************************
 * @name RvBaseViewHolder
 * @author 段露
 * @createDate 2019/3/6  16:52.
 * @updateDate 2019/3/6  16:52.
 * @version V1.0.0
 * @describe RecyclerView.ViewHolder基类.
 ********************************/
public class RvBaseViewHolder extends RecyclerView.ViewHolder {

    private RvBaseAdapter mAdapter;
    private View mConvertView;
    private SparseArray<View> mViews;
    private OnClickInterceptor mOnClickInterceptor;

    public RvBaseViewHolder(RvBaseAdapter adapter, View itemView) {
        this(adapter, itemView, null);
    }

    public RvBaseViewHolder(RvBaseAdapter adapter, View itemView, OnClickInterceptor onClickInterceptor) {
        super(itemView);
        this.mAdapter = adapter;
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
        this.mOnClickInterceptor = onClickInterceptor;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId) {
        View view = this.mViews.get(viewId);
        if (view == null) {
            view = this.mConvertView.findViewById(viewId);
            this.mViews.put(viewId, view);
        }
        return (T) view;
    }

    public RvBaseViewHolder setText(@IdRes int viewId, @StringRes int strResId) {
        TextView tv = this.getView(viewId);
        tv.setText(strResId);
        return this;
    }

    public RvBaseViewHolder setText(@IdRes int viewId, CharSequence charSequence) {
        TextView tv = this.getView(viewId);
        tv.setText(TextUtils.isEmpty(charSequence) ? "" : charSequence);
        return this;
    }

    public RvBaseViewHolder setText(@IdRes int viewId, Spannable spannable, TextView.BufferType bufferType) {
        TextView tv = this.getView(viewId);
        if (TextUtils.isEmpty(spannable)) {
            tv.setText("");
        } else {
            tv.setText(spannable, bufferType);
        }
        return this;
    }

    public RvBaseViewHolder setTextColor(@IdRes int viewId, @ColorRes int textColor) {
        TextView tv = this.getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(tv.getContext().getResources().getColor(textColor, null));
        } else {
            tv.setTextColor(tv.getContext().getResources().getColor(textColor));
        }
        return this;
    }

    public RvBaseViewHolder setTextIntColor(@IdRes int viewId, @ColorInt int textIntColor) {
        TextView tv = this.getView(viewId);
        tv.setTextColor(textIntColor);
        return this;
    }

    public String getText(@IdRes int viewId) {
        TextView tv = this.getView(viewId);
        return tv.getText().toString();
    }

    public RvBaseViewHolder setRatingBarRating(@IdRes int ratingBarId, float rating) {
        RatingBar rb = this.getView(ratingBarId);
        rb.setRating(rating);
        return this;
    }

    public RvBaseViewHolder setViewEnabled(@IdRes int viewId, boolean isEnabled) {
        View view = this.getView(viewId);
        view.setEnabled(isEnabled);
        return this;
    }

    public RvBaseViewHolder setViewClickable(@IdRes int viewId, boolean isClickable) {
        View view = this.getView(viewId);
        view.setClickable(isClickable);
        return this;
    }

    public RvBaseViewHolder setViewSelected(@IdRes int viewId, boolean isSelected) {
        View view = this.getView(viewId);
        view.setSelected(isSelected);
        return this;
    }

    public RvBaseViewHolder setCompoundButtonCheck(@IdRes int compoundButtonId, boolean isCheck) {
        CompoundButton cb = this.getView(compoundButtonId);
        cb.setChecked(isCheck);
        return this;
    }

    public RvBaseViewHolder setCompoundButtonCheckedChangeListener(OnCheckedChangeListener checkListener, @IdRes int checkBoxId) {
        CompoundButton cb = this.getView(checkBoxId);
        ListenerWithPosition listener = new ListenerWithPosition(this);
        cb.setOnCheckedChangeListener(listener);
        listener.setCheckChangeListener(checkListener);
        return this;
    }

    public RvBaseViewHolder setOnClickListener(OnClickListener clickListener, @IdRes int... viewIds) {
        ListenerWithPosition listener = new ListenerWithPosition(this, this.mOnClickInterceptor);
        listener.setOnClickListener(clickListener);

        View v;
        for (int id : viewIds) {
            v = this.getView(id);
            v.setOnClickListener(listener);
        }
        return this;
    }

    public RvBaseViewHolder setOnLongClickListener(OnLongClickListener longClickListener, @IdRes int... viewIds) {
        ListenerWithPosition listener = new ListenerWithPosition(this, this.mOnClickInterceptor);
        listener.setOnLongClickListener(longClickListener);

        View v;
        for (int id : viewIds) {
            v = this.getView(id);
            v.setOnLongClickListener(listener);
        }
        return this;
    }

    public RvBaseViewHolder setViewVisibility(@IdRes int viewId, int visibility) {
        View view = this.getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public int getViewVisibility(@IdRes int viewId) {
        View view = this.getView(viewId);
        return view.getVisibility();
    }

    public RvBaseViewHolder setViewBackgroundColor(@IdRes int viewId, @ColorRes int colorResId) {
        View view = this.getView(viewId);
        if (Build.VERSION.SDK_INT < 23) {
            view.setBackgroundColor(view.getResources().getColor(colorResId));
        } else {
            view.setBackgroundColor(view.getResources().getColor(colorResId, null));
        }
        return this;
    }

    public RvBaseViewHolder setViewBackgroundDrawableRes(@IdRes int viewId, @DrawableRes int drawableResId) {
        View view = this.getView(viewId);
        view.setBackgroundResource(drawableResId);
        return this;
    }

    public int getContentAdapterPosition() {
        return getAdapterPosition() - mAdapter.getHeaderItemCount() - mAdapter.getEmptyItemCount();
    }

}