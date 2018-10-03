package model;

public class Application {

	private int applicationId;
	private int jobId;
	private int jobSeekerId;
	private int recruiterId;
	private String firstname;
	private String surname;
	private String dob;
	private String mobile;
	private String currentOccupation;
	private String filePaths;
	public Application(int applicationId, int jobId, int jobSeekerId, int recruiterId, String firstname, String surname,
			String dob, String mobile, String currentOccupation, String filePaths) {
		super();
		this.applicationId = applicationId;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.recruiterId = recruiterId;
		this.firstname = firstname;
		this.surname = surname;
		this.dob = dob;
		this.mobile = mobile;
		this.currentOccupation = currentOccupation;
		this.filePaths = filePaths;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public int getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCurrentOccupation() {
		return currentOccupation;
	}
	public void setCurrentOccupation(String currentOccupation) {
		this.currentOccupation = currentOccupation;
	}
	public String getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(String filePaths) {
		this.filePaths = filePaths;
	}

}
