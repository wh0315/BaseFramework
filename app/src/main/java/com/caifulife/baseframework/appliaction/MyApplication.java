package com.caifulife.baseframework.appliaction;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 皓 on 2017/8/29.
 */

public class MyApplication extends Application {

    private List<Activity>activities = new LinkedList<>();
    private static MyApplication instance ;
    private static Handler mHandler = null;
    private static int mainUITid;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        instance = this;
        mainUITid = android.os.Process.myTid();
        mHandler = new Handler();
    }

    /**
     * 单例模式
     *$author wh
     *created at 2017/8/29 11:13
     */
    public static MyApplication getInstance ()
    {
        if(instance == null)
        {
            instance = new MyApplication();
        }
        return instance ;
    }
    /**
     * 往栈中添加activity
     *$author wh
     *created at 2017/8/29 11:13
     */
    public void addActivity(Activity activity)
    {
        activities.add(activity);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    /**
     * 移除单个activity
     *$author wh
     *created at 2017/8/29 11:16
     */
    public void finishActivty (Activity activity)
    {
        activities.remove(activity);
        activity.finish();
    }
    /**
     * 退出整个APP应用
     *$author wh
     *created at 2017/8/29 11:20
     */
    public void exit ()
    {
        for (Activity a : activities) {
            if (!a.isDestroyed())
            {
                a.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainUITid() {
        return mainUITid;
    }



}
