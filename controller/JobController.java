package controller;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class JobController {
	public static boolean createJob(String title,ArrayList<String> keywords, ArrayList<String> categories,String desc,boolean adv){
		Job job = new Job(title, DataStorage.getInstance().generateJobId(), keywords, categories, desc, adv);
		boolean result = DataStorage.getInstance().createJob(job);
		return result;
	}
	
//	public static void editJobtitle(int jobid,String attribute){
//		Job job = dataStorage.searchJobbyId(jobid);
//		switch(attribute.trim()){
//			case"1":
//				
//			case"2":
//				job.setKeywords(keywords);
//			case"3":
//				job.setCategories(categories);
//			case"4":
//				job.setDescription(description);
//		}
//	}
	
	public static ArrayList<Job> showCreatedJob(){
		ArrayList<Job> joblist = DataStorage.getInstance().getCreatedJob(DataStorage.getInstance().getCurrentUserId());
		
		return joblist;
	}
	
	public static boolean deleteJob(int jobid){
		boolean result = DataStorage.getInstance().deleteJob(jobid);
		return result;
	}
}
