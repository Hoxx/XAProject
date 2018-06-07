package com.hxx.xaproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AFragment extends Fragment implements View.OnClickListener {

    Button btn_f_a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_a, container, false);
        btn_f_a = view.findViewById(R.id.btn_f_a);
        btn_f_a.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "点击AFragment", Toast.LENGTH_LONG).show();
    }

}
