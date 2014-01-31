package com.assignment1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	//This reacts to when the "Load Counter" button is pressed and takes you to the list of counters
	public void loadCounter(View view){
		
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("Type", "LOAD");
		startActivity(intent);
	}	
	
	//This responds to when "Rename" Button is pressed then takes you to the list of counters and tap on any counter and the name will be changed to that input
	public void renameCounter(View view){
		EditText text = (EditText) findViewById(R.id.renameCounter);
		String renameCounter = text.getText().toString();
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("rename", renameCounter);
		intent.putExtra("Type", "RENAME");
		startActivity(intent);
	}
	
	//This takes the name of a Counter and makes a new counter on the counter list page
	public void sendButton(View view){
		EditText text = (EditText) findViewById(R.id.counterName);
		String counterName = text.getText().toString();
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("buttonName", counterName);
		intent.putExtra("Type", "CREATE");
		startActivity(intent);
	}
	
	//Takes you to the counter list page and any counter you press will then be reset
	public void resetCounter(View view){
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("Type", "RESET");
		startActivity(intent);
	}
	
	//Sorts all the counters in ascending order then takes you to the counter list page
	public void sortCounter(View view){
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("Type", "SORT");
		startActivity(intent);
	}
	
	//Takes you to the counter list page and any counter that is pressed will be deleted
	public void removeCounter(View view){
		Intent intent = new Intent (this,CounterList.class);
		intent.putExtra("Type", "REMOVE");
		startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
