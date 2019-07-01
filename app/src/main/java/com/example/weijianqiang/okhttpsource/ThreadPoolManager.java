package com.example.weijianqiang.okhttpsource;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by weijianqiang
 * On 2019/6/29
 * Description:
 */
public class ThreadPoolManager {

    private static final String TAG = "ThreadPoolManager";
    private static ThreadPoolManager threadPoolManager = null;
    private LinkedBlockingDeque<Runnable> linkedBlockingDeque = null;
    private ThreadPoolExecutor mThreadPoolExecutor = null;

    private Thread coreThread = new Thread(){
        Runnable runn = null;
        @Override
        public void run() {
            while (true){
                try {
                    runn = linkedBlockingDeque.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadPoolExecutor.execute(runn);
            }
        }
    };

    public static ThreadPoolManager getInstance(){
        if (threadPoolManager == null){
            synchronized (ThreadPoolManager.class){
                if (threadPoolManager == null){
                    threadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return threadPoolManager;
    }

    private ThreadPoolManager(){
        linkedBlockingDeque = new LinkedBlockingDeque<>(4);
        mThreadPoolExecutor = new ThreadPoolExecutor(5, 9, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                addTask(r);
            }
        });
        coreThread.start();
    }

    public void addTask(Runnable task){
        if (task != null){
            try {
                linkedBlockingDeque.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
