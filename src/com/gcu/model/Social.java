/**
 * This model is used for the social profile and its attributes. There isn't much data
 * validation here because most of the attributes are derived from dropdwon boxes.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.01
 * @since   2018-11-25
 */

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Social {

	private boolean privacy; //This determines if randoms can see this user's feed and details
	private String career; //Generic career field
	@NotNull(message = "You must enter a city!")
	@Size(min = 2, max = 40, message = "City must be between 2 and 40 characters!")
	private String city;
	private String State;
	private String status; //Relationship status
	@NotNull(message = "You must enter a biography!")
	@Size(min = 50, max = 5000,  message = "Your biography must be atleast 50 characters!")
	private String bio;
	private String education; //Generic education level
	@NotNull(message = "Please enter your most recent school!")
	@Size(min = 2, max = 100, message = "Your school must be between 2 and 100 characters!")
	private String school; //Actual school
	@NotNull(message = "Please enter your most recent job!")
	@Size(min = 2, max = 100, message = "Your job name must be between 2 and 100 characters!")
	private String job; //Actual job
	private int birthMonth;
	private int birthDay;
	private int birthYear;
	private int userId;
	private int id;
	private String gender;
	private int age;

	/**
	 * Getter for the userId variable.
	 * @return int This is the userId of the social profile.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Setter for the userId variable.
	 * @param userId This is the new userId for the social profile.
	 * @return Nothing.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter for the privacy boolean.
	 * @return boolean This is whether or not the profile is private.
	 */
	public boolean isPrivacy() {
		return privacy;
	}

	/**
	 * Setter for the privacy boolean.
	 * @param privacy This is the new status for the privacy boolean.
	 * @return Nothing.
	 */
	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	/**
	 * Getter for the career String.
	 * @return String This is the career variable of the social profile.
	 */
	public String getCareer() {
		return career;
	}

	/**
	 * Setter for the career String.
	 * @param career This is the new career for the social profile.
	 * @return Nothing.
	 */
	public void setCareer(String career) {
		this.career = career;
	}

	/**
	 * Getter for the city variable.
	 * @return String This is the city variable of the social profile.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter for the city variable
	 * @param city This is the new city for the social profile.
	 * @return nothing.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Getter for the state variable.
	 * @return String This returns the state value.
	 */
	public String getState() {
		return State;
	}

	/**
	 * Setter for the state variable.
	 * @param state This is the new state for the social profile.
	 * @return Nothing.
	 */
	public void setState(String state) {
		State = state;
	}

	/**
	 * Getter for the status variable.
	 * @return String This returns the status value.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter for the status variable.
	 * @param status This is the new status for the social profile.
	 * @return Nothing.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for the bio variable.
	 * @return String This returns the bio value.
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * Setter for the bio variable.
	 * @param bio This is the new bio for the social profile.
	 * @return Nothing.
	 */
	public void setBio(String bio) {
		this.bio = bio;
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
	 * @param education This is the new education for the social profile.
	 * @return Nothing.
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * Getter for the school variable.
	 * @return String This returns the school value.
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * Setter for the school variable.
	 * @param school This is the new school variable for the social profile.
	 * @return Nothing.
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * Getter for the job variable.
	 * @return String This returns the job value.
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Setter for the job variable.
	 * @param job This is the new job variable for the social profile.
	 * @return Nothing.
	 */
	public void setJob(String job) {
		this.job = job;
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
	 * @param birthMonth This is the new birthMonth variable for the social profile.
	 * @return Nothing.
	 */
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
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
	 * @param birthDay This is the new birthDay variable for the social profile.
	 * @return Nothing.
	 */
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
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
	 * @param birthYear This is the new birthYear variable for the social profile.
	 * @return Nothing.
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	/**
	 * Getter for the id variable.
	 * @return int This returns the id value.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id variable.
	 * @param id This is the new id for the social profile.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for gender. Added for additional information
	 * @return String Male or female.. like come on.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for gender. Again male or female.
	 * @param gender Male or female.
	 * @return Nothing.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/*
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	*/

	/**
	 * Getter for the age of a user. Added for more information.
	 * @return int The age.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter for the age of a user.
	 * @param age The new age.
	 * @return Nothing.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Non-default constructor used for creating a Social model from the database.
	 * @param bio This is the user's description of their life.
	 * @param privacy This is whether the user wants everyone to see their info or just friends.
	 * @param career This is the user's current career field.
	 * @param city This is the city the user lives in.
	 * @param state This is the state the user lives in.
	 * @param birthDay This is the user's birth day.
	 * @param id This is the id of the social profile.
	 * @param birthMonth This is the user's birth month.
	 * @param birthYear This is the user's birth year.
	 * @param education This is the user's highest education level.
	 * @param status This is the user's relationship status.
	 * @param job This is the user's current job.
	 * @param school This is the user's current school.
	 * @param userId This is the user id of the profile's owner.
	 */
	public Social(String bio, boolean privacy, String career, String city, String state, int birthDay, int id,
			int birthMonth, int birthYear, String education, String status, String job, String school, String gender, int userId) {
		//this.picture = pic;
		this.id = id;
		this.bio = bio;
		this.privacy = privacy;
		this.career = career;
		this.city = city;
		this.State = state;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.education = education;
		this.status = status;
		this.job = job;
		this.school = school;
		this.userId = userId;
		this.gender = gender;
	}

	/**
	 * Default constructor used to set default values for each property.
	 */
	public Social() {
		//this.picture = "";
		this.bio = "";
		this.privacy = false;
		this.career = "";
		this.city = "";
		this.State = "";
		this.birthDay = 0;
		this.birthMonth = 0;
		this.birthYear = 0;
		this.education = "";
		this.job = "";
		this.status = "";
		this.school = "";
		this.userId = 0;
		this.id = 0;
		this.gender = "Male";
	}
}
