package com.hxx.xaproject.mm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasicFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(inflaterLayoutId(), container, false);
    }

    public <V extends View> V F(int id) {
        return getView().findViewById(id);
    }

    public abstract int inflaterLayoutId();

}
