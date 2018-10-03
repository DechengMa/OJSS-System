package view;

import model.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.AuthenticationController;
import controller.JobController;
import model.DataStorage;

public class LoginandRegisiterUI {
	// static Display display;
	public static void main(String[] args) {
		DataStorage.getInstance().open();
		mainFunction();
	}
	
	public static void mainFunction(){
		while(true){
			System.out.println("Welcome to OJSS System, what would you like to do today?"
					+ "\n1.Login \n2.Register \n3.Exit");
			Scanner sc = new Scanner(System.in);
			String option = sc.nextLine();
			
			if (option.trim().equals("1")){
				logInfunction();
			}else if(option.trim().equals("2")) {
				regisiterFunction();
			}else if(option.trim().equals("3")){
				DataStorage.getInstance().close();
				System.out.println("Bye Bye,see you later!");
				System.exit(0);
				break;
			}else{
				System.out.println("Sorry, we don't have this option,please input again");
			}
		}
	}

	private static void regisiterFunction() {
		AuthenticationController authenticationController = new AuthenticationController();
		boolean result = false;
		System.out.println("Welcome to regisiter Page! Firstly,which type of user are you going to regisiter? "
				+ "\n1.Job Applyer \n2.Recruiter");
		Scanner sc = new Scanner(System.in);
		int usertypeoption = sc.nextInt();
		sc.nextLine();
		while (result == false) {
			System.out.println("Please input your username");
			String username = sc.nextLine();
			System.out.println("Please input your password");
			String password = sc.nextLine();
			String usertype = "";
			if(usertypeoption == 1){
				 usertype = "JobSeeker";
			}else if(usertypeoption == 2){
				 usertype = "Recruiter";
			}
			System.out.println("Please input your email");
			String email = sc.nextLine();
			result = authenticationController.regisiter(username, password, usertype,email);
//			DataStorage.getInstance().printAccount();
			if (result == false) {
				System.out.println("Sorry, the username has already been used.Do you want to try again?"
						+ "\n1.Try again 2.Go back to the main page");
				String choice = sc.nextLine();
				if (choice.equals("2")) {
					result = true;
				}
			} else {
				System.out.println("Success! Now back to the main page...\n");
				result = true;
			}
		}
	}

	private static void logInfunction() {
		boolean result1 = true;
		System.out.println("Welcome to login page! ");
		Scanner sc = new Scanner(System.in);
		while (result1) {
			System.out.println("Plz input username");
			String username = sc.nextLine();
			System.out.println("Plz input password");
			String password = sc.nextLine();
			Account account = AuthenticationController.volidateaccount(username, password);
			if(account != null){
				DataStorage.getInstance().setCurrentUserType(account.getUserType());
				DataStorage.getInstance().setCurrentUserId(account.getUserId());
				switch (DataStorage.getInstance().getCurrentUserType()) {
					case ("Recruiter"):
						System.out.println("Recruiter");
						RecruiterUI.recruiterFunction();
						result1 = false;
						break;
					case ("JobSeeker"):
						System.out.println("Jobapplyer");
						JobseekerUI.jobSeekerFunction();
						result1 = false;
						break;
				}
			}else{
				System.out.println("Username or password is wrong, do you wish to try again? \n1.Yes 2.No");
				String option = sc.nextLine();
				if(option.equals("2")){
					break;
				}else if(!option.equals("1")){
					System.out.println("Sorry, doen't have this option, we assume you want to try again");
				}
			}
		}
	}

}
