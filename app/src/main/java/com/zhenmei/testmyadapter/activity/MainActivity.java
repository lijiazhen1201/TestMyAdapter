package com.zhenmei.testmyadapter.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.zhenmei.testmyadapter.R;
import com.zhenmei.testmyadapter.adapter.BaseViewHolder;
import com.zhenmei.testmyadapter.adapter.CommonAdapter;
import com.zhenmei.testmyadapter.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private ListView mListView;
    private List<User> list;
    private MyAdapter adapter;

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User("张三" + i, 20 + i);
            list.add(user);
        }
        adapter = new MyAdapter(this, list, R.layout.layout_item);
        mListView.setAdapter(adapter);
    }

    private class MyAdapter extends CommonAdapter<User> {

        public MyAdapter(Context mContext, List<User> list, int layoutId) {
            super(mContext, list, layoutId);
        }

        @Override
        public void init(BaseViewHolder baseViewHolder, User user) {
            /**
             * 初始化控件，设置
             */
            TextView tvName = baseViewHolder.getView(R.id.tv_name);
            TextView tvAge = baseViewHolder.getView(R.id.tv_age);
            tvName.setText(user.getName());
            tvAge.setText(user.getAge()+"");
        }
    }


}
