package com.example.createloginuserexample;

import teech.sdk.*;
import teech.sdk.exceptions.APIConnectionException;
import teech.sdk.exceptions.InvalidRequestException;
import teech.sdk.exceptions.TeechAuthenticationException;
import teech.sdk.exceptions.TeechException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends Activity {
	
	String user;
	String email;
	String psw;
	Bundle bundle;
	ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create);
		
		
		Button create = (Button)findViewById(R.id.buttonCreate);
		create.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new CreateUserTeech().execute();
			}
		});
	}
	
	public class CreateUserTeech extends AsyncTask<String, Integer, String>{

		protected void onPreExecute() {
			dialog = new ProgressDialog(Create.this);
			dialog.setTitle("Check access data in progress");
			dialog.setMessage("Wait a few seconds...");
			dialog.show();
		}

		protected String doInBackground(String... urls){
			
			EditText text1 = (EditText)findViewById(R.id.editText1);
			user = text1.getText().toString();
			EditText text2 = (EditText)findViewById(R.id.editText2);
			email = text2.getText().toString();
			EditText text3 = (EditText)findViewById(R.id.editText3);
			psw = text3.getText().toString();
			
			String result="0";
			User u = new User();
			u.put("username", user); //required
			u.put("password", psw); //required
			u.put("email",email);
			try {
				u.save();
				result="1";
			} catch (InvalidRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TeechAuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (APIConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TeechException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;	
			
		}

		protected void onPostExecute(String result) {
			if(result.equals("1")){
				Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();//not connection
				Intent in = new Intent(getApplicationContext(), Home.class);
				startActivity(in);
			}else{
				Toast.makeText(getApplicationContext(), "User not created", Toast.LENGTH_SHORT).show();//not connection
			}
			dialog.dismiss();
		}

	}
}
