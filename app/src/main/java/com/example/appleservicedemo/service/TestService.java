package com.example.appleservicedemo.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.appleservicedemo.thread.ExtendThread;
import com.example.appleservicedemo.thread.SecondImpCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestService extends Service {
    private static final String TAG = "TestService";
    private Thread testThread;

    @Override
    public void onCreate() {
        super.onCreate();
        testThread = new ExtendThread("ExtendThread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
//        testThread.start();
        ExecutorService mExecutors = Executors.newFixedThreadPool(1);
        Callable mCallable = new SecondImpCallable("SecondImpCallableName");
        Future mFuture = mExecutors.submit(mCallable);
        try {
            Log.d(TAG, mFuture.get().toString());
        } catch (InterruptedException e) {
            Log.e(TAG, e.toString());
        } catch (ExecutionException e) {
            Log.e(TAG, e.toString());
        }
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
//        testThread.interrupt();
        super.onDestroy();
    }
}
