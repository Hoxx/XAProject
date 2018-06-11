package com.yueshen.xrouter;

import android.content.Context;

public class XRouter {

    private volatile static XRouter instance;

    private static Context c;

    private XRouter() {
    }

    public static void initialize(Context context) {
        c = context.getApplicationContext();
        createInstance();
    }

    //创建单例
    private static void createInstance() {
        if (instance == null) {
            synchronized (XRouter.class) {
                if (instance == null) {
                    instance = new XRouter();
                }
            }
        }
    }

    //检查异常
    private void checkException() {
        if (instance == null)
            throw new NullPointerException("XRouter not used initialize method!");
    }

}
