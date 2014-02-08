package com.example.vociebasedcotrol;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class showlist extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
                setContentView(R.layout.activity_show_message);
                
               
                Intent intent = getIntent();
//                ArrayList<String> strList = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);

                
                ListView list = (ListView) findViewById(R.id.list);
//                list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList));
    }

}