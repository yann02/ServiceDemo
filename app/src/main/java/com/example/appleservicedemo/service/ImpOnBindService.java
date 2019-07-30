package com.example.appleservicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ImpOnBindService extends Service {
    private static final String TAG = "ImpOnBindService";
    private IBinder myIBinder;

    @Override
    public void onCreate() {
        myIBinder=new MyIBinder();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myIBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public class MyIBinder extends Binder {
        public ImpOnBindService getService() {
            return ImpOnBindService.this;
        }
    }

    public void print() {
        Log.d(TAG, "hello ketty");
    }

}
