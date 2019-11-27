/**
 * This model is used for the business profile and its attributes. There is only one item of data here
 * that needs to be validated because everything else is done through a dropdown box or a radio button.
 * There may more properties added in the future, but this is it for now.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Business {
	private int userId;
	private int id;
	private int birthDay;
	private int birthMonth;
	private int birthYear;
	private int age;
	private String profession;
	private String gender; //For presentation not applications
	private String ethnicity; //For applications, can use other if not comfortable answering
	@NotNull(message="You must enter a city!")
	@Size(min = 2, max = 40, message = "City must be between 2 and 40 characters!")
	private String city; //Only field put in manually.
	private String state;
	private String education;
	
	/**
	 * Getter for the userId variable.
	 * @return int This is the userId of the business profile.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Setter for the userId variable.
	 * @param userId This is the new userId for the business profile.
	 * @return Nothing.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter for the id variable.
	 * @return int This is the id of the business profile.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the id variable.
	 * @param id This is the new id for the business profile.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the birthDay variable.
	 * @return int This returns the birthDay value.
	 */
	public int getBirthDay() {
		return birthDay;
	}
	
	/**
	 * Setter for the birthDay variable.
	 * @param birthDay This is the new birthDay variable for the business profile.
	 * @return Nothing.
	 */
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	
	/**
	 * Getter for the birthMonth variable.
	 * @return int This returns the birthMonth value.
	 */
	public int getBirthMonth() {
		return birthMonth;
	}
	
	/**
	 * Setter for the birthMonth variable.
	 * @param birthMonth This is the new birthMonth variable for the business profile.
	 * @return nothing.
	 */
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	
	/**
	 * Getter for the birthYear variable.
	 * @return int This returns the birthYear value.
	 */
	public int getBirthYear() {
		return birthYear;
	}
	
	/**
	 * Setter for the birthYear variable.
	 * @param birthYear This is the new birthYear variable of the business profile.
	 * @return Nothing.
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	/**
	 * Getter for the profession variable.
	 * @return String This returns the profession value.
	 */
	public String getProfession() {
		return profession;
	}
	
	/**
	 * Setter for the profession variable.
	 * @param profession This is the new profession value for the business profile.
	 * @return Nothing.
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	/**
	 * Getter for the gender variable.
	 * @return String This returns the gender value.
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Setter for the gender variable.
	 * @param gender This is the new gender value for the business profile.
	 * @return Nothing.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Getter for the ethnicity variable.
	 * @return String This returns the ethnicity value.
	 */
	public String getEthnicity() {
		return ethnicity;
	}
	
	/**
	 * Settter for the ethnicity variable.
	 * @param ethnicity This is the new ethnicity value for the business profile.
	 * @return Nothing.
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	
	/**
	 * Getter for the city variable.
	 * @return String This returns the city value.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Setter for the city variable.
	 * @param city This is the new city value for the business profile.
	 * @return Nothing.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Getter for the state variable.
	 * @return String This returns the state value.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Setter for the state variable.
	 * @param state This is the new state value for the business profile.
	 * @return Nothing.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Getter for the education variable.
	 * @return String This returns the education value.
	 */
	public String getEducation() {
		return education;
	}
	
	/**
	 * Setter for the education variable.
	 * @param education This is the new education value for the business profile.
	 * @return Nothing.
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	
	/**
	 * This is the non-default constructor for creating a businesss object. Generally used when getting objects
	 * from the database.
	 * @param userId This is the id of the creator of the profile.
	 * @param id This is the id of the profile object itself.
	 * @param birthDay This is the birth day of the user.
	 * @param birthMonth This is the birth month of the user.
	 * @param birthYear This is the birth year of the user.
	 * @param profession This is the general profession of the user.
	 * @param gender This is the gender of the user.
	 * @param ethnicity This is the ethnicity of the user.
	 * @param city This is the state the user resides.
	 * @param state This is the state the user resides.
	 * @param education This is the education level of the user.
	 */
	public Business(int userId, int id, int birthDay, int birthMonth, int birthYear, String profession, String gender, String ethnicity, String city,
			String state, String education) {
		this.userId = userId;
		this.id = id;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.profession = profession;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.city = city;
		this.state = state;
		this.education = education;
	}
	
	/**
	 * Default constructor for the Business object that sets all of the default values.
	 * Gender is only set to male, so something will always be checked.
	 */
	public Business() {
		this.userId = 0;
		this.id = 0;
		this.birthDay = 0;
		this.birthMonth = 0;
		this.birthYear = 0;
		this.profession = "";
		this.gender = "Male";
		this.ethnicity = "";
		this.city = "";
		this.state = "";
		this.education = "";
	}

	/**
	 * Gets the age of the Business user
	 * @return int
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the age of the business user
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
