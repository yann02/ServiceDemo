package com.example.appleservicedemo.thread;

import android.util.Log;

public class ImpRunnable implements Runnable {
    private static final String TAG = "ImpRunnable";
    private Thread thread;

    public ImpRunnable() {
        thread = new Thread(this, "ImpRunnable");
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 10; i++) {
            Log.d(TAG, TAG + i);
        }
    }
}
