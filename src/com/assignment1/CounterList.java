package com.assignment1;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CounterList extends Activity {
	//An array that holds objects containing the information of all the counters
	final ArrayList<Information> infoArr = new ArrayList<Information>();

	//Gets the date and formats it
	Calendar rightNow = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:00");
	String strHour = sdf.format(rightNow.getTime());
	String strWeek = Integer.toString(rightNow.get(Calendar.WEEK_OF_YEAR));
	SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd");
	String strDay = sdf2.format(rightNow.getTime());
	SimpleDateFormat sdf3 = new SimpleDateFormat("MMM");
	String strMonth = sdf3.format(rightNow.getTime());

	

	//Takes the information from the array list of objects and writes the information of each class into a text file
	public void saveFile(String saveFile)
	{
		FileOutputStream fos;

		try {
			fos = openFileOutput(saveFile, Context.MODE_PRIVATE);
			for (int z=0;z<infoArr.size();z++){
				String hist="";
				String sNumb=Integer.toString(infoArr.get(z).getCounts());
				String sHour=Integer.toString(infoArr.get(z).getHours());
				String sDay=Integer.toString(infoArr.get(z).getDay());
				String sWeek=Integer.toString(infoArr.get(z).getWeek());
				String sMonth=Integer.toString(infoArr.get(z).getMonth());
				for(int i=0;i<infoArr.get(z).returnHistory().size();i++){
					
					hist=hist+","+infoArr.get(z).returnHistory().get(i);
					}
				String string = infoArr.get(z).getName()+","+sNumb+","+sHour+","+sDay+","+sWeek+","+sMonth+hist+"\n";
				fos.write(string.getBytes());
			  	}
			fos.close();
			
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
	
	//Stores the last date in a text file
	public void saveFile1(String saveFile)
	{
		FileOutputStream fos;

		try {
			fos = openFileOutput(saveFile, Context.MODE_PRIVATE);
			String string = strHour+","+strDay+","+strWeek+","+strMonth+"\n";
			fos.write(string.getBytes());
			fos.close();
			
			} catch (Exception e) {
			  e.printStackTrace();
			}
	}
	String saveFile = "saveFile.txt";
	String saveFile1 = "saveFile1.txt";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter_list);
		
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.buttonId);
		Intent intent =getIntent();
		
		//Variables sent from the main activity
		final String btnName=intent.getExtras().getString("buttonName");
		final String type=intent.getExtras().getString("Type");
		final String rename=intent.getExtras().getString("rename");
		
	
	
		
		
		
		//This opens two files.  The first file contains the date and time the last time the program was used and stores them in variables.  The second file takes the buttons information and makes a class then adds the class to an array.
		try {
			FileInputStream fis1;
			fis1 = openFileInput(saveFile1);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(fis1));
			String aline1 = in1.readLine();
			String split1[] = aline1.split(",");
			String lastHour =split1[0];
			String lastDay = split1[1];
			String lastWeek = split1[2];
			String lastMonth = split1[3];
			fis1.close();
			FileInputStream fis = openFileInput(saveFile);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String aline = in.readLine();
			while (aline != null) {
				String split[] = aline.split(",");
				int length= split.length;
				String initName = split[0];
				String strNumber = split[1];
				int initCounter = Integer.parseInt(strNumber);
				int initHour=Integer.parseInt(split[2]);
				int initDay=Integer.parseInt(split[3]);
				int initWeek=Integer.parseInt(split[4]);
				int initMonth=Integer.parseInt(split[5]);
				if(!lastMonth.equals(strMonth)){
						infoArr.add(new Information(initName, initCounter,0,0,0,0));
						infoArr.get(infoArr.size()-1).addHistory(lastMonth+"--"+Integer.toString(initMonth));
						infoArr.get(infoArr.size()-1).addHistory("Week of "+lastDay+"--"+Integer.toString(initWeek));
						infoArr.get(infoArr.size()-1).addHistory(lastDay+"--"+Integer.toString(initDay));
						infoArr.get(infoArr.size()-1).addHistory(lastHour+"--"+Integer.toString(initHour));
					}
				else if(!lastWeek.equals(strWeek)){
					infoArr.add(new Information(initName, initCounter,0,0,0,initMonth));
					infoArr.get(infoArr.size()-1).addHistory("Week of "+lastDay+"--"+Integer.toString(initWeek));
					infoArr.get(infoArr.size()-1).addHistory(lastDay+"--"+Integer.toString(initDay));
					infoArr.get(infoArr.size()-1).addHistory(lastHour+"--"+Integer.toString(initHour));
					}
				else if(!lastDay.equals(strDay)){
					infoArr.add(new Information(initName, initCounter,0,0,initWeek,initMonth));
					infoArr.get(infoArr.size()-1).addHistory(lastDay+"--"+Integer.toString(initDay));
					infoArr.get(infoArr.size()-1).addHistory(lastHour+"--"+Integer.toString(initHour));
						}
				else if(!lastHour.equals(strHour)){
					infoArr.add(new Information(initName, initCounter,0,initDay,initWeek,initMonth));
					infoArr.get(infoArr.size()-1).addHistory(lastHour+"--"+Integer.toString(initHour));
					}
			
				else{
					infoArr.add(new Information(initName, initCounter,initHour,initDay,initWeek,initMonth));
					}
				for(int i=6;i<length;i++){
					infoArr.get(infoArr.size()-1).addHistory(split[i]);
					}

				aline=in.readLine();
		    			}	
			fis.close();
			}
		catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
		} 
		catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
		}
		
		
		
		
		saveFile1(saveFile1);
		
		
		//Adds a new button to the array list and creates a new class with default information
		if (type.equals("CREATE")){
			infoArr.add(new Information(btnName, 0,0,0,0,0));
			}
		
		//Sorts the Buttons from least to greatest
		if (type.equals("SORT")){
			Collections.sort(infoArr);
		}
		
		//Instructs the user on what to do after they tapped on a button from the main activity page
		if (type.equals("REMOVE")){
			Toast.makeText(CounterList.this, "Tap on the Counter(s) you want to Remove", Toast.LENGTH_SHORT).show();
		}
		if (type.equals("RESET")){
			Toast.makeText(CounterList.this, "Tap on the Counter(s) you want to Reset", Toast.LENGTH_SHORT).show();
		}
		if (type.equals("RENAME")){
			Toast.makeText(CounterList.this, "Tap on the Counter(s) you want to Rename", Toast.LENGTH_SHORT).show();	
		}
		
		
		int i=0;	
		
		//This dynamically creates Buttons based on their information that is in the class information.  Includes creating a textview showing the number of counts and a third button which will take you to a statistics page
		for (int z=0; z<infoArr.size(); z++){
			final Button btn = new Button(this);
			final Button btnStat = new Button(this);
			final TextView counter = new TextView(this);
			String tempNumber =Integer.toString(infoArr.get(z).getCounts()) ;
			counter.setText(tempNumber);
			counter.setId(100+i);
			counter.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			
			btnStat.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			btnStat.setId(-i);
			btnStat.setText("Stats for "+infoArr.get(z).getName());
			
			
			btnStat.setOnClickListener(new OnClickListener(){
					public void onClick (View v){
						//Takes you to a statistics page that shows how many times the counter has been clicked during an hour, day , week and month
						for (int c=0; c<infoArr.size(); c++){
							
							if (infoArr.get(c).getId()==(-(btnStat.getId()))){
								Intent intent = new Intent (CounterList.this, Statistics.class);
								intent.putExtra("Hour", infoArr.get(c).getHours());
								intent.putExtra("Day", infoArr.get(c).getDay());
								intent.putExtra("Week", infoArr.get(c).getWeek());
								intent.putExtra("Month",infoArr.get(c).getMonth() );
								intent.putExtra("History",infoArr.get(c).returnHistory());
								startActivity(intent);
								}
							
							}
						
						}
					});
			
	       
			btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			btn.setId(i);
			infoArr.get(z).setId(i);
			btn.setText(infoArr.get(z).getName());
			btn.setOnClickListener(new OnClickListener(){
					public void onClick (View v){
						
						//This resets all the counts of the button to zero
						if (type.equals("RESET")){
							for (int c=0; c<infoArr.size(); c++){
								if (infoArr.get(c).getId()==btn.getId()){
									counter.setText("0");
									infoArr.get(c).setCounts(0);
									infoArr.get(c).setHours(0);
									infoArr.get(c).setDay(0);
									infoArr.get(c).setWeek(0);
									infoArr.get(c).setMonth(0);
									infoArr.get(c).reset();
									}
								}
							saveFile(saveFile);
							}
						
						//Completely removes the button from the counter list and array and program
						else if (type.equals("REMOVE")){
							for (int c=0; c<infoArr.size(); c++){
								if (infoArr.get(c).getId()==btn.getId()){
									infoArr.remove(c);
									}	
								}	
							btn.setVisibility(View.GONE);
							counter.setVisibility(View.GONE);
							btnStat.setVisibility(View.GONE);
							saveFile(saveFile);
							}
						
						//After obtaining a new name when you tap on the button it renames the button 
						else if (type.equals("RENAME")){
							btn.setText(rename);
							for (int c=0; c<infoArr.size(); c++){
								if (infoArr.get(c).getId()==(-(btnStat.getId()))){
									btnStat.setText("Stats for "+rename);
								if (infoArr.get(c).getId()==btn.getId()){
									infoArr.get(c).setName(rename);
									}

								}
							}
							saveFile(saveFile);
						}
							
					//Every time the button is pressed the counter is incremented by one and all statistics are updated
						else{
							String temp = counter.getText().toString();
							int number = Integer.parseInt(temp);
							number++;
							counter.setText(Integer.toString(number));
							for (int c=0; c<infoArr.size(); c++){
								if (infoArr.get(c).getId()==btn.getId()){
									infoArr.get(c).setCounts(number);
									infoArr.get(c).setHours(infoArr.get(c).getHours()+1);
									infoArr.get(c).setDay(infoArr.get(c).getDay()+1);
									infoArr.get(c).setWeek(infoArr.get(c).getWeek()+1);
									infoArr.get(c).setMonth(infoArr.get(c).getMonth()+1);
									}
						
								}
							saveFile(saveFile);
							}
							

						}
					});	
				
				
				ll.addView(btn);
				ll.addView(counter);
				ll.addView(btnStat);
				i++;
				
		
			}
		saveFile(saveFile);
		}
}
