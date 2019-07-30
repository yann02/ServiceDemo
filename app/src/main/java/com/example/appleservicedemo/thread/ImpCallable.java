package com.example.appleservicedemo.thread;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ImpCallable<Integer> implements Callable {
    private static final String TAG = "ImpCallable";
    private Thread thread;
    public ImpCallable() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(this);
        thread = new Thread(futureTask,"ImpCallable");
    }

    public Thread getThread() {
        return  thread;
    }

    @Override
    public Integer call() {
        int i = 0;
        for (; i < 10; i++) {
            Log.d(TAG, TAG + i);
        }
        return null;
    }
}
