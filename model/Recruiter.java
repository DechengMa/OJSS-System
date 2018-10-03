package model;

import java.util.ArrayList;

public class Recruiter{

	private int recruiterId;
	private ArrayList<Integer> createdJobIds;
	
	public Recruiter(int recruiterId, ArrayList<Integer> createdJobIds) {
		super();
		this.recruiterId = recruiterId;
		this.createdJobIds = createdJobIds;
	}
	
	public int getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}
	public ArrayList<Integer> getCreatedJobIds() {
		return createdJobIds;
	}
	public void setCreatedJobIds(ArrayList<Integer> createdJobIds) {
		this.createdJobIds = createdJobIds;
	}
	
	
}
