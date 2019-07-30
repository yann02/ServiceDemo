package com.example.appleservicedemo.thread;

import android.util.Log;

public class ExtendThread extends Thread {
    private static final String TAG = "ExtendThread";

    public ExtendThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 10; i++) {
            Log.d(TAG, TAG + i);
        }
    }
}
