package com.yueshen.routerapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class RouterApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifeCallback());

    }

    //Activity 生命周期回调
    private class ActivityLifeCallback implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            L(activity.getLocalClassName() + "onActivityCreated");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            L(activity.getLocalClassName() + "onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            L(activity.getLocalClassName() + "onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            L(activity.getLocalClassName() + "onActivityPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            L(activity.getLocalClassName() + "onActivityStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            L(activity.getLocalClassName() + "onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            L(activity.getLocalClassName() + "onActivityDestroyed");
        }
    }

    private void L(String msg) {
        Log.e(getClass().getSimpleName(), msg);
    }
}
