package com.example.appleservicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 继承IntentService
 * 1、不需要执行stopSelf方法关闭服务
 * 2、线程同步，一般用于执行单个任务
 */
public class ExtendIntentService extends IntentService {
    private static final String TAG = "ExtendIntentService";

    public ExtendIntentService() {
        super("ExtendIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, intent.getStringExtra("name") + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ExtendIntentService onDestroy");
    }
}
