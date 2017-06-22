package com.example.lol;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by 8 on 2017/4/25.
 */

public class editdialog extends DialogFragment implements View.OnClickListener {
    TextView TextView7,TextView8,TextView9,TextView10;
    Database db = new Database();
    android.widget.Button Button,Button1;
    AlarmManager alarmManager;
    int item=0;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
String g="";
    public static editdialog newInstance(int mm){
        editdialog f = new editdialog();
        Bundle bundle = new Bundle();
        bundle.putInt("item", mm);
        f.setArguments(bundle);
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit, container);
        TextView7=(TextView)view.findViewById(R.id.textView7);
        TextView8=(TextView)view.findViewById(R.id.textView8);
        TextView9=(TextView)view.findViewById(R.id.textView9);
        TextView10=(TextView)view.findViewById(R.id.textView10);
        ArrayList<String> content=(ArrayList<String>)db.requerycontentforid(getActivity(),getTag());
        getDialog().setTitle(content.get(5)+"日");
        TextView7.setText(content.get(0));
        TextView8.setText(content.get(5)+"日"+"\n"+content.get(1)+"\n"+"到"+"\n"+content.get(6)+"日"+"\n"+content.get(2));
        TextView9.setText(content.get(3));
        TextView10.setText(content.get(4));
        content=(ArrayList<String>)db.requerybutton(getActivity(),getTag());
        TextView10=(TextView)view.findViewById(R.id.textView15);
        for(int s=0;s<5;s++) {
            if (!content.get(s).equals(" ")) {
                g=g+content.get(s)+"\n";
            }
            }
        TextView10.setText(g);
        Button =(android.widget.Button)view.findViewById(R.id.button16);
        Button1 =(android.widget.Button)view.findViewById(R.id.button14);
        Button.setOnClickListener(this);
        Button1.setOnClickListener(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            item = bundle.getInt("item");
        }
        return view;
    }

    public void setAlerm(Date date, int eventid)
    {
        String S=sdf.format(date);
        Toast.makeText(getActivity(), "設置鬧鈴"+S, Toast.LENGTH_LONG).show();
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getActivity(),AlermReceiver.class);
        intent.putExtra("eventid", eventid);//將id值附加到intent中
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getActivity(),0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
    }
    public void cancelAlerm()
    {
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getActivity(),AlermReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getActivity(),0, intent, 0);
        alarmManager.cancel(pendingIntent);
    }
    public  void setAlarm()
    {
        cancelAlerm();
        Map<Date, Integer> map=db.findnearlyTime(getActivity());
        for(Date d:map.keySet())
        {
            if(d!=null)
            {
                setAlerm(d,map.get(d));
            }
            else
            {

            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button16:
                db.delete(getActivity(),getTag());
                setAlarm();
                if(item==0){
                CalendarView CalendarView = (CalendarView) getFragmentManager()
                        .findFragmentById(R.id.fragment);
                CalendarView.refresh();
                MainActivity ac = (MainActivity)getActivity();
                ac.refresh();}
                else{
                    searchresult ac = (searchresult)getActivity();
                    ac.refresh();
                }
                dismiss();
                break;
            case R.id.button14:
                if(item==0){
                newsdialog editNameDialog = newsdialog.newInstance(getTag());
                editNameDialog.show(getFragmentManager(), "editdialog");}
                else{
                    newsdialog editNameDialog = newsdialog.newInstance(getTag(),1);
                    editNameDialog.show(getFragmentManager(), "editdialog");
                }
                dismiss();
                break;
        }




    }
}
