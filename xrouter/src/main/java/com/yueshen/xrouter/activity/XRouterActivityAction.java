package com.yueshen.xrouter.activity;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class XRouterActivityAction extends XRouterActivityLifeCallback {

    private static volatile XRouterActivityAction instance;
    //弱引用
    private WeakReference<Activity> weakReference;

    private XRouterActivityAction() {

    }

    //单例
    public static XRouterActivityAction getInstance() {
        if (instance == null) {
            synchronized (XRouterActivityAction.class) {
                if (instance == null) {
                    instance = new XRouterActivityAction();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCurrentActivity(Activity activity) {
        if (weakReference == null) {
            weakReference = new WeakReference<>(activity);
        }
    }



    //获取当前的Activity
    private Activity getCurrentActivity() {
        if (weakReference == null || weakReference.get() == null)
            throw new NullPointerException("XRouterActivityAction Not get current Activity");
        return weakReference.get();
    }


}
