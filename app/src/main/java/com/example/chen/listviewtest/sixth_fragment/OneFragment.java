package com.example.chen.listviewtest.sixth_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.chen.listviewtest.R;

public class OneFragment extends Fragment {

    public  final String EXTRA_MSG = "onefragment_extra_msg";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sixth_one_fragment,container,false);

        String msg = getArguments().getString(EXTRA_MSG);
        EditText et = (EditText) view.findViewById(R.id.sixth_one_fragment_et);
        et.setText(msg);
        view.findViewById(R.id.one_fragment_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.sixth_container,new TwoFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}
