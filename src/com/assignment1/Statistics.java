package com.assignment1;
 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Statistics extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		LinearLayout ll = (LinearLayout)findViewById(R.id.statId);
		
		//Gets the current date and formats it 
		Calendar date = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:00");
		String strDate = sdf.format( date.getTime());
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd");
		String strDate1 = sdf1.format(date.getTime());
		SimpleDateFormat sdf2 = new SimpleDateFormat("MMM");
		String strDate2 = sdf2.format(date.getTime());
		
		Intent intent =getIntent();

		
		int hour=intent.getExtras().getInt("Hour");
		int day=intent.getExtras().getInt("Week");
		int week=intent.getExtras().getInt("Day");
		int month=intent.getExtras().getInt("Month");
		ArrayList<String> history=intent.getStringArrayListExtra("History");
		
		for(int i=0; i<history.size();i++){
			TextView counter = new TextView(this);
			counter.setText(history.get(i));
			counter.setId(i+4);
			counter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			ll.addView(counter);

		}
		TextView counter1 = new TextView(this);
		counter1.setText(strDate+"--"+Integer.toString(hour));
		counter1.setId(0);
		counter1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll.addView(counter1);
		TextView counter2 = new TextView(this);
		counter2.setText(strDate1+"--"+Integer.toString(day));
		counter2.setId(1);
		counter2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll.addView(counter2);
		TextView counter3 = new TextView(this);
		counter3.setText("Week of "+strDate1+"--"+Integer.toString(week));
		counter3.setId(1);
		counter3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll.addView(counter3);
		TextView counter4 = new TextView(this);
		counter4.setText("Month of "+strDate2+"--"+Integer.toString(month));
		counter4.setId(1);
		counter4.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		ll.addView(counter4);
		
	}

	
}
