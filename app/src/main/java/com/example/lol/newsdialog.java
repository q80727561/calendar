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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class newsdialog extends DialogFragment implements View.OnClickListener{
	Date curDate=new Date(System.currentTimeMillis());
	Button Button1,Button2,Button3,Button11,Button12,Button13,Button14,Button23,Button21,Button20,Button22,Button24,Button25;
	android.widget.ImageView ImageView;
	TextView TextView1,TextView2;
	Database db = new Database();
	EditText EditText1,EditText2,EditText3;
	 ArrayList<String> content,buttons;
	String item,items,b1=" ",b2=" ",b3=" ",b4=" ",b5=" ";
	String time=null,time1=null,time2=null,time3=null,time4=null,time5=null,time6=null,time7=null,time8=null,time9=null;
	int number=0;
	ArrayAdapter<String> adapter;
	String[] array  = new String[] {
		"整點", "5分鐘", "10分鐘", "15分鐘", "20分鐘","25分鐘","30分鐘","45分鐘","1小時","2小時"};
	int a,b,c,d,e;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	Spinner spinner,spinner2,spinner3,spinner4,spinner5;
	AlarmManager alarmManager;
	public static newsdialog newInstance(String mm){
		newsdialog f = new newsdialog();
		Bundle bundle = new Bundle();
		bundle.putString("item", mm);
		f.setArguments(bundle);
		return f;
	}
	public static newsdialog newInstance(String mm,int s){
		newsdialog f = new newsdialog();
		Bundle bundle = new Bundle();
		bundle.putString("item", mm);
		bundle.putInt("items", s);
		f.setArguments(bundle);
		return f;
	}
	public static newsdialog newInstancee(String mm){
		newsdialog f = new newsdialog();
		Bundle bundle = new Bundle();
		bundle.putString("item", mm);
		f.setArguments(bundle);
		return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
	{
		getDialog().setTitle("新建行事曆");
		View view = inflater.inflate(R.layout.news, container);
		EditText1=(EditText)view.findViewById(R.id.editText1);
		EditText2=(EditText)view.findViewById(R.id.editText2);
		EditText3=(EditText)view.findViewById(R.id.editText3);
		TextView1= (TextView)view.findViewById(R.id.textView1);
		TextView2= (TextView)view.findViewById(R.id.textView2);
		Button1= (Button)view.findViewById(R.id.button1);
		Button2= (Button)view.findViewById(R.id.button2);
		Button3= (Button)view.findViewById(R.id.button3);
		Button11= (Button)view.findViewById(R.id.button11);
		Button12= (Button)view.findViewById(R.id.button12);
		Button13= (Button)view.findViewById(R.id.button13);
		Button14= (Button)view.findViewById(R.id.button);
		Button21= (Button)view.findViewById(R.id.button21);
		Button20= (Button)view.findViewById(R.id.button20);
		Button23= (Button)view.findViewById(R.id.button23);
		Button22= (Button)view.findViewById(R.id.button22);
		Button24= (Button)view.findViewById(R.id.button24);
		Button25= (Button)view.findViewById(R.id.button25);
		ImageView= (android.widget.ImageView)view.findViewById(R.id.imageView1);
		String string=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd");
		string=sdf.format(curDate);
		Button1.setOnClickListener(this);
		Button2.setOnClickListener(this);
		Button3.setOnClickListener(this);
		Button13.setOnClickListener(this);
		Button14.setOnClickListener(this);
		Button12.setOnClickListener(this);
		Button11.setOnClickListener(this);
		Button20.setOnClickListener(this);
		Button23.setOnClickListener(this);
		Button21.setOnClickListener(this);
		Button22.setOnClickListener(this);
		Button24.setOnClickListener(this);
		Button25.setOnClickListener(this);
		Bundle bundleh = getArguments();
		if (bundleh != null) {
			items = bundleh.getString("item");
			number = bundleh.getInt("items",0);
		}
		if(items==""){
		Button11.setText(string);
		Button12.setText(string);}
		else{
			Button11.setText(items);
			Button12.setText(items);
		}
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner = (Spinner) view.findViewById(R.id.spinner);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		spinner4 = (Spinner) view.findViewById(R.id.spinner4);
		spinner5 = (Spinner) view.findViewById(R.id.spinner5);
		 adapter = this.createArrayAdapter();
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter);
		spinner.setAdapter(adapter);
		spinner3.setAdapter(adapter);
		spinner4.setAdapter(adapter);
		spinner5.setAdapter(adapter);
		if(getTag()!="newsdialog"){
			Bundle bundle = getArguments();
			if (bundle != null) {
				item = bundle.getString("item");
			}
			getDialog().setTitle("編輯行事曆");
			content=(ArrayList<String>)db.requerycontentforid(getActivity(),item);
			EditText1.setText(content.get(0));
			Button1.setText(content.get(1));
			Button2.setText(content.get(2));
			EditText2.setText(content.get(3));
			EditText3.setText(content.get(4));
			Button11.setText(content.get(5));
			Button12.setText((content.get(6)));
			buttons=(ArrayList<String>)db.requerybutton(getActivity(),item);
			if(!content.get(5).equalsIgnoreCase(content.get(6)))
			{
				TextView1.setText("從");
				TextView2.setVisibility(View.VISIBLE);
				Button11.setVisibility(View.VISIBLE);
				Button12.setVisibility(View.VISIBLE);
				Button13.setVisibility(View.VISIBLE);
				Button13.setText(Button2.getText().toString());
				ImageView.setVisibility(View.GONE);
				Button3.setVisibility(View.GONE);
				Button2.setVisibility(View.GONE);
			}
			for(int s=0;s<5;s++) {
				if (!buttons.get(s).equals(" ")) {
					switch (s) {
						case 0:
						alertVISIBLE(Button23, spinner2);
						a = 1;
							buttonalert(spinner2,0);
						break;
						case 1:
							alertVISIBLE(Button20, spinner);
							b = 1;
							buttonalert(spinner,1);
							break;
						case 2:
							alertVISIBLE(Button22, spinner3);
							c = 1;
							buttonalert(spinner3,2);
							break;
						case 3:
							alertVISIBLE(Button24, spinner4);
							d = 1;
							buttonalert(spinner4,3);
							break;
						case 4:
							alertVISIBLE(Button25, spinner5);
							e = 1;
							buttonalert(spinner5,4);
							break;


					}
				}
			}
		}
		return view;
	}
	private void buttonalert(Spinner spinner, int a){
		for (int i = 0; i < 10; i++) {
			if (array[i].equals(buttons.get(a))) {
				spinner.setSelection(i, true);
				break;
			}
		}
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



	private ArrayAdapter<String> createArrayAdapter() {


		return new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, array);
	}
	@Override
	public void onClick(View v) {
switch (v.getId()){
	case R.id.button1:
		if(getTag()!="newsdialog") {
			timedialog editNameDialog = timedialog.newInstance("1");
			editNameDialog.show(getFragmentManager(), "22");
		}
		else{
			timedialog editNameDialog1 = timedialog.newInstance("1");
			editNameDialog1.show(getFragmentManager(), "kk");
		}

		break;
	case R.id.button2:
		if(getTag()!="newsdialog") {
			timedialog editNameDialog = timedialog.newInstance("2");
			editNameDialog.show(getFragmentManager(), "22");
		}
		else{
			timedialog editNameDialog1 = timedialog.newInstance("2");
			editNameDialog1.show(getFragmentManager(), "kk");
		}
		break;
	    case R.id.button3:
		TextView1.setText("從");
		TextView2.setVisibility(View.VISIBLE);
		Button11.setVisibility(View.VISIBLE);
		Button12.setVisibility(View.VISIBLE);
		Button13.setVisibility(View.VISIBLE);
		Button13.setText(Button2.getText().toString());
		ImageView.setVisibility(View.GONE);
		Button3.setVisibility(View.GONE);
		Button2.setVisibility(View.GONE);
		break;
	case R.id.button13:
		if(getTag()!="newsdialog") {
			timedialog editNameDialog = timedialog.newInstance("3");
			editNameDialog.show(getFragmentManager(), "22");
		}
		else{
			timedialog editNameDialog1 = timedialog.newInstance("3");
			editNameDialog1.show(getFragmentManager(), "kk");
		}
		break;
	case R.id.button:
		String s11 = Button11.getText().toString();
		String s12 = Button12.getText().toString();
		if(EditText1.getText().toString().equals("")||EditText2.getText().toString().equals("")||EditText3.getText().toString().equals("")||Button1.getText().toString().equals("--:--")||Button2.getText().toString().equals("--:--")){
			Toast.makeText(getActivity(), "資料不齊全", Toast.LENGTH_SHORT).show();
		}else {
			if(a==1)
			{
				b1=spinner2.getSelectedItem().toString();
				setalert(b1);
			}
			if( b==1){
				b2=spinner.getSelectedItem().toString();
				setalert(b2);
			}
			if(c==1){
				b3=spinner3.getSelectedItem().toString();
				setalert(b3);
			}
			if(d==1){
				b4=spinner4.getSelectedItem().toString();
				setalert(b4);
			}
			if(e==1){
				b5=spinner5.getSelectedItem().toString();
				setalert(b5);
			}
			if (getTag() == "newsdialog") {
				db.addData(getActivity(), EditText1.getText().toString(), Button1.getText().toString(), Button2.getText().toString(), EditText2.getText().toString(), EditText3.getText().toString(), s11, s12);
               db.addbutton(getActivity(),b1,b2,b3,b4,b5);
				db.addalert(getActivity(),time,time1,time2,time3,time4,time5,time6,time7,time8,time9);
				setAlarm();
			} else{
				db.edit(getActivity(), EditText1.getText().toString(), Button1.getText().toString(), Button2.getText().toString(), EditText2.getText().toString(), EditText3.getText().toString(), s11, s12, item);
				db.editbutton(getActivity(),b1,b2,b3,b4,b5,item);
                db.updataalert(getActivity(),time,time1,time2,time3,time4,time5,time6,time7,time8,time9,item);
                setAlarm();
		}
		if(number==0){
			CalendarView CalendarView = (CalendarView) getFragmentManager()
				.findFragmentById(R.id.fragment);
		CalendarView.refresh();
        MainActivity ac = (MainActivity) this.getActivity();
        ac.refresh();}
			else{
			searchresult ac = (searchresult)getActivity();
			ac.refresh();
		}
        dismiss();}
		break;
	case R.id.button11:
		if(getTag()!="newsdialog") {
			selectdatedialog selectdatedialog = new selectdatedialog();
			selectdatedialog.show(getFragmentManager(),"24");
		}else{
		selectdatedialog selectdatedialog = new selectdatedialog();
		selectdatedialog.show(getFragmentManager(),"22");}
		break;
	case R.id.button12:
		if(getTag()!="newsdialog") {
		selectdatedialog selectdatedialog1 = new selectdatedialog();
		selectdatedialog1.show(getFragmentManager(),"25");}
		else{
		selectdatedialog selectdatedialog = new selectdatedialog();
		selectdatedialog.show(getFragmentManager(),"23");}
		break;
	case R.id.button21:
               if(a==0)
			   {
				   alertVISIBLE(Button23,spinner2);
                    a=1;
			   }else if( b==0){
				   alertVISIBLE(Button20,spinner);
				   b=1;
			   }else if(c==0){
				   alertVISIBLE(Button22,spinner3);
				   c=1;
			   }else if(d==0){
				   alertVISIBLE(Button24,spinner4);
				   d=1;
			   }else if(e==0){
				   alertVISIBLE(Button25,spinner5);
				   e=1;
			   }

		break;
	case R.id.button23:
		alert(Button23,spinner2);
		a=0;
		break;
	case R.id.button20:
		alert(Button20,spinner);
		b=0;
		break;
	case R.id.button22:
		alert(Button22,spinner3);
		c=0;
		break;
	case R.id.button24:
		alert(Button24,spinner4);
		d=0;
		break;
	case R.id.button25:
		alert(Button25,spinner5);
		e=0;
		break;
}
	}
private  void alert(Button Button, Spinner Spinner){
	Button.setVisibility(View.GONE);
	Spinner.setVisibility(View.GONE);
	Spinner.setAdapter(adapter);
}
	private  void alertVISIBLE(Button Button, Spinner Spinner){
		Button.setVisibility(View.VISIBLE);
		Spinner.setVisibility(View.VISIBLE);
	}
public void setalert(String s){
	String a5 =Button11.getText().toString().replace("年","").replace("月","");
	a5=MainActivity.buffer(a5);
	String a6=Button1.getText().toString();
	Date date1;
	int min;
   if(a6.substring(0,2).equals("下午")){
	   a5=a5+" "+a6.replace("下午","");
	   date1=Stringtodate(a5);
	   if(date1.getHours()!=12){
	   min=date1.getHours()+12;
	   date1.setHours(min);}

   }else
   {
	a5=a5+" "+a6.replace("上午","");
	date1=Stringtodate(a5);
	   if(date1.getHours()==12){
		   min=date1.getHours()-12;
		   date1.setHours(min);}
   }
	for (int i = 0; i < 10; i++) {
		if (array[i].equals(s)){
           switch (i){
			   case 0:
				   time=sdf.format(date1);
			   break;
			   case 1:
				   min=date1.getMinutes()-5;
                     date1.setMinutes(min);
				   time1=sdf.format(date1);
				   break;
			   case 2:
				   min=date1.getMinutes()-10;
				   date1.setMinutes(min);
				   time2=sdf.format(date1);
				   break;
			   case 3:
				   date1=Stringtodate(a5);
				   min=date1.getMinutes()-15;
				   date1.setMinutes(min);
				   time3=sdf.format(min);
				   break;
			   case 4:
				   min=date1.getMinutes()-20;
				   date1.setMinutes(min);
				   time4=sdf.format(date1);
				   break;
			   case 5:
				   min=date1.getMinutes()-25;
				   date1.setMinutes(min);
				   time5=sdf.format(min);
				   break;
			   case 6:
				   min=date1.getMinutes()-30;
				   date1.setMinutes(min);
				   time6=sdf.format(date1);
				   break;
			   case 7:
				   min=date1.getMinutes()-45;
				   date1.setMinutes(min);
				   time7=sdf.format(date1);
				   break;
			   case 8:
				   min=date1.getHours()-1;
				   date1.setHours(min);
				   time8=sdf.format(date1);
				   break;
			   case 9:
				   min=date1.getHours()-2;
				   date1.setHours(min);
				   time9=sdf.format(date1);
				   break;
		   }
			break;
		}
	}


}
public static Date Stringtodate(String s){
	Date date=null;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try
	{
		date=sdf.parse(s);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}


	return  date;

}
    public static Date Stringtodate1(String s){
        Date date=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月ddahh:mm");
        try
        {
            date=sdf.parse(s);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return  date;

    }



}


