package com.example.lol;


import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener, DrawerLayout.DrawerListener, NavigationView.OnNavigationItemSelectedListener {
	ListView lv;
	android.widget.Button Button, Button18;
	CalendarView CalendarView;
	Database db = new Database();
	ArrayAdapter<String> tempAd;
	android.widget.TextView TextView, TextView1;
	ArrayList<String> idd, iee;
	private DrawerLayout mDrawerLayout;
	View header;
	NavigationView navigationView;
	Date curDate = new Date(System.currentTimeMillis());
	private ListView mDrawerList;
	private int recodes=1;
	String string ;
	public static String buffer(String s) {
		StringBuffer buf = new StringBuffer();
		buf.append(s);
		buf.insert(4, "-");
		buf.insert(7, "-");
		return buf.toString();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		mDrawerLayout.setDrawerListener(this);
		navigationView = (NavigationView) findViewById(R.id.nav_view);
		header = navigationView.getHeaderView(0);
		mDrawerList = (ListView) header.findViewById(R.id.l);
		TextView1 = (android.widget.TextView) header.findViewById(R.id.textView18);
		navigationView.setNavigationItemSelectedListener(this);
		Intent it = getIntent();
		if (it.getExtras() != null) {
			NotificationManager notif = (NotificationManager)
					getSystemService(NOTIFICATION_SERVICE);
			notif.cancel(it.getExtras().getInt("NOTIFICATION_ID"));
			String id = it.getExtras().getString("id");
			if (!id.equals("")) {
				editdialog editDialog = new editdialog();
				editDialog.show(getFragmentManager(), id);
			}
		}
		CalendarView = (CalendarView) getFragmentManager()
				.findFragmentById(R.id.fragment);
		CalendarView.calendarGrid.setOnItemClickListener(new DayItemClickListener());
		lv = (ListView) findViewById(R.id.listView);
		TextView = (android.widget.TextView) findViewById(R.id.textView11);
		Button = (android.widget.Button) findViewById(R.id.button15);
		Button18 = (android.widget.Button) findViewById(R.id.button18);
		lv.setOnItemClickListener(this);
		mDrawerList.setOnItemClickListener(this);
		Button.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//設置當前日期為今天
						newsdialog editNameDialog = newsdialog.newInstancee(TextView.getText().toString());
						editNameDialog.show(getFragmentManager(), "newsdialog");
					}

				}
		);
		Button18.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mDrawerLayout.openDrawer(Gravity.LEFT);
						string = null;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
						string = sdf.format(curDate);
						TextView1.setText(string+"行程");
						final ArrayList<String> weekcontent = (ArrayList<String>) db.requery(MainActivity.this, string);
						ArrayAdapter<String> tempAdd;
						tempAdd = new ArrayAdapter<String>(MainActivity.this,
								android.R.layout.simple_spinner_item,
								weekcontent);
						mDrawerList.setAdapter(tempAdd);
						iee = db.requeryid(MainActivity.this, string);
					}
				}
		);
	}

	@Override
	public void onDrawerSlide(View drawerView, float slideOffset) {
	}

	@Override
	public void onDrawerOpened(View drawerView) {
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
	}

	@Override
	public void onDrawerClosed(View drawerView) {
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	}

	@Override
	public void onDrawerStateChanged(int newState) {
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.add) {
			newsdialog editNameDialog = newsdialog.newInstancee(string);
			editNameDialog.show(getFragmentManager(), "newsdialog");
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else if (id == R.id.Inquire) {
			search searchDialog =new search();
			searchDialog.show(getFragmentManager(), "search");
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else if (id == R.id.goback) {
			goBack();
		}
		item.setCheckable(false);
		return true;
	}

	class DayItemClickListener implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			CalendarView.onItemClick2(view);
			String s = CalendarView.date;
			final ArrayList<String> weekcontent = (ArrayList<String>) db.requery(MainActivity.this, s);
			tempAd = new ArrayAdapter<String>(MainActivity.this,
					android.R.layout.simple_spinner_item,
					weekcontent);
			idd = db.requeryid(MainActivity.this, s);
			lv.setAdapter(tempAd);
			TextView.setText(s);
		}
	}

	public void refresh() {
		if (tempAd != null)
			tempAd.clear();
		lv.setAdapter(tempAd);
		TextView.setText("");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (parent.getId() == R.id.listView) {
			editdialog editDialog = new editdialog();
			editDialog.show(getFragmentManager(), idd.get(position));
		} else {
			editdialog editDialog = new editdialog();
			editDialog.show(getFragmentManager(), iee.get(position));
			mDrawerLayout.closeDrawer(GravityCompat.START);
		}

		//idd.get(position);
		//Toast.makeText(getActivity(), "你選擇的是" +idd.get(position), Toast.LENGTH_SHORT).show();
	}

	//===========================判斷返回方法==================
	public void goBack() {

		if (recodes == 1) {
			Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
			recodes=0;
		} else {
			System.exit(0);
		}
	}
}

	
	

