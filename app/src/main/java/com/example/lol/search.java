package com.example.lol;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 8 on 2017/6/1.
 */

public class search extends DialogFragment {
    Button Button1;
    EditText EditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("搜尋");
        View view = inflater.inflate(R.layout.search,container);
        Button1=(Button)view.findViewById(R.id.button26);
        EditText=(EditText)view.findViewById(R.id.editText4);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),searchresult.class);
                intent.putExtra("edit",EditText.getText().toString());
                getActivity().startActivity(intent);
                dismiss();
            }
        });
        return view;
    }


}
