package com.example.thorineaccount.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thorineaccount.R;
import com.example.thorineaccount.db.AccountDao;
import com.example.thorineaccount.entity.AccountCategory;
import com.example.thorineaccount.entity.AccountItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountEditActivity extends AppCompatActivity {

    private List<AccountCategory> categoryList;
    private TextView textViewSelectedType;
    private EditText editTextMoney;
    private EditText editTextRemark;
    private boolean isIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_edit);
        isIncome = this.getIntent().getBooleanExtra("isIncome",true);//如果没获取到isIncome，默认值为true
        textViewSelectedType = findViewById(R.id.textViewSelectedType);
        editTextMoney = (EditText)this.findViewById(R.id.editTextMoney);
        editTextRemark = (EditText)this.findViewById(R.id.editTextRemark);
        if (isIncome){
            textViewSelectedType.setText("工资");
        }else {
            textViewSelectedType.setText("交通");
        }
        editTextMoney.setText("100");
        initView();

        Button buttonOK = (Button)this.findViewById(R.id.buttonOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKOnClick();
            }
        });
        editTextMoney.requestFocus();
    }

    private void buttonOKOnClick() {
        AccountItem item = new AccountItem();
        //从输入框中获取数据
        item.setCategory(textViewSelectedType.getText().toString());
        item.setMoney(Double.parseDouble(editTextMoney.getText().toString()));
        item.setRemark(editTextRemark.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        item.setDate(sdf.format(new Date()));
        AccountDao dbManager  = new AccountDao(this);
        if(isIncome){
            dbManager.addIncome(item);
        }else {
            dbManager.addOutlay(item);
        }
        this.setResult(1);
        this.finish();
    }

    private void initView() {
        AccountDao dbManager = new AccountDao(this);
        if (isIncome)
            categoryList = dbManager.getIncomeType();
        else
            categoryList = dbManager.getOutlayType();

        GridView gridView = (GridView) this.findViewById(R.id.gridView1);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,categoryList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridViewOnItemClick(position);
            }
        });
    }

    private List<AccountCategory> getTestDataOutlay() {
        categoryList = new ArrayList<>();
        categoryList.add(new AccountCategory(1,"交通",R.drawable.traffic_icon));
        categoryList.add(new AccountCategory(2,"食物",R.drawable.breakfast_icon));
        categoryList.add(new AccountCategory(3,"图书",R.drawable.book_icon));
        categoryList.add(new AccountCategory(4,"电影",R.drawable.film_icon));
        return categoryList;
    }

    private List<AccountCategory> getTestDataIncome() {
        categoryList = new ArrayList<>();
        categoryList.add(new AccountCategory(1,"工资",R.drawable.fund_icon));
        categoryList.add(new AccountCategory(2,"奖金",R.drawable.insurance_icon));
        categoryList.add(new AccountCategory(3,"兼职收入",R.drawable.baby_icon));
        return categoryList;
    }

    private void gridViewOnItemClick(int position) {
        textViewSelectedType.setText(categoryList.get(position).toString());
    }
}
