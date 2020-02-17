package com.example.thorineaccount.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.thorineaccount.R;
import com.example.thorineaccount.activity.AccountEditActivity;
import com.example.thorineaccount.adapter.AccountItemAdapter;
import com.example.thorineaccount.db.AccountDao;
import com.example.thorineaccount.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {

    View mRootView;

    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_income, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        Button buttonAdd = (Button)mRootView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonAddOnClick();
            }
        });
        ListView listView = (ListView)mRootView.findViewById(R.id.listView1);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(id);
                return true;
            }

        });
        refreshData();
    }

    protected void buttonAddOnClick() {
        Intent intent = new Intent(this.getActivity(),AccountEditActivity.class);
        intent.putExtra("isIncome",true);
        startActivityForResult(intent,1);//在AccountEditActivity中根据请求码进行判断
    }
    //刷新界面
    private void refreshData() {
        AccountDao dbManager = new AccountDao(getActivity());
        List<AccountItem> incomeAccountList = dbManager.getIncomeList();
        AccountItemAdapter adapter = new AccountItemAdapter(incomeAccountList,getActivity());
        ListView listView = mRootView.findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        TextView textViewIncomeSummary = (TextView) mRootView.findViewById(R.id.textViewIncomeSummary);
        textViewIncomeSummary.setText("10000");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("tinyaccount","onActivityResult");
        refreshData();
    }

    protected void deleteItem(final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(R.string.delete_confirm_title);
        builder.setMessage(R.string.delete_confirm_msg);

        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AccountDao dbManager = new AccountDao(getContext());
                dbManager.deleteIncome(id);
                refreshData();
            }
        });
        builder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private List<AccountItem> getTestData(){
        List<AccountItem> result = new ArrayList<AccountItem>();
        for(int i=0;i<5;i++){
            AccountItem item = new AccountItem();
            item.setId(i);
            item.setCategory("兼职收入");
            item.setMoney(i*100);
            item.setDate("2020-2-8:"+i);
            result.add(item);
        }
        return result;
    }
}
