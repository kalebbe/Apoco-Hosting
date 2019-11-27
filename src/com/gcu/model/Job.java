/**
 * This model is used for job listings. Jobs can be created
 * by company accounts only.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */
package com.gcu.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Job {
	@NotNull(message = "You must include a job title!")
	@Size(min=10, max=200, message="Job title must be between 10 and 200 characters")
	private String jobTitle;
	//Not Required
	@Size(min=1, max=30, message="Salary must be between 1 and 30 characters!")
	private String salary;
	@NotNull(message = "Job requirements must be included!")
	@Size(min = 20, max = 10000, message="Job requirements must be between 20 and 10,000 characters!")
	private String requirements;
	@NotNull(message = "Job description is required!")
	@Size(min=20, max=10000, message="Job descrition must be between 20 and 10,000 characters!")
	private String description;
	@NotNull(message = "Job link is required!")
	@Size(min=8, max=300, message="Job link must be between 8 and 300 characters!")
	private String link;
	@NotNull(message = "City is required!")
	@Size(min=2, max=100, message="City must be between 2 and 100 characters!")
	private String city;
	private String state;
	private Date date;
	private String type; //This is the job type, will be a dropdown
	private String company; //This will be taken from the account's first name
	private int id;
	private int userId;
	
	/**
	 * Used when default values are needed for a created job object
	 */
	public Job() {
		this.jobTitle = "";
		this.salary = "";
		this.requirements = "";
		this.description = "";
		this.link = "";
		this.date = null;
		this.id = -1;
		this.userId = -1;
		this.type = "";
		this.city = "";
		this.state = "";
		this.setCompany("");
	}
	
	/**
	 * Non-default constructor for giving properties to a new job object
	 * @param jobTitle
	 * @param salary
	 * @param requirements
	 * @param description
	 * @param company
	 * @param link
	 * @param id
	 * @param date
	 * @param userId
	 * @param type
	 * @param city
	 * @param state
	 */
	public Job(String jobTitle, String salary, String requirements, String description, String company,
			String link, int id, Date date, int userId, String type, String city, String state) {
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.requirements = requirements;
		this.description = description;
		this.link = link;
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.type = type;
		this.city = city;
		this.state = state;
		this.setCompany(company);
	}
	
	/**
	 * Getter for job ID
	 * @return int
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter for job ID
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter for Job Title
	 * @return String
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * Setter for the job title
	 * @param jobTitle
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * Getter for job salary
	 * @return String
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * Setter for job salary
	 * @param salary
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	/**
	 * Getter for job requirements
	 * @return String
	 */
	public String getRequirements() {
		return requirements;
	}
	/**
	 * Setter for job requirements
	 * @param requirements
	 */
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	/**
	 * Getter for job description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter for job Description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter for link to job
	 * @return String
	 */ 
	public String getLink() {
		return link;
	}
	/**
	 * Setter for link to job
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * Getter for job posted date
	 * @return Date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Setter for job posted date
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Gets the user id of the user that posted the job listing
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * Sets the user id of the job poster
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * Gets the job type
	 * @return String
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets the job type
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Gets the city of the job listing
	 * @return String
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Sets the city of the job listing
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Gets the state of the job listing
	 * @return String
	 */ 
	public String getState() {
		return state;
	}
	/**
	 * Sets the state of the job listing
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Gets the company of the job listing
	 * @return String
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * Sets the company of the job listing
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}
}
