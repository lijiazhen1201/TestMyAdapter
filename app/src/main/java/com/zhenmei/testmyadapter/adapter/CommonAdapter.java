package com.zhenmei.testmyadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 *  ListView和GridView的万能适配器
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<T> list;

    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    /**
     * item的布局id
     */
    private int layoutId;

    /**
     * 构造器
     *
     * @param mContext
     * @param list
     * @param layoutId
     */
    public CommonAdapter(Context mContext, List<T> list, int layoutId) {
        super();
        this.mContext = mContext;
        this.list = list;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 设置数据源
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * item的数量
     *
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 获得item
     *
     * @param position
     * @return
     */
    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    /**
     * 获得id
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获得view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = BaseViewHolder.get(mInflater, convertView, parent, layoutId, position);
        init(baseViewHolder, list.get(position));
        return baseViewHolder.getConvertView();
    }

    /**
     * 重写此方法，在里面初始化item控件和设置item控件中的值
     *
     * @param baseViewHolder
     * @param t
     */
    public abstract void init(BaseViewHolder baseViewHolder, T t);
}
