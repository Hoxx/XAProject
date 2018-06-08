package com.hxx.xaproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yueshen.xannotation.FindId;
import com.yueshen.xannotationprocessor.XAnnotationProcessor;

public class BFragment extends Fragment implements View.OnClickListener {

    @FindId(R.id.btn_f_b)
    Button btn_f_b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_b, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        XAnnotationProcessor.bind(this);
        btn_f_b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "点击BFragment", Toast.LENGTH_LONG).show();
    }

}
