package com.example.createloginuserexample;


import teech.sdk.Teech;
import teech.sdk.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	String user;
	String psw;
	Bundle bundle;
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Teech.init("xxxxxxxxxxxxxxxxxxx23423bajdaxxa", "xxxxx4656mfkxxxsYtrE45xx");
		
		Button login = (Button)findViewById(R.id.buttonLogIn);
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText text2 = (EditText)findViewById(R.id.editText1);
				user = text2.getText().toString();
				EditText text3 = (EditText)findViewById(R.id.editText2);
				psw = text3.getText().toString();
				
				ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			    if (activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED){
			    	new LogInTeech().execute();
			    }else{
			    	Toast.makeText(getApplicationContext(), "There is not a internet connection", Toast.LENGTH_SHORT).show();//not connection
			    }
			}
		});
		
		Button create = (Button)findViewById(R.id.buttonCreate);
		create.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(getApplicationContext(), Create.class);
				startActivity(in);
			}
		});

	}
	
	public class LogInTeech extends AsyncTask<String, Integer, String>{
		String response;

		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setTitle("Check access data in progress");
			dialog.setMessage("Wait a few seconds...");
			dialog.show();
		}

		protected String doInBackground(String... urls){
			String result="";
			User u = new User();
			boolean b = u.logIn(user, psw);
			if(b){
				result = "1";
			}else{
				result = "0";
			}
			return result;
		}

		protected void onPostExecute(String result) {
			if(result.equals("1")){
				Intent in = new Intent(getApplicationContext(), Home.class);
				startActivity(in);
			}else{
				Toast.makeText(getApplicationContext(), "LogIn Fail!", Toast.LENGTH_SHORT).show();//not connection
			}
			dialog.dismiss();
		}

	}
}