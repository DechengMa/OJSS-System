package model;

import java.util.ArrayList;

public class Job {
	private String title;
	private int jobId;
	private ArrayList<String> keywords;
	private ArrayList<String> categories;
	private String description;
	private boolean advertisement;
	
	public Job() {
		super();
	}
	
	public void display() {
		System.out.println("title:" + title + " jobId:" + jobId + " advertisement:" + advertisement + " description:"
				+ description);
		System.out.print("keywords: ");
		for (String str : keywords) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.print("categories: ");
		for (String str : categories) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	public Job(String title, int jobId, ArrayList<String> keywords, ArrayList<String> categories, String description,
			boolean advertisement) {
		super();
		this.title = title;
		this.jobId = jobId;
		this.keywords = keywords;
		this.categories = categories;
		this.description = description;
		this.advertisement = advertisement;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(boolean advertisement) {
		this.advertisement = advertisement;
	}

}
