package com.hxx.xaproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class BFragment extends Fragment implements View.OnClickListener {

    Button btn_f_b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_b, container, false);
        btn_f_b = view.findViewById(R.id.btn_f_b);
        btn_f_b.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "点击BFragment", Toast.LENGTH_LONG).show();
    }
    
}
