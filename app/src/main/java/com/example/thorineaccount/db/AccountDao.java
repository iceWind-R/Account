package com.example.thorineaccount.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thorineaccount.entity.AccountCategory;
import com.example.thorineaccount.entity.AccountItem;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private DatabaseHelper helper;
    private SQLiteDatabase db;
    public AccountDao(Context context){
        //创建数据库
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    //收入类型
    public List<AccountCategory> getIncomeType(){
        List<AccountCategory> result = new ArrayList<AccountCategory>();
        String sql = "select id,category,icon from AccountIncomeType";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            int icon = cursor.getInt(cursor.getColumnIndex("icon"));
            AccountCategory c = new AccountCategory(id,category,icon);
            result.add(c);
        }
        cursor.close();
        return result;
    }

    //支出类型
    public List<AccountCategory> getOutlayType(){
        ArrayList<AccountCategory> result = new ArrayList<AccountCategory>();
        String sql = "select id,category,icon from AccountOutlayType";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            int icon = cursor.getInt(cursor.getColumnIndex("icon"));
            AccountCategory c = new AccountCategory(id,category,icon);
            result.add(c);
        }
        cursor.close();
        return result;
    }

    public List<AccountItem> getIncomeList(){
        ArrayList<AccountItem> result = new ArrayList<>();
        Cursor cursor = db.query("AccountIncome",null,null,null,null,null,null);
        while (cursor.moveToNext()){//依次读取，将每次读取的对象加入集合中
            AccountItem item = new AccountItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            item.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
            item.setDate(cursor.getString(cursor.getColumnIndex("date")));
            item.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    //支出类型
    public List<AccountItem> getOutlayList(){
        ArrayList<AccountItem> result = new ArrayList<AccountItem>();
        String sql = "select id,category,money,remark,date from AccountOutlay";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            AccountItem item = new AccountItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            item.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
            item.setDate(cursor.getString(cursor.getColumnIndex("date")));
            item.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            result.add(item);
        }
        cursor.close();
        return result;
    }

    public void addIncome(AccountItem item){
        db.beginTransaction();//开启事务
        try{
            db.execSQL("INSERT INTO AccountIncome(id,category,money,date,remark) VALUES(null,?,?,?,?)",new Object[]{item.getCategory(),item.getMoney(),item.getDate(), item.getRemark()});
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();//结束
        }
    }

    //添加支出
    public void addOutlay(AccountItem item) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO AccountOutlay(id,category,money,date,remark) VALUES(null,?,?,?,?)",
                    new Object[]{item.getCategory(), item.getMoney(),item.getDate(),item.getRemark()});

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    //删除收入
    public void deleteIncome(long id) {
        String sql = "delete from AccountIncome where id="+id;

        db.beginTransaction();
        try {
            db.execSQL(sql);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    //添加收入类型
    public void addIncomeCategory(String category,int icon) {
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO AccountIncomeType(id,category,icon) VALUES(null,?,?)",
                    new Object[]{category,icon});

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //添加支出3类型
    public void addOutlayCategory(String category,int icon) {
        db.beginTransaction();  //开始事务
        try {
            db.execSQL("INSERT INTO AccountOutlayType(id,category,icon) VALUES(null,?,?)",
                    new Object[]{category,icon});

            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }
}
