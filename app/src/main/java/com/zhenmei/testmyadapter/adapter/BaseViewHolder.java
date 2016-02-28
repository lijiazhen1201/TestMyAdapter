package com.zhenmei.testmyadapter.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通用ViewHolder
 */
public class BaseViewHolder {

    /**
     * 用来缓存view
     */
    private SparseArray<View> views;

    /**
     * 复用的布局
     */
    private View convertView;

    /**
     * item的位置
     */
    private int position;

    /**
     * 构造器
     *
     * @param mInflater
     * @param parent
     * @param resId
     * @param position
     */
    private BaseViewHolder(LayoutInflater mInflater, ViewGroup parent, int resId, int position) {
        this.views = new SparseArray<View>();
        this.position = position;
        convertView = mInflater.inflate(resId, parent, false);
        convertView.setTag(this);
    }

    /**
     * 获取BaseViewHolder对象
     *
     * @param mInflater
     * @param convertView
     * @param parent
     * @param resId
     * @param position
     * @return
     */
    public static BaseViewHolder get(LayoutInflater mInflater, View convertView, ViewGroup parent, int resId,
                                     int position) {
        if (convertView == null) {
            return new BaseViewHolder(mInflater, parent, resId, position);
        }
        return (BaseViewHolder) convertView.getTag();
    }

    /**
     * 通过id获取控件，如果没有从布局中找，然后存到views中
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 返回convertView
     *
     * @return
     */
    public View getConvertView() {
        return convertView;
    }

    /**
     * 返回position
     *
     * @return
     */
    public int getPosition() {
        return this.position;
    }
}

