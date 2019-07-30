package com.example.appleservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appleservicedemo.service.ExtendIntentService;
import com.example.appleservicedemo.service.ImpOnBindService;
import com.example.appleservicedemo.service.TestService;
import com.example.appleservicedemo.thread.ExtendThread;
import com.example.appleservicedemo.thread.ImpCallable;
import com.example.appleservicedemo.thread.ImpRunnable;
import com.example.appleservicedemo.thread.SecondImpCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ExtendThread extendThread;
    private ImpRunnable impRunnable;
    private ImpCallable<Integer> impCallable;
    private int i;
    private ImpOnBindService mImpOnBindService;
    private boolean mBound;

    /**
     * 启动绑定服务传递的参数
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            ImpOnBindService.MyIBinder myIBinder = (ImpOnBindService.MyIBinder) iBinder;
            mImpOnBindService = myIBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
            mBound = false;
        }
    };

    private void bindService() {
        Intent intent = new Intent(this, ImpOnBindService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService() {
        unbindService(mServiceConnection);
        mBound = false;
    }

    public void print() {
        if (mBound) {
            mImpOnBindService.print();
        } else {
            Toast.makeText(this, "服务未绑定", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extendThread = new ExtendThread("ExtendThread");
        impRunnable = new ImpRunnable();
        impCallable = new ImpCallable<>();
    }

    public void onClick(View view) {
        Log.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.btn_start:
                //开启服务
//                startService(new Intent(this, TestService.class));
                //跳转到携程APP
//                Intent intent = getPackageManager().getLaunchIntentForPackage("ctrip.android.view");
//                startActivity(intent);
                //开启线程
//                startThread(impCallable.getThread());
                //开启多线程
//                executeThreadPool();
                Intent intent = new Intent(this, ExtendIntentService.class);
                intent.putExtra("name", "" + (i++));
                startService(intent);
                break;
            case R.id.btn_close:
                Log.e(TAG, "点了关闭");
                stopService(new Intent(this, TestService.class));
//                stopThread(impCallable.getThread());
                break;
            case R.id.btn_alive:
                Log.e(TAG, "点了检查");
//                checkThread(impCallable.getThread());
                break;
            case R.id.btn_start_bind:
                Log.e(TAG, "开启绑定服务");
                bindService();
                break;
            case R.id.btn_unbind_service:
                Log.e(TAG, "取消绑定服务");
                unBindService();
                break;
            case R.id.btn_execute_service:
                Log.e(TAG, "操作绑定服务");
                print();
                break;
            default:
        }
    }

    private void checkThread(Thread thread) {
        Log.d(TAG, thread.toString() + thread.isInterrupted());
    }

    private void stopThread(Thread thread) {
        thread.interrupt();
    }

    private void startThread(Thread thread) {
        thread.start();
    }

    private void executeThreadPool() {
        ExecutorService mExecutorService = Executors.newFixedThreadPool(5);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable mCallable = new SecondImpCallable(i + " ");
            Future future = mExecutorService.submit(mCallable);
            futures.add(future);
        }
        mExecutorService.shutdown();
        for (int j = 0; j < 5; j++) {
            try {
                Log.d(TAG, "线程执行结束" + futures.get(j).get().toString());
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            } catch (ExecutionException e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unBindService();
            mBound = false;
        }
    }
}
