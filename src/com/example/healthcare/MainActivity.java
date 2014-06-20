package com.example.healthcare;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button startbn=(Button)findViewById(R.id.startbutton);
		startbn.setOnClickListener(new OnClickListener()
		                             {
										@Override
										public void onClick(View arg0) {
											// TODO Auto-generated method stub
											startActivity();
										}
		                               });
		
		Button stopbn=(Button)findViewById(R.id.stopbutton);
		stopbn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finishActivity();
			}
		});
		
	
	}
	 @Override
	 public void onBackPressed()
	 {
		 finishActivity();
	 }
	   
	 // start another activity  
	private void startActivity()
	{
		Intent intent=new Intent(this,JiaSuQi.class);
	    startActivity(intent);
	}
	
	
	// Exit HealthCare
	private void finishActivity()
	{
		AlertDialog.Builder dlg = new AlertDialog.Builder(this);
		dlg.setTitle("温馨提示：");
		dlg.setMessage("确定推出？");
		dlg.setPositiveButton("退出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					MainActivity.this.finish();
			}			
		});
		
		dlg.setNegativeButton("取消", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
					
		});
		dlg.create().show();
		//Window window = dlg.getWindow();
		//finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
