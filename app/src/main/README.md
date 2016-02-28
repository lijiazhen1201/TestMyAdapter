# TestMyAdapter

## ListView和GridView的万能适配器

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

---

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
	
	

