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


public class Login extends Activity {
	private Button button1 = null;
	private Button button2 = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(Listner);
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(Listner);
	}
	private OnClickListener Listner = new OnClickListener()     
	{         @Override        
		public void onClick(View v){
	    if(v.equals(button1)){//login
	    	new LoginTask().execute();
	    } 
	    else{
	    	Intent intent = new Intent();                 
	    	intent.setClass(Login.this, Register.class);                 
	    	startActivity(intent);                
	    	Login.this.finish();
	    }
	    }
	};
	
	/**
	 * 
	 * Login Task
	 *
	 */
	class LoginTask extends AsyncTask<Void, Void, Boolean>{
		private String name;
		private String pwd;
		
		protected void onPreExecute() {
			name = ((EditText) findViewById(R.id.editText1)).getText().toString();
			pwd = ((EditText) findViewById(R.id.editText2)).getText().toString();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			boolean flag = login(name, pwd);
			return flag;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			System.out.println(result);
	    	  if(! result){//login failed
	    		  Toast.makeText(Login.this, "login failed", Toast.LENGTH_SHORT).show();
	    		  return ;
	    	  }
	          Intent intent = new Intent();     
	          intent.setClass(Login.this, MainUser.class);                 
	          startActivity(intent);                
	          Login.this.finish(); 
			super.onPostExecute(result);
		}
	}
	/**
	 * user login
	 */
	private boolean login(String name, String pwd){
		return new NetUtils().login(name, pwd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
