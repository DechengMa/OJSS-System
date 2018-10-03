package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.*;

import org.omg.Messaging.SyncScopeHelper;

import controller.ApplicationController;
import controller.InvitationController;
import model.Application;

public class JobseekerUI {
	public static void jobSeekerFunction() {
		while(true){
			System.out.println("Welcome! Dear Job Seeker! What would you like to do?");
			System.out.println("1.Search for a job \n2.Apply for a job \n3.Manage your own skillset \n4.Check inviation "
					+ "\n5.See applyed job \n6.logout");
			Scanner sc = new Scanner(System.in);
			String option = sc.nextLine();
			switch (option.trim()) {
				case ("1"):
					searchJob();
					break;
				case ("2"):
					applyJob();
					break;
				case ("3"):
					manageSkillset();
					break;
				case ("4"):
					checkInivation();
					break;
				case ("5"):
					showAppliedJob();
					break;
				case ("6"):
					return;
				default:
					System.out.println("Doen't have this option, now go back to the main menu");
					break;
			}
		}
		
	}
	
	public static void checkInivation(){
		ArrayList<Invitation> inviatationList = InvitationController.getInvitation();
		if(inviatationList != null){
			System.out.println("This is all the inviation that you received");
			for (Invitation invitation : inviatationList) {
				invitation.display();
			}
		}else{
			System.out.println("Sorry, you currently didn't receive any inviation");
		}
	}
	
	public static void showAppliedJob(){
		Scanner sc = new Scanner(System.in);
		ArrayList<Job> joblist = DataStorage.getInstance().findAppliedJobs(DataStorage.getInstance().getCurrentUserId());
		boolean showjudge = true;
		if(joblist.isEmpty()){
			System.out.println("This is all jobs that you applied");
			for (Job job : joblist) {
				job.getTitle();
			}
			while(showjudge){
				System.out.println("If you want to go back to the main menu, enter 1");
				String option = sc.nextLine();
				if(option.equals("1")){
					showjudge = false;
				}else{
					System.out.println("Doesn't have this option, please input again");
				}
			}
		}else{
			System.out.println("No job found, now going to the main menu\n");
		}
	}

	public static void manageSkillset(){
		JobSeeker jobseeker = DataStorage.getInstance().searchJobSeekerbyId(DataStorage.getInstance().getCurrentUserId());
		ArrayList<String> skillsetlist = jobseeker.getSkillset();
		System.out.println("Here are your skills right now");
		for (String string : skillsetlist) {
			System.out.println(string);
		}
		System.out.println("What would you like to do ? \n1.Add skill 2.Edit skill 3.Delete skill 4.Go back to the main page");
		Scanner sc = new Scanner(System.in);
		String option = sc.nextLine();
		switch(option.trim()){
			case("1"):
				System.out.println("Please enter your new skill");
				String newskill = sc.nextLine();
				skillsetlist.add(newskill);
				DataStorage.getInstance().editJobSeeker(jobseeker);
				System.out.println("Success, now go back to the Job Seeker menu");
				break;
			case("2"):
				System.out.println("This is all skillset you have:");
				for (String string : skillsetlist) {
					System.out.println(string+" ");
				}
				boolean checkOption = true;
				while(checkOption) {
					for(int i = 0; i < skillsetlist.size(); i++) {
						System.out.print((i+1) + ". "+ skillsetlist.get(i) + " ");
					}
					System.out.println();
					System.out.println("which skill would you like to edit,please input its number");
				    int editskillnumber = sc.nextInt();
				    sc.nextLine();	    
				    int num = editskillnumber - 1;
				    System.out.println("The skill you want to edit is " + skillsetlist.get(num));
				    System.out.println("Please enter your new skills");		    
				    String editskill = "";
				    editskill = sc.nextLine();
				    skillsetlist.set(num, editskill);
				    System.out.println("Your current skills are:");
				    for(int i = 0; i < skillsetlist.size(); i++) {
						System.out.print((i+1) + ". "+ skillsetlist.get(i) + " ");
					}
				    System.out.println();
				    System.out.println("Do you want to edit other skills?(input 1 for continue, 2 for quit)");
				    int option1 = 0;
				    	option1 = sc.nextInt();
				    if(option1 == 2) {
				    	checkOption = false;
				    }
				}
			    jobseeker.setSkillset(skillsetlist);
				System.out.println("Success");	
			    break;
			case("3"):
				System.out.println("This is all skillset you have:");
				for (String string : skillsetlist) {
					System.out.println(string+" ");
				}
				boolean checkOption1 = true;
				while(checkOption1) {
					if(skillsetlist.size() > 0) {
						for(int i = 0; i < skillsetlist.size(); i++) {
								System.out.print((i+1) + ". "+ skillsetlist.get(i) + " ");
						}
						System.out.println();
						System.out.println("Which skill would you want to delete,please input its number");
						int editskillnumber = sc.nextInt();
						boolean insideornot = true;
						for(int i = 0; i < skillsetlist.size(); i++) {
							System.out.print((i+1) + ". "+ skillsetlist.get(i) + " ");
							if(i+1 == editskillnumber){
								insideornot = false;
							}
						}	
						if(insideornot == false){
							skillsetlist.remove(editskillnumber -1);
							System.out.println("Skill deleted");
							System.out.println("Your current skills are:");
							for(int i = 0; i < skillsetlist.size(); i++) {
								System.out.print((i+1) + ". "+ skillsetlist.get(i) + " ");
							}
							System.out.println();
							System.out.println("Do you want to detele other skills?(1.Yes 2.No)");
							int option2 = 0; 
							option2 = sc.nextInt();
							if(option2 == 2) {
								checkOption = false;
							}
						}else{
							System.out.println("Sorry, please input valid number");
						}	
					}else {
						System.out.println("sorry, you have no skill to delete");
						checkOption = false;
					}
				}
				jobseeker.setSkillset(skillsetlist);
				System.out.println("Success");
					
		    	break;
			case("4"):
				break;
		}
	}

	public static void searchJob() {
		System.out.println("Welcome to search job function!\n"
				+ "Please enter your option:\n1.Search job by title \n2.Search job by keywords \n3.Go back to main menu");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		boolean searchJobjudge = true;
		switch(choice.trim()){
			case"1":
				while(searchJobjudge){
					System.out.println("Please input the job title:");
					String title = sc.nextLine();
					ArrayList<Job> joblist = DataStorage.getInstance().searchJobbytitle(title);
					if(joblist!=null){
						System.out.println("Here are the job found");
						for (Job job : joblist) {
							job.display();
							System.out.println("Do you wish to jump to apply job function ?\n1.Yes 2.No, please go to the main page");
							String choice1 = sc.nextLine();
							if (choice1.trim().equals("1")) {
								searchJobjudge = false;
								applyJob();
							} else if (choice1.trim().equals("2")) {
								System.out.println("Now go back to search job function\n");
								searchJobjudge = false;
								searchJob();
							}
						}
					}else{
						System.out.println("Sorry, no job found,Do you want to try again?\n1.Yes 2.No");
						String option = sc.nextLine();
						if(option.equals("2")){
							System.out.println("Now go back to the search menu");
							searchJobjudge = false;
							searchJob();
						}else if(!option.equals("1")){
							System.out.println("Sorry, doesn't have this option, now go back to the search menu");
							searchJobjudge = false;
							searchJob();
						}
					}
				}
				break;
			case"2":
				ArrayList<String> keywordslist = new ArrayList<>();
				boolean choice1 = true;
				while (choice1) {
					System.out.println("Please input the keywords");
					keywordslist.add(sc.nextLine());
					System.out.println("Do you wish to input more keyword for searching \n1.Yes 2.No");
					if (sc.nextLine().equals("2")) {
						choice1 = false;
					}
					if (keywordslist.size() > 3) {
						choice1 = false;
						System.out.println("You already input the maximum number of keywords");
					}
				}
				ArrayList<Job> joblist = DataStorage.getInstance().searchJobbykeywords(keywordslist);
				if(joblist != null){
					System.out.println("Here are the jobs found");
					for (Job job : joblist) {
						job.display();
					}
					System.out.println("Do you wish to jump to apply job function ?\n1.Yes 2.No, please go to the main page");
					String choice2 = sc.nextLine();
					if (choice2.trim().equals("1")) {
						searchJobjudge = false;
						applyJob();
					} else if (choice2.trim().equals("2")) {
						System.out.println("Now go back to search job function\n");
						searchJobjudge = false;
						searchJob();
					}
				}else{
					System.out.println("Sorry, no job found, now go back to search function...\n");
					searchJobjudge = false;
					searchJob();
				}
				break;
			case"3":
				
				break;
			default:
				System.out.println("Doesn't have this option, please try again");
				searchJob();
				break;
		}
	}
	

	public static void applyJob() {
		int jobId = -1 , recruiterId = -1;
		String firstname ="", surname = "", dob = "", mobile = "", currentOccupation = "", filePaths = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to apply job function!");
		boolean applyjudge = true;
		while(applyjudge){
			System.out.println("First, please input the the ID of the job");
			String strjobID = sc.nextLine();
			if(!strjobID.trim().equals("")){
			    jobId = Integer.parseInt(strjobID.trim());
				applyjudge = false;
			}else{
				System.out.println("Sorry, please input valid jobid");
			}
		}
		
		while(!applyjudge){
			System.out.println("Please input your first name :");
			firstname = sc.nextLine();
			System.out.println("Please input your last name");
			surname = sc.nextLine();
			if(!firstname.isEmpty()||!surname.isEmpty()){
				applyjudge = true;
			}else{
				System.out.println("Sorry, name can't be null, please input again");
			}
		}
		
		while(applyjudge){
			System.out.println("Please input your date of birth");
			dob = sc.nextLine();
			if(!dob.isEmpty()){
				applyjudge = false;
			}else{
				System.out.println("Sorry, date of birth can't be null, please input again");
			}
		}
		
		while(!applyjudge){
			System.out.println("Please input your moblie number");
			mobile = sc.nextLine();
			if(!mobile.isEmpty()){
				applyjudge = true;
			}else{
				System.out.println("Sorry, mobile can't be null, please input again");
			}
		}
		
		while(applyjudge){
			System.out.println("please input your current occupation");
			currentOccupation = sc.nextLine();
			if(!currentOccupation.isEmpty()){
				applyjudge = false;
			}else{
				System.out.println("Sorry, occupation can't be null, please input again");
			}
		}
		
		while(!applyjudge){
			System.out.println("Please input the path of files you want to upload");
			filePaths = sc.nextLine();
			if(!filePaths.isEmpty()){
				applyjudge = true;
			}else{
				System.out.println("Sorry, FilePath can't be null, please input again");
			}
		}
		boolean createappresult = ApplicationController.createApplication(jobId, recruiterId, firstname, surname, dob, mobile, currentOccupation, filePaths);
		if (createappresult) {
			System.out.println("Success");
		}else{
			System.out.println("Something wrong, please check");
		}
	}
}
