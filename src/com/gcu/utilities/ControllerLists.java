/**
 * The purpose of this utility class is to remove some
 * fluff from controllers and provide some reusability
 * for methods that were used multiple times.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */

package com.gcu.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerLists {
	
	/**
	 * This method returns a list of all state abbreviations
	 * @return List<String>
	 */
	public List<String> getStates(){
		List<String> stateList = new ArrayList<String>();
		stateList.add("AL");
		stateList.add("AK");
		stateList.add("AZ");
		stateList.add("AR");
		stateList.add("CA");
		stateList.add("CO");
		stateList.add("CT");
		stateList.add("DE");
		stateList.add("FL");
		stateList.add("GA");
		stateList.add("HI");
		stateList.add("ID");
		stateList.add("IL");
		stateList.add("IN");
		stateList.add("IA");
		stateList.add("KS");
		stateList.add("KY");
		stateList.add("LA");
		stateList.add("ME");
		stateList.add("MD");
		stateList.add("MA");
		stateList.add("MI");
		stateList.add("MN");
		stateList.add("MS");
		stateList.add("MO");
		stateList.add("MT");
		stateList.add("NE");
		stateList.add("NV");
		stateList.add("NH");
		stateList.add("NJ");
		stateList.add("NM");
		stateList.add("NY");
		stateList.add("NC");
		stateList.add("ND");
		stateList.add("OH");
		stateList.add("OK");
		stateList.add("OR");
		stateList.add("PA");
		stateList.add("RI");
		stateList.add("SC");
		stateList.add("SD");
		stateList.add("TN");
		stateList.add("TX");
		stateList.add("UT");
		stateList.add("VT");
		stateList.add("VA");
		stateList.add("WA");
		stateList.add("WV");
		stateList.add("WI");
		stateList.add("WY");
		return stateList;
	}
	
	/**
	 * This method returns a hashmap with a key value pair of months and their associated number.
	 * @return Map<Integer, String>
	 */
	public Map<Integer, String> getMonths() {
		Map<Integer, String> monthList = new HashMap<Integer, String>();
		monthList.put(1, "January");
		monthList.put(2, "February");
		monthList.put(3, "March");
		monthList.put(4, "April");
		monthList.put(5, "May");
		monthList.put(6, "June");
		monthList.put(7, "July");
		monthList.put(8, "August");
		monthList.put(9, "September");
		monthList.put(10, "October");
		monthList.put(11, "November");
		monthList.put(12, "December");
		return monthList;
	}
	
	/**
	 * This method returns a list of years from 2018 to 1900 descending.
	 * @return List<Integer>
	 */
	public List<Integer> getBirthYear(){
		List<Integer> yearList = new ArrayList<Integer>();
		for(int i = 2019; i >= 1900; i--) { //Loops 2018 -> 1900 descending
			yearList.add(i);
		}
		return yearList;
	}
	
	/**
	 * This method returns a list of numbers from 1 to 31 ascending for a day number choice.
	 * @return List<Integer>
	 */
	public List<Integer> getBirthDay(){
		List<Integer> dayList = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) { //Loop to grab numbers 1->31
			dayList.add(i);
		}
		return dayList;
	}
	
	/**
	 * This method returns a list of different job types.
	 * @return List<String>
	 */
	public List<String> getJobList(){
		List<String> jobList = new ArrayList<String>();
		//These job types were grabbed from a career webpage and are meant to be generic.
		jobList.add("Accounting");
		jobList.add("Admin");
		jobList.add("Automotive");
		jobList.add("Banking");
		jobList.add("Biotech");
		jobList.add("Business");
		jobList.add("Construction");
		jobList.add("Consultant");
		jobList.add("Customer Service");
		jobList.add("Design");
		jobList.add("Distribution");
		jobList.add("Education");
		jobList.add("Engineering");
		jobList.add("Facilities");
		jobList.add("Finanace");
		jobList.add("Franchise");
		jobList.add("General Labor");
		jobList.add("Government");
		jobList.add("Grocery");
		jobList.add("Health Care");
		jobList.add("Human Resources");
		jobList.add("Installation");
		jobList.add("Repair");
		jobList.add("Insurance");
		jobList.add("Journalism");
		jobList.add("Legal");
		jobList.add("Management");
		jobList.add("Manufacturing");
		jobList.add("Marketing");
		jobList.add("Non-profit");
		jobList.add("Pharmaceutical");
		jobList.add("Quality Assurance");
		jobList.add("Real Estate");
		jobList.add("Research");
		jobList.add("Restaurant/Food Service");
		jobList.add("Retail");
		jobList.add("Sales");
		jobList.add("Science");
		jobList.add("Shipping");
		jobList.add("Technology");
		jobList.add("Telecommunications");
		jobList.add("Training");
		jobList.add("Transportation");
		jobList.add("Warehouse");
		jobList.add("Other");
		return jobList;
	}
	
	/**
	 * This method returns a list of different education levels.
	 * @return List<String>
	 */
	public List<String> getEdList(){
		List<String> edList = new ArrayList<String>();
		edList.add("None");
		edList.add("Elementary");
		edList.add("Middle School");
		edList.add("Some High School");
		edList.add("GED");
		edList.add("High School Diploma");
		edList.add("Some College");
		edList.add("Associates Degree");
		edList.add("Bachelor's Degree");
		edList.add("Master's Degree");
		edList.add("Doctorate's Degree");
		return edList;
	}
	
	/**
	 * This method returns a list of different relationship status'
	 * @return List<String>
	 */
	public List<String> getStatList(){
		List<String> statList = new ArrayList<String>();
		statList.add("Single");
		statList.add("In a relationship");
		statList.add("Engaged");
		statList.add("Married");
		statList.add("Separated");
		statList.add("Divorced");
		statList.add("Widowed");
		return statList;
	}
	
	/**
	 * This method returns a list of different ethnicity types.
	 * @return List<String>
	 */
	public List<String> getEthList(){
		List<String> ethList = new ArrayList<String>();
		ethList.add("Native American");
		ethList.add("Asian");
		ethList.add("Black or African American");
		ethList.add("Pacific Islander");
		ethList.add("Hispanic or Latino");
		ethList.add("White");
		ethList.add("Other");
		return ethList;
	}
	
	/**
	 * This method returns a list of salary ranges.
	 * @return List<String>
	 */
	public List<String> getSalaryList(){
		List<String> salaryList = new ArrayList<String>();
		salaryList.add("$20,000 or lower");
		salaryList.add("$20,000 to $50,000");
		salaryList.add("$50,000 to $80,000");
		salaryList.add("$80,000 to $100,000");
		salaryList.add("$100,000 or higher");
		salaryList.add("I don't want to answer");
		return salaryList;
	}
}
