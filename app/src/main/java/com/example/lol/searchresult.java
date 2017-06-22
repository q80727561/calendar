package com.example.lol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 8 on 2017/6/1.
 */

public class searchresult extends Activity{
    EditText EditText;
    ArrayList<String> idd,iee;
    ListView lv;
    ArrayAdapter<String> tempAd;
    Button Button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.searchresult);
        EditText=(EditText)findViewById(R.id.editText5);
        Button=(Button)findViewById(R.id.button29);
        Intent it = getIntent();
        String id = it.getExtras().getString("edit");
        EditText.setText(id);
        iee=new ArrayList<String>();
        idd=Database.searchContent(this,id);
        for(int i=0;i<idd.size();i++)
        {
            iee.add(i,idd.get(i).split("#")[1]);
        }
        lv = (ListView) findViewById(R.id.g);
        tempAd = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                iee);
        lv.setAdapter(tempAd);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id=idd.get(i).split("#")[0];
                editdialog editDialog = editdialog.newInstance(1);
                editDialog.show(getFragmentManager(),id);
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=EditText.getText().toString();
                idd=Database.searchContent(searchresult.this,id);
                iee=new ArrayList<String>();
                for(int i=0;i<idd.size();i++)
                {
                    iee.add(i,idd.get(i).split("#")[1]);
                }
                tempAd = new ArrayAdapter<String>(searchresult.this,
                        android.R.layout.simple_spinner_item,
                        iee);
                lv.setAdapter(tempAd);
            }
        });

      }
public void refresh(){
    String id=EditText.getText().toString();
    idd=Database.searchContent(searchresult.this,id);
    iee=new ArrayList<String>();
    for(int i=0;i<idd.size();i++)
    {
        iee.add(i,idd.get(i).split("#")[1]);
    }
    tempAd = new ArrayAdapter<String>(searchresult.this,
            android.R.layout.simple_spinner_item,
            iee);
    lv.setAdapter(tempAd);
}
}
