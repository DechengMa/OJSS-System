package view;

import controller.*;
import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.Data;

import controller.JobController;
import model.DataStorage;
import model.Job;

public class RecruiterUI {
	public static void recruiterFunction() {
		while(true){
			System.out.println("Welcome! Dear Recruiter! What would you like to do?");
			System.out.println("1.Create a job \n2.Edit job \n3.See created job \n4.Delete job \n5:Send invitation \n6.Logout");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
			switch(option){
				case(1):
					createJob();
					break;
				case(2):
					editJob();
					break;
				case(3):
					seeCreatedJob();
					break;
				case(4):
					deleteJob();
					break;
				case(5):
					sendInvitation();
					break;
				case(6):
					return;
			}
		}
		
	}
	
	public static void seeCreatedJob(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to see created job function");
		ArrayList<Job> joblist = JobController.showCreatedJob();
		if(joblist == null){
			System.out.println("Sorry, you haven't create any job");
		}else{
			System.out.println("This is all the job that you created: \n");
			for(int i = 0;i < joblist.size();i++){
				joblist.get(i).display();
			}
		}
		
		boolean gobackornot = true;
		while(gobackornot){
			System.out.println("Enter 1 to go back");
			String option = sc.nextLine();
			if(option.trim().equals("1")){
				gobackornot = false;
			}else{
				System.out.println("Doesn't have this option");
			}
		}
	}
	
	public static void deleteJob(){
		System.out.println("Welcome to delele job function, you can only delete the jobs that you created");
		ArrayList<Job> joblist = DataStorage.getInstance().getCreatedJob(DataStorage.getInstance().getCurrentUserId());
		boolean deleteornot = true;
		while(deleteornot){
			System.out.println("Please input the jobid that you want to delete");
			Scanner sc = new Scanner(System.in);
			boolean createdjobornot = false;
			int jobid = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < joblist.size(); i++) {
				if(jobid == joblist.get(i).getJobId()){
					createdjobornot = true;
				}
			}
			if(createdjobornot){
				boolean result = JobController.deleteJob(jobid);
				if(result){
					System.out.println("Success! \n Now back to main menu");
					break;
				}else{
					System.out.println("Something wrong, do you wish to try again? \n1.Yes 2.No");
					String option = sc.nextLine();
					if(option.equals("2")){
						break;
					}else{
						System.out.println("Doesn't have this option,we assumed you wish to try again");
					}	
				}
			}else{
				System.out.println("Sorry, you can not delete other recruiter's job, do you wish to try again? \n1.Yes 2.No");
				String option1 = sc.nextLine();
				if(option1.equals("2")){
					break;
				}else if(!option1.equals("1")){
					System.out.println("Doesn't have this option,we assumed you wish to try again");
				}	
			}
		}
	}
	
	public static void sendInvitation(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to send invitation page! \nWho do you want to send invitation to?");
		boolean invitation = true;
		while(invitation){
			System.out.println("Please input Job Seeker's Id");
			int jobseekerid = sc.nextInt();
			sc.nextLine();
			System.out.println("Please input the message you want to send");
			String message = sc.nextLine();
			boolean result = InvitationController.createdInvitation(DataStorage.getInstance(), message, jobseekerid);
			if(result == true){
				invitation = false;
				System.out.println("Success! Now going to the main menu");
			}else{
				System.out.println("Something wrong, do you wish to try again? \n1.Yes 2.No");
				String option = sc.nextLine();
				if(option.equals("2")){
					invitation = false;
					System.out.println("Now going to the main menu");
				}
			}
		}
		
	}
	
	
	public static void editJob(){
		Scanner sc =  new Scanner(System.in);
		System.out.println("Welcome to edit job function");
		System.out.println("You can only edit the job that you've created ");
				
		ArrayList<Job> createdjoblist = DataStorage.getInstance().getCreatedJob(DataStorage.getInstance().getCurrentUserId());
		for(int i = 0;i<createdjoblist.size();i++){
			createdjoblist.get(i).display();
		}
		System.out.println("Which job would you like to edit? Please input its id");
		int jobid = sc.nextInt();
		boolean ownjob = true;
		sc.nextLine();
		for(int i = 0; i < createdjoblist.size();i++){
			if(createdjoblist.get(i).getJobId() == jobid ){
				ownjob = false;
			}
		}
		if(ownjob == false){
			Job job = DataStorage.getInstance().searchJobbyId(jobid);
			System.out.println("Which attribute of this job would you like to edit?");
			System.out.println("1.Jobtitle 2.Job keywords 3.Job categories 4.Job description 5.Advertised or not");
			String attibure = sc.nextLine();
			switch(attibure.trim()){
				case"1":
					//title
					System.out.println("Please input the new title of this job");
					String title = sc.nextLine();
					job.setTitle(title);
					System.out.println("Success, now go back to the recruiter menu");
					break;
				case"2":
					//
					System.out.println("Which option do you want \n1.Add keywords \n2.Delete \n3.Edit");
					ArrayList<String> keywordslist = job.getKeywords();
					switch(sc.nextLine().trim()){
						case"1":
							System.out.println("Please input the new keywords");
							keywordslist.add(sc.nextLine());
							while(true){
								System.out.println("Do you wish to add more? \n1.Yes \n2.No");
								String option = sc.nextLine().trim();
								if(option.equals("1")){
									keywordslist.add(sc.nextLine());
								}else if (!option.equals("2")) {
									System.out.println("Doesn't have this option");
								}else{
									break;
								}
							}
							break;
						case"2":
							System.out.println("This is all keywords of this job :");
							for(int i = 0 ;i < keywordslist.size();i++){
								System.out.println(keywordslist.get(i));
							}
							System.out.println("Which keyword do you want to delete? Please input the name");
							String keywordsdelete  = sc.nextLine();
							int keywordsindex = -1;
							for(int i = 0 ;i < keywordslist.size();i++){
								if(keywordsdelete.equals(keywordslist.get(i))){
									keywordsindex = i;
								}
							}
							if(keywordsindex != -1){
								keywordslist.remove(keywordsindex);
							}else{
								System.out.println("Sorry, this job doesn't have this keywords");
							}
							break;
						case"3":
							System.out.println("Which keyword do you want to edit? Please input the name");
							for(int i = 0 ;i < keywordslist.size();i++){
								System.out.println(keywordslist.get(i));
							}
							String keywordsedit  = sc.nextLine();
							int index = -1;
							for(int i = 0 ;i < keywordslist.size();i++){
								if(keywordsedit.equals(keywordslist.get(i))){
									index = i;
								}
							}
							if(index != -1){
								System.out.println("Please input the new keyword to replace it ");
								keywordslist.set(index, sc.nextLine());
								job.setKeywords(keywordslist);
							}else{
								System.out.println("Sorry,doesn't have this keyword");
							}
							break;
						default:
							System.out.println("Does have this option");
							break;
					}
					job.setKeywords(keywordslist);
					break;
				case"3":
					ArrayList<String> categorieslist = job.getCategories();
					System.out.println("Which option do you want ? \n1.Add categorie \n2.Delete categorie\n3.Edit categorie \n4.Go back");
					switch(sc.nextLine()){
						case"1":
							System.out.println("Please input the new categorie of this job");
							categorieslist.add(sc.nextLine());
							System.out.println("Success!");
							break;
						case"2":
							System.out.println("This is all the categories:");
							for(int i = 0 ;i < categorieslist.size();i++){
								System.out.println(categorieslist.get(i));
							}
							System.out.println("Which categorie do you want to delete? Please input the name");
							String categoiresdelete  = sc.nextLine();
							boolean findornot = true;
							for(int i = 0 ;i < categorieslist.size();i++){
								if(categoiresdelete.equals(categorieslist.get(i))){
									categorieslist.remove(i);
									findornot = false;
								}
							}
							if(findornot){
								System.out.println("Sorry, categories not found");
							}
							break;
						case"3":
							System.out.println("This is all the categories:");
							for(int i = 0 ;i < categorieslist.size();i++){
								System.out.println(categorieslist.get(i));
							}
							System.out.println("Which categorie do you want to edit? Please input the name");
							String categoiresedit = sc.nextLine();
							int cateindex = -1;
							for(int i = 0 ;i < categorieslist.size();i++){
								if(categoiresedit.equals(categorieslist.get(i))){
									cateindex = i;
								}
							}
							if(cateindex!= -1){
								System.out.println("Please input the categoire you want it to be");
								categorieslist.set(cateindex, sc.nextLine());
							}else{
								System.out.println("Sorry, this job doesn't have this categorie");
							}
							break;
						default:
							System.out.println("Does have this option");
							break;
					}
					break;
				case"4":
					while(true){
						System.out.println("Please input the new description of this job");
						String newdesc = sc.nextLine();
						if(newdesc.trim().isEmpty()){
							System.out.println("Sorry, Please input valid description");
						}else{
							job.setDescription(newdesc);
							break;
						}
					}
					break;
			}
		} else
			System.out.println("You can't edit this job");
	}
	
	public static void createJob(){
		JobController jobController = new JobController();
		System.out.println("Welcome to create job function");
		Scanner sc =  new Scanner(System.in);
		ArrayList<String> keywordslist = new ArrayList<>();
		ArrayList<String> categorieslist = new ArrayList<>();
		boolean judge = true;
		String jobtitle,keywords;
		System.out.println("First,please input the job title");
		jobtitle = sc.nextLine();
		while(judge){
			System.out.println("Please input the keywords,1 keyword a time");
			keywords = sc.nextLine();
			keywordslist.add(keywords);
			System.out.println("Do you wish to input another keywords? \n1.Yes \n2.No");
			String option = sc.nextLine();
			if(option.equals("2")){
				judge = false;
			}else if(!option.trim().equals("1")){
				System.out.println("Don't have this option,please choose again");
			}
			if(keywordslist.size() > 5){
				System.out.println("Sorry, the maximum number of keywords is 5, now going to the next step");
				judge = false;
			}
		}
			System.out.println("Please input the categories of the job");
			
			String categories = sc.nextLine();
			categorieslist.add(categories);
			System.out.println("Please input the description of the job");
			String desc = sc.nextLine();
			System.out.println("Do you want to advertise this job or not? /1.advertise it 2.Do not advertise it");
			String adv = sc.nextLine();
			boolean advornot = false;
			if(adv.equals("1")){
				advornot = true;
			}else if(adv.equals("2")){
				advornot = false;
			}
			if(jobController.createJob(jobtitle,keywordslist, categorieslist, desc, advornot)){
				System.out.println("Success! Now back to the main menu");
			}else{
				System.out.println("Something wrong,do you wish to create again? \1.Yes 2.No");
				String createjoboption = sc.nextLine();
				if(createjoboption.equals("2")){
					System.out.println("Now back to the main menu");
				}
			}
		}		
	}

