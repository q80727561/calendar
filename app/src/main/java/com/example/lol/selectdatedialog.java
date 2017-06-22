package com.example.lol;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.Date;

/**
 * Created by 8 on 2017/4/27.
 */

public class selectdatedialog extends DialogFragment {
    CalendarView CalendarView;
    android.widget.Button Button;
    String s="";
    Date curDate=new Date(System.currentTimeMillis());
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.selectdate,container);
        CalendarView = (CalendarView) getFragmentManager()
                .findFragmentById(R.id.fragment2);
        CalendarView.calendarGrid.setOnItemClickListener(new DayItemClickListener());
        Button=(android.widget.Button)view.findViewById(R.id.button17);
        Button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!s.equals("")) {
                    newsdialog newsdialog;
                    if (getTag() == "22" || getTag() == "23")
                        newsdialog = (newsdialog) getFragmentManager().findFragmentByTag("newsdialog");
                    else
                        newsdialog = (newsdialog) getFragmentManager().findFragmentByTag("editdialog");
                    android.widget.Button button11 = (android.widget.Button) newsdialog.getView()
                            .findViewById(R.id.button11);
                    android.widget.Button button12 = (android.widget.Button) newsdialog.getView()
                            .findViewById(R.id.button12);
                    String b11 = button11.getText().toString();
                    String b12 = button12.getText().toString();
                    b11 = b11.replace("年", "").replace("月", "");
                    Integer s11 = Integer.parseInt(b11);
                    b12 = b12.replace("年", "").replace("月", "");
                    b11 = s.replace("年", "").replace("月", "");
                    Integer s1 = Integer.parseInt(b11);
                    Integer s12 = Integer.parseInt(b12);
                    if (getTag() == "23" || getTag() == "25") {
                        if (s1 < s11)
                            button11.setText(s);
                        button12.setText(s);
                    } else {
                        if (s1 > s12)
                            button12.setText(s);
                        button11.setText(s);
                    }
                    getFragmentManager().beginTransaction().remove(CalendarView).commit();
                    dismiss();
                }
            }
        });
         return  view;
    }

    class DayItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CalendarView.onItemClick2(view);
            s=CalendarView.date;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getFragmentManager().beginTransaction().remove(CalendarView).commit();
    }
}
