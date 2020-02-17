package com.example.thorineaccount.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thorineaccount.R;
import com.example.thorineaccount.entity.AccountItem;

import java.util.List;

public class OutlayRecyclerViewAdapter extends RecyclerView.Adapter<OutlayRecyclerViewAdapter.NormalTextViewHolder> {

    private List<AccountItem> mItems;
    private final LayoutInflater mLayoutInflater;

    public OutlayRecyclerViewAdapter(Activity context,List<AccountItem> mItems) {
        mLayoutInflater=LayoutInflater.from(context);
        this.mItems = mItems;
    }
    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override//列表视图上显示数据
    public void onBindViewHolder(@NonNull NormalTextViewHolder holder, int position) {
        AccountItem item = this.mItems.get(position);
        holder.tvCategory.setText(item.getCategory());
        holder.tvRemark.setText(item.getRemark());
        holder.tvMoney.setText(String.valueOf(item.getMoney()));
        holder.tvDate.setText(item.getDate());
        int icon=R.drawable.book_icon;
        if (icon>0)
            holder.imageView.setImageResource(icon);
    }

    @Override
    public int getItemCount() {//返回列表的大小
        return mItems==null?0:mItems.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvRemark;
        TextView tvMoney;
        TextView tvDate;
        ImageView imageView;
        public NormalTextViewHolder (@NonNull View itemView) {
            super(itemView);
            tvCategory= (TextView) itemView.findViewById(R.id.textViewCategory);
            tvRemark = (TextView)itemView.findViewById(R.id.textViewRemark);
            tvMoney = (TextView)itemView.findViewById(R.id.textViewMoney);
            tvDate = (TextView)itemView.findViewById(R.id.textViewDate);
            imageView = (ImageView)itemView.findViewById(R.id.imageViewIcon);
        }
    }
}
