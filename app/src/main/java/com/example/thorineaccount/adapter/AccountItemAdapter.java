package com.example.thorineaccount.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thorineaccount.R;
import com.example.thorineaccount.entity.AccountItem;

import java.util.List;

public class AccountItemAdapter extends BaseAdapter {

    private List<AccountItem> mItems;
    private LayoutInflater mInflater;

    public AccountItemAdapter(List<AccountItem> mItems, Activity context) {
        this.mItems = mItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {//返回列表视图的长度
        return this.mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //第一个参数是布局文件
        View view = this.mInflater.inflate(R.layout.list_view_item,null);
        TextView tvCategory = (TextView)view.findViewById(R.id.textViewCategory);
        TextView tvRemark = (TextView)view.findViewById(R.id.textViewRemark);
        TextView tvMoney = (TextView)view.findViewById(R.id.textViewMoney);
        TextView tvDate = (TextView)view.findViewById(R.id.textViewDate);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageViewIcon);
        AccountItem item = mItems.get(position);
        tvCategory.setText(item.getCategory());
        tvRemark.setText(item.getRemark());
        tvMoney.setText(String.valueOf(item.getMoney()));
        tvDate.setText(item.getDate());
        int icon = R.drawable.baby_icon;
        if(icon>0){
            imageView.setImageResource(icon);//显示图标
        }
        return view;
    }
}
