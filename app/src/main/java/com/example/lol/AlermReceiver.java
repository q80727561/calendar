package com.example.lol;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by 8 on 2017/5/1.
 */

public class AlermReceiver extends BroadcastReceiver {
    private static final int NOTIF_ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        int eventid=intent.getIntExtra("eventid", -1);//得到附加信息中的事件id
        //查詢相應的事件名
        String s="";
        ArrayList<String> content=new ArrayList<String>();
        if(eventid!=-1)
        {
            s= String.valueOf(eventid);
            content=Database.requerycontentforid(context,s);
        }
        Map<Date, Integer> map=Database.findnearlyTime(context);
        for(Date d:map.keySet())
        {//遍歷鍵值Date
            if(d!=null)
            {
                setAlerm(context,d,map.get(d));//設置鬧鐘測試
            }
            else
            {
                Log.d("minDate", "not found");
            }
        }
        NotificationManager notif = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context).setTicker(content.get(0))
                .setSmallIcon(R.drawable.ic_launcher);
        Notification note;
        Intent intent1=new Intent(context,MainActivity.class);
        intent1.putExtra("NOTIFICATION_ID", NOTIF_ID);
        intent1.putExtra("id", s);
        PendingIntent pI = PendingIntent.getActivity(context,
                0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
       note=builder.setContentIntent(pI).setContentTitle("行事曆提醒").setContentText(content.get(0)).build();
        notif.notify(NOTIF_ID, note);   // 送出提醒訊息
    }
    AlarmManager alarmManager;//鬧鐘管理對象聲明
    public void setAlerm(Context context, Date date, int eventid)
    {
        Toast.makeText(context, "設鬧鈴"+date.toLocaleString(), Toast.LENGTH_SHORT).show();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(context,AlermReceiver.class);
        intent.putExtra("eventid", eventid);//將id值附加到intent中
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
    }
}
