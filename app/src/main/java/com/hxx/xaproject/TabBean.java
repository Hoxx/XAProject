package com.hxx.xaproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class TabBean<F extends Fragment> {

    Class<F> fClass;

    public TabBean(Class<F> fClass) {
        this.fClass = fClass;
    }

    public F getFragment() {
        F f = null;
        try {
            Log.e("TAG", "fClass.getCanonicalName():" + fClass.getCanonicalName());
            Class<?> c = Class.forName(fClass.getCanonicalName());
            f = (F) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return f;
    }
}
