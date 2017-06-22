package com.example.lol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public  class Database {

	public static SQLiteDatabase openDatabase(Context fa) {
		SQLiteDatabase mydb = null;
		try {
			mydb = SQLiteDatabase.openDatabase("/data/data/com.example.lol/mydb",
					null,
					SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY
			);
			String sql = "CREATE TABLE IF NOT EXISTS " + "tb" +
					"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + //索引欄位
					"title VARCHAR(20), " +
					"time VARCHAR(30)," +
					"time1 VARCHAR(30)," +
					"place VARCHAR(30)," +
					"content VARCHAR(50)," +
					"date   VARCHAR(30)," +
					"date1 VARCHAR(30));";
			mydb.execSQL(sql);
			sql="create table if not exists alerttable(_id INTEGER PRIMARY KEY AUTOINCREMENT,alertone char(20),"+
					"alerttwo char(20),alertthree char(20),alertfour char(20), alertfive char(20),alertsix char(20),"
					+"alertseven char(20),alerteight char(20),alertnine char(20),alertten char(20));";
			mydb.execSQL(sql);
			sql="create table if not exists button(_id INTEGER PRIMARY KEY AUTOINCREMENT,first char(20),second char(20),"+
					"third char(20),fourth char(20),fifth char(20));";
			mydb.execSQL(sql);
		} catch (Exception e) {
			Log.d("error", e.toString() + "=============open===============");
			Toast.makeText(fa, "數據庫打開創建錯誤：" + e.toString(), Toast.LENGTH_LONG).show();
		}
		return mydb;
	}
	//關閉數據庫的方法
	public static void closeDatabase(SQLiteDatabase mydb, Context fa) {
		try {
			mydb.close();
		} catch (Exception e) {
			Log.d("error", e.toString() + "=============close===============");
			Toast.makeText(fa, "數據庫關閉失敗：" + e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	public static void addbutton(Context fa, String first, String second, String third, String fourth, String fifth){

		SQLiteDatabase mydb = null;
		String[] FROM1 = new String[]{"first", "second","third", "fourth", "fifth"};
		try {
			mydb = openDatabase(fa);

			ContentValues cv = new ContentValues(5);
			cv.put(FROM1[0], first);
			cv.put(FROM1[1], second);
			cv.put(FROM1[2], third);
			cv.put(FROM1[3], fourth);
			cv.put(FROM1[4], fifth);
			mydb.insert("button", null, cv);// 新增1筆記錄
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			Toast.makeText(fa, "添加失敗" + e.toString(), Toast.LENGTH_LONG).show();
		}
	}
	public static ArrayList<String> requerybutton(Context fa, String mm) {
		ArrayList<String> al = new ArrayList<String>();
		SQLiteDatabase mydb = null;
		Cursor cur = null;
		al.clear();
		try {
			mydb = openDatabase(fa);
			String sql = "SELECT first,second,third,fourth,fifth FROM " + "button" + " where _id =" + "'" + mm + "'";
			cur = mydb.rawQuery(sql, null);
			while (cur.moveToNext()) {
				al.add(cur.getString(0));
				al.add(cur.getString(1));
				al.add(cur.getString(2));
				al.add(cur.getString(3));
				al.add(cur.getString(4));
			}
			cur.close();
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
	//儲存新資料
	public static void addData(Context fa, String title, String time, String time1, String place, String content, String date, String date1) {
		SQLiteDatabase mydb = null;
		String[] FROM1 = new String[]{"title", "time","time1", "place", "content", "date","date1"};
		try {
			mydb = openDatabase(fa);

			ContentValues cv = new ContentValues(7);
			cv.put(FROM1[0], title);
			cv.put(FROM1[1], time);
			cv.put(FROM1[2], time1);
			cv.put(FROM1[3], place);
			cv.put(FROM1[4], content);
			cv.put(FROM1[5], date);
			cv.put(FROM1[6], date1);
			mydb.insert("tb", null, cv);
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			Toast.makeText(fa, "添加失敗" + e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public static ArrayList<String> requerycontentforid(Context fa, String mm) {
		ArrayList<String> al = new ArrayList<String>();
		SQLiteDatabase mydb = null;
		Cursor cur = null;
		al.clear();
		try {
			mydb = openDatabase(fa);
			String sql = "SELECT title,time,time1,place,content,date,date1 FROM " + "tb" + " where _id =" + "'" + mm + "'";
			cur = mydb.rawQuery(sql, null);

			while (cur.moveToNext()) {
				al.add(cur.getString(0));
				al.add(cur.getString(1));
				al.add(cur.getString(2));
				al.add(cur.getString(3));
				al.add(cur.getString(4));
				al.add(cur.getString(5));
				al.add(cur.getString(6));
			}
			cur.close();
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<String> requery(Context fa, String mm) {
		ArrayList<String> al = new ArrayList<String>();
		SQLiteDatabase mydb = null;
		Cursor cur = null;
		al.clear();
		try {
			mydb = openDatabase(fa);
			String sql = "SELECT title FROM " + "tb" + " where date <=" + "'" + mm + "'"+" and date1 >=" + "'" + mm + "'";
			cur = mydb.rawQuery(sql, null);

			while (cur.moveToNext()) {
				al.add(cur.getString(0));

			}
			cur.close();
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<String> requeryid(Context fa, String mm) {
		ArrayList<String> al = new ArrayList<String>();
		SQLiteDatabase mydb = null;
		Cursor cur = null;//宣告查詢
		al.clear();
		try {
			mydb = openDatabase(fa);
			String sql = "SELECT _id FROM " + "tb" + " where date <=" + "'" + mm + "'"+" and date1 >=" + "'" + mm + "'";
			cur = mydb.rawQuery(sql, null);

			while (cur.moveToNext()) {
				al.add(cur.getString(0));

			}
			cur.close();
			closeDatabase(mydb, fa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public static void delete(Context fa, String mm) {
		SQLiteDatabase mydb = null;
		try {

            mydb = openDatabase(fa);
            mydb.delete("tb", "_id=" + "'" + mm + "'", null);
            mydb.delete("alerttable", "_id=" + "'" + mm + "'", null);
            mydb.delete("button", "_id=" + "'" + mm + "'", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void edit(Context fa, String title, String time, String time1, String place, String content, String date, String date1, String id) {
		SQLiteDatabase mydb = null;
		String[] FROM1 = new String[]{"title", "time","time1", "place", "content", "date", "date1"};
		try {
			mydb = openDatabase(fa);
			ContentValues cv = new ContentValues(7);
			cv.put(FROM1[0], title);
			cv.put(FROM1[1], time);
			cv.put(FROM1[2], time1);
			cv.put(FROM1[3], place);
			cv.put(FROM1[4], content);
			cv.put(FROM1[5], date);
			cv.put(FROM1[6], date1);
			mydb.update("tb", cv, "_id=" + id, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void editbutton(Context fa, String first, String second, String third, String fourth, String fifth, String id) {
		SQLiteDatabase mydb = null;
		String[] FROM1 = new String[]{"first", "second","third", "fourth", "fifth"};
		try {
			mydb = openDatabase(fa);
			ContentValues cv = new ContentValues(5);
			cv.put(FROM1[0], first);
			cv.put(FROM1[1], second);
			cv.put(FROM1[2], third);
			cv.put(FROM1[3], fourth);
			cv.put(FROM1[4], fifth);
			mydb.update("button", cv, "_id=" + id, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void updataalert(Context fa, String alertone, String alerttwo , String alertthree , String alertfour , String alertfive , String alertsix ,
                             String alertseven , String alerteight , String alertnine , String alertten, String id) {

		SQLiteDatabase mydb = null;
		try {
			mydb = openDatabase(fa);
			ContentValues cv = new ContentValues(10);
			cv.put("alertone", alertone);
			cv.put("alerttwo", alerttwo);
			cv.put("alertthree", alertthree);
			cv.put("alertfour", alertfour);
			cv.put("alertfive", alertfive);
			cv.put("alertsix", alertsix);
			cv.put("alertseven", alertseven);
			cv.put("alerteight", alerteight);
			cv.put("alertnine", alertnine);
			cv.put("alertten", alertten);
			mydb.update("alerttable", cv, "_id=" + id, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public  void addalert(Context fa, String alertone, String alerttwo , String alertthree , String alertfour , String alertfive , String alertsix ,
                      String alertseven , String alerteight , String alertnine , String alertten) {

	SQLiteDatabase mydb = null;
	try {
		mydb = openDatabase(fa);
		ContentValues cv = new ContentValues(10);
		cv.put("alertone", alertone);
		cv.put("alerttwo", alerttwo);
		cv.put("alertthree", alertthree);
		cv.put("alertfour", alertfour);
		cv.put("alertfive", alertfive);
		cv.put("alertsix", alertsix);
		cv.put("alertseven", alertseven);
		cv.put("alerteight", alerteight);
		cv.put("alertnine", alertnine);
		cv.put("alertten", alertten);
		mydb.insert("alerttable", null, cv);
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public static Map<Date,Integer> findnearlyTime(Context fa)
	{
		Map<Date,Integer> map=new HashMap<Date,Integer>();
		Date date=null;
		Integer eventid=null;
		SQLiteDatabase sld=null;
		Cursor cur = null;
		try
		{

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date nowtime=new Date(System.currentTimeMillis());
			String nowstr=sdf.format(nowtime);
			sld=openDatabase(fa);
			String minDatestr=null;
			String sql="select min(alertone) from alerttable where alertone>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			sql="select min(alerttwo) from alerttable where alerttwo>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			//第三列時間的最小值
			sql="select min(alertthree) from alerttable where alertthree>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();

			sql="select min(alertfour) from alerttable where alertfour>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			//第五列時間的最小值
			sql="select min(alertfive) from alerttable where alertfive>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			//第六列時間的最小值
			sql="select min(alertsix) from alerttable where alertsix>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			//第七列時間的最小值
			sql="select min(alertseven) from alerttable where alertseven>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			sql="select min(alerteight) from alerttable where alerteight>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
			sql="select min(alertnine) from alerttable where alertnine>'"+nowstr+"'";
			cur=sld.rawQuery(sql,null);
			if(cur.moveToNext())
			{
				if(cur.getString(0)!=null&&minDatestr==null)
				{
					minDatestr=cur.getString(0);
				}
				else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
				{
					minDatestr=cur.getString(0);
				}
			}
			cur.close();
            sql="select min(alertten) from alerttable where alertten>'"+nowstr+"'";
            cur=sld.rawQuery(sql,null);
            if(cur.moveToNext())
            {
                if(cur.getString(0)!=null&&minDatestr==null)
                {
                    minDatestr=cur.getString(0);
                }
                else if(cur.getString(0)!=null&&cur.getString(0).compareTo(minDatestr)<0)
                {
                    minDatestr=cur.getString(0);
                }
            }
            cur.close();
			if(minDatestr!=null)
			{
				sql="select _id from alerttable where alertone='"+minDatestr+"' or " +
						"alerttwo='"+minDatestr+"' or alertthree='"+minDatestr+"' or " +
						"alertfour='"+minDatestr+"' or alertfive='"+minDatestr+"' or " +
						"alertsix='"+minDatestr+"' or alertseven='"+minDatestr+"' or alerteight='"+minDatestr+"' or alertnine='"+minDatestr+"' or alertten='"+minDatestr+"'";
				cur=sld.rawQuery(sql,null);
				if(cur.moveToNext())
				{
					eventid=cur.getInt(0);//拿到事件id
				}
				date=newsdialog.Stringtodate(minDatestr);
				map.put(date, eventid);
			}
		}
		catch(Exception e)
		{
			Log.d("error", e.toString()+"=============selectDate===============");
			Toast.makeText(fa, "查詢日期失敗："+e.toString(), Toast.LENGTH_LONG).show();
		}
		finally
		{
			try
			{
				cur.close();
				closeDatabase(sld,fa);
			}
			catch(Exception e)
			{
				Log.d("error", e.toString()+"=============selectDate===============");
			}
		}
		return map;
	}
	public static ArrayList<String> searchContent(Context fa,String edit)
	{
		SQLiteDatabase sld=null;
		sld=openDatabase(fa);
		ArrayList<String> al=new ArrayList<String>();
		String sql=null;
		al.clear();
		if(!edit.equals(""))
		try
		{
			sql="select _id ,title from tb where title like '%"+edit+"%'";
			Cursor cur=sld.rawQuery(sql, null);
			while(cur.moveToNext())
			{
				al.add(cur.getInt(0)+"#"+cur.getString(1));
			}
			cur.close();
		}
		catch(Exception e)
		{
			Toast.makeText(fa, "數據庫錯誤 delete："+e.toString(), Toast.LENGTH_LONG).show();
		}finally
		{
			try{closeDatabase(sld,fa);}catch(Exception e){e.printStackTrace();}
		}
		return al;
	}
}




