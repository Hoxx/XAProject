package com.yueshen.xrouter;

import android.app.Application;

import com.yueshen.xrouter.activity.XRouterActivityAction;

public class XRouter {

    private XRouter() {
    }

    public static void register(Application app) {
        if (app == null) return;
        app.registerActivityLifecycleCallbacks(XRouterActivityAction.getInstance());
    }





}
