package com.example.lol;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by 8 on 2017/4/17.
 */


public class timedialog extends DialogFragment implements View.OnClickListener ,View.OnFocusChangeListener {
  int id;
    Date curDate;
    String item;
    View view;
    android.widget.Button Button30,Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,Button,Button10;
    EditText editText,editText1,editText2,editText3;
    public static timedialog newInstance(String mm){
        timedialog f = new timedialog();
        Bundle bundle = new Bundle();
        bundle.putString("item", mm);
        f.setArguments(bundle);
        return f;
    }
    public void onClick(View v){

        switch (v.getId()){
            case R.id.button:
                if(Button30.getText().toString().equals("上午"))
                Button30.setText("下午");
                else
                    Button30.setText("上午");
                break;
            case R.id.button0:
                timeselect(Button0);
                break;
            case R.id.button1:
                timeselect(Button1);
                break;
            case R.id.button2:
                timeselect(Button2);
                break;
            case R.id.button3:
                timeselect(Button3);
                break;
            case R.id.button4:
                timeselect(Button4);
                break;
            case R.id.button5:
                timeselect(Button5);
                break;
            case R.id.button6:
                timeselect(Button6);
                break;
            case R.id.button7:
                timeselect(Button7);
                break;
            case R.id.button8:
                timeselect(Button8);
                break;
            case R.id.button9:
                timeselect(Button9);
                break;
            case R.id.button10:
               curDate=new Date(System.currentTimeMillis());
                String string=null;
                String string11=null;
                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
                SimpleDateFormat sd1=new SimpleDateFormat("a");
                string=sdf.format(curDate);
                string11=sd1.format(curDate);
                editText.setText(string.substring(0,1));
                editText1.setText(string.substring(1,2));
                editText2.setText(string.substring(3,4));
                editText3.setText(string.substring(4,5));
                Button30.setText(string11);
                break;
            case R.id.button19:
                newsdialog newsdialog;
                if(getTag()!="kk")
                    newsdialog = (newsdialog) getFragmentManager()
                        .findFragmentByTag("editdialog");
                else
                    newsdialog = (newsdialog) getFragmentManager()
                            .findFragmentByTag("newsdialog");
                android.widget.Button button= (android.widget.Button) newsdialog.getView()
                        .findViewById(R.id.button1);
                android.widget.Button button2= (android.widget.Button) newsdialog.getView()
                        .findViewById(R.id.button2);
                android.widget.Button button3= (android.widget.Button) newsdialog.getView()
                        .findViewById(R.id.button13);
                android.widget.Button button11= (android.widget.Button) newsdialog.getView()
                        .findViewById(R.id.button11);
                android.widget.Button button12= (android.widget.Button) newsdialog.getView()
                        .findViewById(R.id.button12);
                String s,s1;
                if(editText.getText().toString().equals("0")&&editText1.getText().toString().equals("0")){
                    editText.setText("1");
                    editText1.setText("2");
                }
                String time=Button30.getText().toString()+editText.getText().toString()+editText1.getText().toString()+":"+editText2.getText().toString()+editText3.getText().toString();
                String date=button11.getText().toString();
                String date1=button12.getText().toString();
                s=button2.getText().toString();
                s1=button.getText().toString();
                Date date3,date4;
                if(item.equals("1")){
                    if(!s.equals("--:--")){
                        date1+=s;
                        date3=newsdialog.Stringtodate1(date1);
                        date+=time;
                        date4=newsdialog.Stringtodate1(date);
                        if(date4.getTime()>date3.getTime()){
                            button2.setText(time);
                            button3.setText(time);
                        }
                    }
                    button.setText(time);
                  }
                else {
                    if(!s1.equals("--:--")){
                        date+=s1;
                        date3=newsdialog.Stringtodate1(date);
                        date1+=time;
                        date4=newsdialog.Stringtodate1(date1);
                        if(date4.getTime()<date3.getTime()){
                            button.setText(time);
                        }
                        }
                    button2.setText(time);
                    button3.setText(time);
                    }
                dismiss();
                break;
        }
    }
    public void timeselect(android.widget.Button button){
        int t5=0;
        int tt= Integer.valueOf(button.getText().toString());
        String ed=editText.getText().toString();
        String ed1=editText1.getText().toString();
        String ed2=editText2.getText().toString();
        String ed3=editText3.getText().toString();
          switch (id) {
              case 0:
                  if(!editText1.getText().toString().equals(" "))
                      t5= Integer.valueOf(editText1.getText().toString());
                  if(t5>2){
                      if(tt<1){
                          editText.setText(button.getText().toString());
                          editText1.requestFocus();
                          if(ed1.equals(" "))
                              editText1.setText("0");
                          if(ed2.equals(" "))
                              editText2.setText("0");
                          if(ed3.equals(" "))
                              editText3.setText("0");
                      }
                  }else if(tt<2){
                  editText.setText(button.getText().toString());
                  editText1.requestFocus();
                      if(ed1.equals(" "))
                          editText1.setText("0");
                      if(ed2.equals(" "))
                          editText2.setText("0");
                      if(ed3.equals(" "))
                          editText3.setText("0");
                  }
                  break;
              case 1:
                  if(!editText.getText().toString().equals(" "))
                      t5= Integer.valueOf(editText.getText().toString());
                  if(t5==1){
                      if(tt<3){
                          editText1.setText(button.getText().toString());
                          editText2.requestFocus();
                          if(ed.equals(" "))
                              editText.setText("0");
                          if(ed2.equals(" "))
                              editText2.setText("0");
                          if(ed3.equals(" "))
                              editText3.setText("0");
                        }}
                  else{
                          editText1.setText(button.getText().toString());
                          editText2.requestFocus();
                      if(ed.equals(" "))
                          editText.setText("0");
                      if(ed2.equals(" "))
                          editText2.setText("0");
                      if(ed3.equals(" "))
                          editText3.setText("0");
                  }
                  break;
              case 2:
                  if(tt<6){
                  editText2.setText(button.getText().toString());
                  editText3.requestFocus();
                      if(ed.equals(" "))
                          editText.setText("0");
                      if(ed1.equals(" "))
                          editText1.setText("0");
                      if(ed3.equals(" "))
                          editText3.setText("0");
                  }
                  break;
              case 3:
                  if(tt<10){
                  editText3.setText(button.getText().toString());
                  editText.requestFocus();
                      if(ed.equals(" "))
                          editText.setText("0");
                      if(ed1.equals(" "))
                          editText1.setText("0");
                      if(ed2.equals(" "))
                          editText2.setText("0");
                  }
                  break;
          }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {    getDialog().setTitle("選擇時間");
        Bundle bundle = getArguments();
        if (bundle != null) {
            item = bundle.getString("item");
        }
        view = inflater.inflate(R.layout.time, container);
        Button30= (android.widget.Button)view.findViewById(R.id.button);
        Button0= (android.widget.Button)view.findViewById(R.id.button0);
        Button1= (android.widget.Button)view.findViewById(R.id.button1);
        Button2= (android.widget.Button)view.findViewById(R.id.button2);
        Button3= (android.widget.Button)view.findViewById(R.id.button3);
        Button4= (android.widget.Button)view.findViewById(R.id.button4);
        Button5= (android.widget.Button)view.findViewById(R.id.button5);
        Button6= (android.widget.Button)view.findViewById(R.id.button6);
        Button7= (android.widget.Button)view.findViewById(R.id.button7);
        Button8= (android.widget.Button)view.findViewById(R.id.button8);
        Button9= (android.widget.Button)view.findViewById(R.id.button9);
        Button10= (android.widget.Button)view.findViewById(R.id.button10);
        Button= (android.widget.Button)view.findViewById(R.id.button19);
         editText= (EditText)view.findViewById(R.id.editText);
        editText1= (EditText)view.findViewById(R.id.editText1);
        editText2= (EditText)view.findViewById(R.id.editText2);
        editText3= (EditText)view.findViewById(R.id.editText3);
        editText.setInputType(InputType.TYPE_NULL);
        editText1.setInputType(InputType.TYPE_NULL);
        editText2.setInputType(InputType.TYPE_NULL);
        editText3.setInputType(InputType.TYPE_NULL);
        editText.setText(" ");
        editText1.setText(" ");
        editText2.setText(" ");
        editText3.setText(" ");
        editText.setOnFocusChangeListener(this);
        editText1.setOnFocusChangeListener(this);
        editText2.setOnFocusChangeListener(this);
        editText3.setOnFocusChangeListener(this);
        Button30.setOnClickListener(this);
        Button0.setOnClickListener(this);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
        Button4.setOnClickListener(this);
        Button5.setOnClickListener(this);
        Button6.setOnClickListener(this);
        Button7.setOnClickListener(this);
        Button8.setOnClickListener(this);
        Button9.setOnClickListener(this);
        Button10.setOnClickListener(this);
        Button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.editText:
                    id = 0;
                    break;
                case R.id.editText1:
                    id = 1;
                    break;
                case R.id.editText2:
                    id = 2;
                    break;
                case R.id.editText3:
                    id = 3;
                    break;
            }
        }
    }
}

