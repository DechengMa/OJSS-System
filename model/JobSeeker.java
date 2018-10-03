package model;

import java.util.ArrayList;

public class JobSeeker {

	private int jobSeekerId;
	private ArrayList<String> skillset;

	public JobSeeker(int jobSeekerId, ArrayList<String> skillset) {
		super();
		this.jobSeekerId = jobSeekerId;
		this.skillset = skillset;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public ArrayList<String> getSkillset() {
		return skillset;
	}

	public void setSkillset(ArrayList<String> skillset) {
		this.skillset = skillset;
	}

}
