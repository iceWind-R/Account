package com.example.thorineaccount.fragment;

import android.app.Activity;
import android.graphics.Color;

import java.util.ArrayList;

public class ChartManager {

    private Activity mContext;
    ArrayList<Integer> mOriginColors = new ArrayList<Integer>();//定义颜色
    public ChartManager(Activity ctx){
        this.mContext = ctx;
        mOriginColors.add(Color.parseColor("#59EA3A"));
        mOriginColors.add(Color.parseColor("#FFFA40"));
        mOriginColors.add(Color.parseColor("#E238A7"));
        mOriginColors.add(Color.parseColor("#"));
        mOriginColors.add(Color.parseColor("#"));
        mOriginColors.add(Color.parseColor("#"));
        mOriginColors.add(Color.parseColor("#"));
        mOriginColors.add(Color.parseColor("#"));
        mOriginColors.add(Color.parseColor("#"));

    }

    //生成饼图
//    public void showPoeChartAccount(PieChart pieChart, String date){
//        AccountApplication app = (AccountApplication)(mContext.getApplication());
//        AccountDao dbManager = app.getmDatabaseManager();
//
//        List<AccountItem> incomeAccountList = dbManager.getOutlayStaticsList(app.currentDateMonth);
//        ArrayList<String> xValues = new ArrayList<String>();
//        ArrayList<Entry> yValues = new ArrayList<Entry>();
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//        for(int i=0;i<incomeAccountList.size();i++){
//            xValues.add(incomeAccountList.get(i).getCategory());
////            yValues.add(new Entry(float) incomeAccountList.get(i).getMoney(),i,incomeAccountList.get(i).getCategory());
//            colors.add(mOriginColors.get(i%mOriginColors.size()));
//        }
//
//    }
}
