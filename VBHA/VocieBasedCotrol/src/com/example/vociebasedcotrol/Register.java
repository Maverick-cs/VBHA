package com.example.vociebasedcotrol;

import com.example.vociebasedcotrol.utils.NetUtils;

import android.app.Activity;

import android.content.Intent;   
import android.os.AsyncTask;
import android.os.Bundle;   
import android.view.Menu;
import android.view.View;    
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	private Button button1= null;
	private Button button2= null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_register);
	    button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(Listner);
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(Listner);
	} 
	private OnClickListener Listner = new OnClickListener()     
	{         @Override        
		public void onClick(View v){
	    if(v.equals(button1)){
	          Intent intent = new Intent();                 
	          intent.setClass(Register.this, Login.class);                 
	          startActivity(intent);                
	          Register.this.finish();       
	    } 
	    else{//register
	    	new RegisterTask().execute();
	    }
	    }
	};
	
	/**
	 * 
	 * register
	 *
	 */
	class RegisterTask extends AsyncTask<Void, Void, Boolean>{
		String name, email, pwd;
		int age;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			name = ((EditText) findViewById(R.id.editText1)).getText().toString();
			email = ((EditText) findViewById(R.id.editText2)).getText().toString();
			String str_age = ((EditText) findViewById(R.id.editText3)).getText().toString();
			age = Integer.parseInt(str_age);
			pwd = ((EditText) findViewById(R.id.editText4)).getText().toString();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			boolean flag = new NetUtils().register(name, pwd, age, email);
			return flag;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
	    	if(result){
	    		Toast.makeText(Register.this, "注册成功, 请登录", Toast.LENGTH_SHORT).show();
		    	Intent intent = new Intent();                 
		    	intent.setClass(Register.this, Login.class);                 
		    	startActivity(intent);                
		    	Register.this.finish();
	    	}else{
	    		Toast.makeText(Register.this, "注册失败, 用户名已存在", Toast.LENGTH_SHORT).show();
	    	}			
		}
		
	}
}
