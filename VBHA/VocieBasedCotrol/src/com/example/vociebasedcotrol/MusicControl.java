package com.example.vociebasedcotrol;

import java.util.ArrayList;

import android.app.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;   
import android.os.Bundle;   
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;    
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MusicControl extends Activity {
	private Button button1= null;
	private Button btn_speak= null;
	private static final int VOICE_RECOGNITION_REQUEST_CODE = 4321; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_musiccontrol);
	    button1= (Button)findViewById(R.id.button1);
	    button1.setOnClickListener(listener); 
	    btn_speak= (Button)findViewById(R.id.btn_speak);
	    btn_speak.setOnClickListener(listener); 
	
	    // TODO Auto-generated method stub
	}
	private OnClickListener listener = new OnClickListener()     
	{         @Override        public void onClick(View v)       
	{   
		if(v.equals(button1)){
		Intent intent = new Intent();                 
		intent.setClass(MusicControl.this, MainUser.class);                 
		startActivity(intent);                
		MusicControl.this.finish();     
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
               
                Toast.makeText(MusicControl.this,  
                            "ActivityNotFoundException",   
                            Toast.LENGTH_LONG).show();   
            }
		}
	}     
	};   
	
	@Override 
	protected void onActivityResult(int requestCode,int resultCode,Intent data)  
	{  
	 
	    if(requestCode==VOICE_RECOGNITION_REQUEST_CODE&&resultCode==RESULT_OK)  
	    {  
	     
	        ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  
	  
	        //mList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));  
	        String resultsString = "";   
//	        for (int i = 0; i < results.size(); i++)  
//	        {  
//	            resultsString += results.get(i);  
//	        }   
	        resultsString=results.get(0);
	        Toast.makeText(this,resultsString,Toast.LENGTH_LONG).show();   
	        super.onActivityResult(requestCode, resultCode, data);  
	    }  
	} 
}
