package com.example.vociebasedcotrol;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class VoiceRec extends Activity  
{  
 private static final int VOICE_RECOGNITION_REQUEST_CODE = 4321;  
 //private ListView mList;  
 private Button btn_back= null;
 private Button btn_speak= null;
public void onCreate(Bundle savedInstanceState)  
{  
    super.onCreate(savedInstanceState);  
    setContentView(R.layout.activity_vr);  
//    mList = (ListView) findViewById(R.id.ListView01);  
    btn_back= (Button)findViewById(R.id.btn_back);
    btn_back.setOnClickListener(listner); 
    btn_speak = (Button) findViewById(R.id.btn_speak);   
    btn_speak.setOnClickListener(listner);   
}
              
                
            	private OnClickListener listner = new OnClickListener()     
        	{         @Override        
        		public void onClick(View v){
        	    if(v.equals(btn_back)){
        	          Intent intent = new Intent();                 
        	intent.setClass(VoiceRec.this, MainUser.class);                 
        	startActivity(intent);                
        	VoiceRec.this.finish();       
        	} 
        	    else{        	   
            try 
            {  
               
                Intent intent = new Intent  
                (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);  
             
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.  
                                LANGUAGE_MODEL_FREE_FORM);  
 
             
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"ChangShu");  
            
                startActivityForResult(intent,   
                VOICE_RECOGNITION_REQUEST_CODE);  
            }  
            catch (ActivityNotFoundException e)  
            {  
              
                Toast.makeText(VoiceRec.this,  
                            "ActivityNotFoundException",   
                            Toast.LENGTH_LONG).show();   
            }  
        }        
    };   
};


@Override 
protected void onActivityResult(int requestCode,int resultCode,Intent data)  
{  
    
    if(requestCode==VOICE_RECOGNITION_REQUEST_CODE&&resultCode==RESULT_OK)  
    {  
     
        ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  
  
        //mList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));  
        String resultsString = "";   
//        for (int i = 0; i < results.size(); i++)  
//        {  
//            resultsString += results.get(i);  
//        }   
        
        resultsString=results.get(0);
        Toast.makeText(this,resultsString,Toast.LENGTH_LONG).show();   
        super.onActivityResult(requestCode, resultCode, data);  
    }  
}  
} 