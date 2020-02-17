package com.example.thorineaccount.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thorineaccount.R;
import com.example.thorineaccount.activity.AccountEditActivity;
import com.example.thorineaccount.adapter.OutlayRecyclerViewAdapter;
import com.example.thorineaccount.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutlayFragment extends Fragment {

    private View mRootView;
    private RecyclerView mRecyclerView;

    public OutlayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_outlay, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        refresh();
        final Button addButton = mRootView.findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddOnClick();
            }
        });
    }

    protected void buttonAddOnClick() {
        Intent intent = new Intent(this.getActivity(), AccountEditActivity.class);
        intent.putExtra("isIncome",false);
        startActivity(intent);
    }

    private void refresh() {
        List<AccountItem> outlayAccountList = getTestData();
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(new OutlayRecyclerViewAdapter(this.getActivity(),outlayAccountList));
        TextView textViewOutlaySummary = mRootView.findViewById(R.id.textViewOutlaySummary);
        textViewOutlaySummary.setText("2000");
    }

    private List<AccountItem> getTestData() {
        List<AccountItem> result = new ArrayList<>();
        for(int i = 0;i<5;i++){
            AccountItem item = new AccountItem();
            item.setId(i);
            item.setCategory("食物");
            item.setMoney(100 * i);
            item.setDate("2020-2-8:" + i);
            result.add(item);
        }
        return result;
    }

}
