package com.assignment1;

import java.util.ArrayList;

public class Information implements Comparable <Information>{
	
	
	String name;
	int counts;
	int day;
	int week;
	int hours;
	int month;
	int id;
	ArrayList<String> history = new ArrayList<String>();
	
	//Getters and setters for day
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	//Getters and setters for week
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	//Getters and setters for hours
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	//Getters and setters for Month
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	//Getters and setters for button ID
	public int getId() {
		return id;
			}
	public void setId(int id) {
		this.id = id;
		}
	
	//Getters and setters for the button name
	public String getName() {
		return name;
			}
	public void setName(String name) {
		this.name = name;
		}
	
	//Getters and setters for the counter of the counter
	public int getCounts() {
		return counts;
		}
	public void setCounts(int counts) {
		this.counts = counts;
			}
	
	//A constructor that initializes all the information from a button either from a textfile or with default values
	public Information(String name, int counts, int hours, int day, int week,
			int month) {
		super();
		this.name = name;
		this.counts = counts;
		this.day = day;
		this.week = week;
		this.hours = hours;
		this.month = month;
	}

	//Stores the past counts of the counter of previous dates
	public void addHistory(String date){
		if (!history.contains(date)){
			history.add(date);
			}
		}
	//Returns the ArrayList of history
	public ArrayList<String> returnHistory(){
			return history;
		}
	//Resets the history array and makes it empty
	public void reset(){
		history.clear();
	}
	
	
	
	//A function that sorts the ArrayList Information in descending order
	@Override
	public int compareTo(Information infoArr) {
		// TODO Auto-generated method stub
		 if (this.getCounts() < infoArr.getCounts()) {
	            return 1;
	        } else if (this.getCounts() > infoArr.getCounts()) {
	            return -1;
	        } else {
	            return 0;
	        }

	}
	
	
	
		
		
	}

