/**
 * This model is used for the dating profile and its attributes. Most of data validation is done through a drop down box
 * or radio button. Age verification will be done in the business logic.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-18
 */

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Dating {
	private int userId;
	private int id;
	private String profession;
	private String salary;
	private String education;
	private String ethnicity;
	private int birthDay;
	private int birthMonth;
	private int birthYear;
	private int age;
	@NotNull(message="You must enter a city!")
	@Size(min = 2, max = 40, message = "City must be between 2 and 40 characters!")
	private String city;
	private String state;
	private String status;
	private String gender;
	@NotNull(message="You must enter a nickname!")
	@Size(min = 2, max = 20, message = "Nickname must be between 2 and 20 characters!")
	private String nickname;
	@NotNull(message="Interests cannot be left blank!")
	@Size(min = 10, max = 100, message = "Interests must be between 10 and 100 characters!")
	private String interests;
	@NotNull(message="About me section cannot be left blank!")
	@Size(min = 50, max = 5000, message = "About me must be atleast 50 characters!")
	private String about;
	
	/**
	 * Non-default constructor for the creation of a Dating object.
	 * @param profession
	 * @param salary
	 * @param education
	 * @param ethnicity
	 * @param birthDay
	 * @param birthMonth
	 * @param birthYear
	 * @param city
	 * @param state
	 * @param status
	 * @param gender
	 * @param nickname
	 * @param interests
	 * @param about
	 * @param userId
	 * @param id
	 */
	public Dating(String profession, String salary, String education, String ethnicity, int birthDay, 
			int birthMonth, int birthYear, String city, String state, String status, String gender, 
			String nickname, String interests, String about, int userId, int id) {
		this.userId = userId;
		this.id = id;
		this.profession = profession;
		this.salary = salary;
		this.education = education;
		this.ethnicity = ethnicity;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.city = city;
		this.state = state;
		this.status = status;
		this.gender = gender;
		this.nickname = nickname;
		this.interests = interests;
		this.about = about;
	}
	
	/**
	 * Default constructor for a dating object.
	 */
	public Dating() {
		this.userId = 0;
		this.id = 0;
		this.profession = "";
		this.salary = "";
		this.education = "";
		this.birthDay = 0;
		this.birthMonth = 0;
		this.birthYear = 0;
		this.city = "";
		this.state = "";
		this.status = "";
		this.gender = "Male";
		this.nickname = "";
		this.interests = "";
		this.about = "";
	}

	/**
	 * Getter for User ID (Logged in user)
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Setter for User ID
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * getter for the Dating ID (database)
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the Dating ID
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for profession
	 * @return String
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Setter for profession
	 * @param profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * Auto generated getter for salary
	 * @return String
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * Auto generated setter for salary
	 * @param salary
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * Auto generated getter for education
	 * @return String
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * Auto generated setter for education
	 * @param education
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * Auto generated getter for ethnicity
	 * @return String
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 * Auto generated setter for ethnicity
	 * @param ethnicity
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 * Auto generated getter for birth day
	 * @return int
	 */
	public int getBirthDay() {
		return birthDay;
	}

	/**
	 * Auto generated setter for birth day
	 * @param birthDay
	 */
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * Auto generated getter for birth month
	 * @return int
	 */
	public int getBirthMonth() {
		return birthMonth;
	}

	/**
	 * Auto generated setter for birth month
	 * @param birthMonth
	 */
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	/**
	 * Auto generated getter for birth year
	 * @return int
	 */
	public int getBirthYear() {
		return birthYear;
	}

	/**
	 * Auto generated setter for birth year
	 * @param birthYear
	 */
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * Auto generated getter for age
	 * @return int
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Auto generated setter for age
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Auto generated getter for city
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Auto generated setter for city
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Auto generated getter for state
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/**
	 * Auto generated gsetter for state
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Auto generated getter for status
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Auto generated setter for status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Auto generated getter for gender
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Auto generated setter for gender
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Auto generated getter for nickname
	 * @return String
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Auto generated setter for nickname
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Auto generated getter for interests
	 * @return String
	 */
	public String getInterests() {
		return interests;
	}

	/**
	 * Auto generated setter for interests
	 * @param interests
	 */
	public void setInterests(String interests) {
		this.interests = interests;
	}

	/**
	 * Auto generated getter for about
	 * @return String
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * Auto generated setter for about
	 * @param about
	 */
	public void setAbout(String about) {
		this.about = about;
	}
	
}
