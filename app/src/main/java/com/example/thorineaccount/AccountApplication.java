package com.example.thorineaccount;

import android.app.Application;

import com.example.thorineaccount.db.AccountDao;

public class AccountApplication extends Application {
    private AccountDao mDatabaseManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseManager = new AccountDao(this);
    }
    public AccountDao getmDatabaseManager(){
        return mDatabaseManager;
    }
}
