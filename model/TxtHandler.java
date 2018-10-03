package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.*;

public class TxtHandler {

	private static final String ATTRIBUTE_SEPARATOR = "``";
	private static final String ARRAYLIST_SEPARATOR = "`";
	private static final String DISPLAY_LINE_SEPARATOR = "-----------------------------------------";
	private static final String FILE_PATH_JOBS = "txt/jobs.txt";
	private static final String FILE_PATH_INVITATIONS = "txt/invitations.txt";
	private static final String FILE_PATH_APPLICATION = "txt/applications.txt";
	private static final String FILE_PATH_ACCOUNTS = "txt/accounts.txt";
	private static final String FILE_PATH_ADMINISTRATOR = "txt/administrators.txt";
	private static final String FILE_PATH_RECRUITERS = "txt/recruiters.txt";
	private static final String FILE_PATH_JOBSEEKERS = "txt/jobseekers.txt";
	
	public static ArrayList<Job> readJobs() {
		boolean normal = true;
		ArrayList<Job> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_JOBS);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from jobs.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 6) {
					boolean pass1 = Validator.jobTitle(details[0]);
					boolean pass2 = Validator.jobJobId(details[1]);
					boolean pass3 = true;
					if (details[2].contains(ARRAYLIST_SEPARATOR)) {
						String[] temp = details[2].split(ARRAYLIST_SEPARATOR);
						for (String i : temp) {
							if (!Validator.jobKeywords(i)) {
								pass3 = false;
							}
						}
					} else {
						pass3 = Validator.jobKeywords(details[2]);
					}

					boolean pass4 = true;
					if (details[3].contains(ARRAYLIST_SEPARATOR)) {
						String[] temp = details[3].split(ARRAYLIST_SEPARATOR);
						for (String i : temp) {
							if (!Validator.jobCategories(i)) {
								pass4 = false;
							}
						}
					} else {
						pass4 = Validator.jobCategories(details[3]);
					}

					boolean pass5 = Validator.jobDescription(details[4]);
					boolean pass6 = Validator.jobAdvertisement(details[5]);

					// export lines from jobs.txt to output
					if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6) {
						ArrayList<String> keywords = new ArrayList<>();
						if (details[2].contains(ARRAYLIST_SEPARATOR)) {
							String[] keywordsArray = details[2].split(ARRAYLIST_SEPARATOR);
							for (String i : keywordsArray) {
								keywords.add(i);
							}
						} else {
							keywords.add(details[2]);
						}

						ArrayList<String> categories = new ArrayList<>();
						if (details[3].contains(ARRAYLIST_SEPARATOR)) {
							String[] categoriesArray = details[3].split(ARRAYLIST_SEPARATOR);
							for (String i : categoriesArray) {
								categories.add(i);
							}
						} else {
							categories.add(details[1]);
						}

						boolean advertisement;
						if (details[5].equalsIgnoreCase("true")) {
							advertisement = true;
						} else {
							advertisement = false;
						}
						result.add(
								new Job(details[0],Integer.parseInt(details[1]), keywords, categories, details[4], advertisement));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " title";
						}
						if (!pass2) {
							errorStr += " jobId";
						}
						if (!pass3) {
							errorStr += " keywords";
						}
						if (!pass4) {
							errorStr += " categories";
						}
						if (!pass5) {
							errorStr += " description";
						}
						if (!pass6) {
							errorStr += " advertisement";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from jobs.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeJobs(ArrayList<Job> jobs) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_JOBS);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Job job : jobs) {
				ArrayList<String> keywords = job.getKeywords();
				ArrayList<String> categories = job.getCategories();
				String keywordsStr = "";
				String categoriesStr = "";
				for (String i : keywords) {
					keywordsStr = keywordsStr + i + ARRAYLIST_SEPARATOR;
				}
				keywordsStr = keywordsStr.substring(0, keywordsStr.length() - 1);
				for (String i : categories) {
					categoriesStr = categoriesStr + i + ARRAYLIST_SEPARATOR;
				}
				categoriesStr = categoriesStr.substring(0, categoriesStr.length() - 1);
				writer.println(job.getTitle() + ATTRIBUTE_SEPARATOR + job.getJobId() + ATTRIBUTE_SEPARATOR + keywordsStr + ATTRIBUTE_SEPARATOR + categoriesStr
						+ ATTRIBUTE_SEPARATOR + job.getDescription() + ATTRIBUTE_SEPARATOR + job.isAdvertisement());
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}

	public static ArrayList<Invitation> readInvitations() {
		boolean normal = true;
		ArrayList<Invitation> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_INVITATIONS);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from invitations.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 4) {
					boolean pass1 = Validator.invatationInvatationId(details[0]);
					boolean pass2 = Validator.invatationMessage(details[1]);
					boolean pass3 = Validator.invatationSenderId(details[2]);
					boolean pass4 = Validator.invatationReceiverId(details[3]);

					// export lines from jobs.txt to output
					if (pass1 && pass2 && pass3 && pass4) {
						result.add(new Invitation(Integer.parseInt(details[0]), details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3])));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " invitationId";
						}
						if (!pass2) {
							errorStr += " message";
						}
						if (!pass3) {
							errorStr += " senderId";
						}
						if (!pass4) {
							errorStr += " receiverId";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from invitations.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeInvitations(ArrayList<Invitation> invitations) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_INVITATIONS);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Invitation invitation : invitations) {
				writer.println(invitation.getInvitationId() + ATTRIBUTE_SEPARATOR + invitation.getMessage() + ATTRIBUTE_SEPARATOR + invitation.getSenderId()
						+ ATTRIBUTE_SEPARATOR + invitation.getReceiverId());
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}

	public static ArrayList<Application> readApplications() {
		boolean normal = true;
		ArrayList<Application> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_APPLICATION);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from invitations.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 10) {
					boolean pass1 = Validator.applicationApplicationId(details[0]);
					boolean pass2 = Validator.applicationJobId(details[1]);
					boolean pass3 = Validator.applicationJobSeekerId(details[2]);
					boolean pass4 = Validator.applicationRecruiterId(details[3]);
					boolean pass5 = Validator.applicationFirstname(details[4]);
					boolean pass6 = Validator.applicationSurname(details[5]);
					boolean pass7 = Validator.applicationDob(details[6]);
					boolean pass8 = Validator.applicationMobile(details[7]);
					boolean pass9 = Validator.applicationCurrentOccupation(details[8]);
					boolean pass10 = Validator.applicationFilePaths(details[9]);

					// export lines from jobs.txt to output
					if (pass1 && pass2 && pass3 && pass4 && pass5 && pass6 && pass7 && pass8 && pass9 && pass10) {
						result.add(new Application(Integer.parseInt(details[0]), Integer.parseInt(details[1]), Integer.parseInt(details[2]), Integer.parseInt(details[3]), 
								details[4], details[5], details[6], details[7], details[8], details[9]));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " applicationId";
						}
						if (!pass2) {
							errorStr += " jobId";
						}
						if (!pass3) {
							errorStr += " jobSeekerId";
						}
						if (!pass4) {
							errorStr += " recruiterId";
						}
						if (!pass5) {
							errorStr += " firstname";
						}
						if (!pass6) {
							errorStr += " surname";
						}
						if (!pass7) {
							errorStr += " dob";
						}
						if (!pass8) {
							errorStr += " mobile";
						}
						if (!pass9) {
							errorStr += " currentOccupation";
						}
						if (!pass10) {
							errorStr += " filePaths";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from application.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeApplications(ArrayList<Application> applications) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_APPLICATION);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Application application : applications) {
				writer.println(application.getApplicationId() + ATTRIBUTE_SEPARATOR + application.getJobId() + ATTRIBUTE_SEPARATOR
						+ application.getJobSeekerId() + ATTRIBUTE_SEPARATOR + application.getRecruiterId() + ATTRIBUTE_SEPARATOR
						+ application.getFirstname() + ATTRIBUTE_SEPARATOR + application.getSurname() + ATTRIBUTE_SEPARATOR
						+ application.getDob() + ATTRIBUTE_SEPARATOR + application.getMobile() + ATTRIBUTE_SEPARATOR 
						+ application.getCurrentOccupation() + ATTRIBUTE_SEPARATOR + application.getFilePaths());
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}
	
	public static ArrayList<Account> readAccounts() {
		boolean normal = true;
		ArrayList<Account> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_ACCOUNTS);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from invitations.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 5) {
					boolean pass1 = Validator.accountUserId(details[0]);
					boolean pass2 = Validator.accountUsername(details[1]);
					boolean pass3 = Validator.accountPassword(details[2]);
					boolean pass4 = Validator.accountUserType(details[3]);
					boolean pass5 = Validator.accountEmail(details[4]);
					
					// export lines from jobs.txt to output
					if (pass1 && pass2 && pass3 && pass4 && pass5) {
						result.add(new Account(Integer.parseInt(details[0]), details[1], details[2], details[3], details[4]));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " userId";
						}
						if (!pass2) {
							errorStr += " username";
						}
						if (!pass3) {
							errorStr += " password";
						}
						if (!pass4) {
							errorStr += " userType";
						}
						if (!pass5) {
							errorStr += " email";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from accounts.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeAccounts(ArrayList<Account> accounts) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_ACCOUNTS);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Account account : accounts) {
				writer.println(account.getUserId() + ATTRIBUTE_SEPARATOR + account.getUsername() + ATTRIBUTE_SEPARATOR + account.getPassword()
						+ ATTRIBUTE_SEPARATOR + account.getUserType() + ATTRIBUTE_SEPARATOR + account.getEmail());
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}
	
	public static ArrayList<Administrator> readAdministrators() {
		boolean normal = true;
		ArrayList<Administrator> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_ADMINISTRATOR);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from invitations.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 2) {
					boolean pass1 = Validator.administratorAdministratorId(details[0]);
					boolean pass2 = Validator.administratorName(details[1]);
					
					// export lines from jobs.txt to output
					if (pass1 && pass2) {
						result.add(new Administrator(Integer.parseInt(details[0]), details[1]));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " administratorId";
						}
						if (!pass2) {
							errorStr += " name";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from administrator.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeAdministrators(ArrayList<Administrator> administrators) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_ADMINISTRATOR);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Administrator administrator : administrators) {
				writer.println(administrator.getAdministratorId() + ATTRIBUTE_SEPARATOR + administrator.getName());
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}
	
	
	
	public static ArrayList<Recruiter> readRecruiters() {
		boolean normal = true;
		ArrayList<Recruiter> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_RECRUITERS);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from jobs.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 2) {
					boolean pass1 = Validator.recruiterRecruiterId(details[0]);
					boolean pass2 = true;
					if (details[1].contains(ARRAYLIST_SEPARATOR)) {
						String[] temp = details[1].split(ARRAYLIST_SEPARATOR);
						for (String i : temp) {
							if (!Validator.recruiterCreatedJobIds(i)) {
								pass2 = false;
							}
						}
					} else {
						pass2 = Validator.recruiterCreatedJobIds(details[1]);
					}

					// export lines from jobs.txt to output
					if (pass1 && pass2) {
						ArrayList<Integer> createdJobIds = new ArrayList<>();
						if (details[1].contains(ARRAYLIST_SEPARATOR)) {
							String[] createdJobIdsArray = details[1].split(ARRAYLIST_SEPARATOR);
							for (String i : createdJobIdsArray) {
								createdJobIds.add(Integer.parseInt(i));
							}
						} else {
							createdJobIds.add(Integer.parseInt(details[1]));
						}

						result.add(new Recruiter(Integer.parseInt(details[0]), createdJobIds));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " recruiterId";
						}
						if (!pass2) {
							errorStr += " createdJobIds";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from recruiters.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeRecruiters(ArrayList<Recruiter> recruiters) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_RECRUITERS);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (Recruiter recruiter : recruiters) {
				ArrayList<Integer> createdJobIds = recruiter.getCreatedJobIds();
				String createdJobIdsStr = "";
				for (Integer i : createdJobIds) {
					createdJobIdsStr = createdJobIdsStr + i + ARRAYLIST_SEPARATOR;
				}
				createdJobIdsStr = createdJobIdsStr.substring(0, createdJobIdsStr.length() - 1);
				writer.println(recruiter.getRecruiterId() + ATTRIBUTE_SEPARATOR + createdJobIdsStr);
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}
	
	public static ArrayList<JobSeeker> readJobSeekers() {
		boolean normal = true;
		ArrayList<JobSeeker> result = new ArrayList<>();
		ArrayList<String> errorLines = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(FILE_PATH_JOBSEEKERS);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = "";
			while ((line = reader.readLine()) != null) {
				// do the following to each line/record
				// export line/record from jobs.txt to String[] details and validate the attributes
				String[] details = line.split(ATTRIBUTE_SEPARATOR);
				if (details.length == 2) {
					boolean pass1 = Validator.jobseekerJobSeekerId(details[0]);
					boolean pass2 = true;
					if (details[1].contains(ARRAYLIST_SEPARATOR)) {
						String[] temp = details[1].split(ARRAYLIST_SEPARATOR);
						for (String i : temp) {
							if (!Validator.jobseekerSkillset(i)) {
								pass2 = false;
							}
						}
					} else {
						pass2 = Validator.jobseekerSkillset(details[1]);
					}

					// export lines from jobs.txt to output
					if (pass1 && pass2) {
						ArrayList<String> skillset = new ArrayList<>();
						if (details[1].contains(ARRAYLIST_SEPARATOR)) {
							String[] skillsetArray = details[1].split(ARRAYLIST_SEPARATOR);
							for (String i : skillsetArray) {
								skillset.add(i);
							}
						} else {
							skillset.add(details[1]);
						}

						result.add(new JobSeeker(Integer.parseInt(details[0]), skillset));
					} else {
						normal = false;
						errorLines.add(DISPLAY_LINE_SEPARATOR);
						errorLines.add("Error line content: " + line);
						String errorStr = "";
						if (!pass1) {
							errorStr += " jobSeekerId";
						}
						if (!pass2) {
							errorStr += " skillset";
						}
						errorLines.add("check: " + errorStr);
					}
				} else {
					normal = false;
					errorLines.add(DISPLAY_LINE_SEPARATOR);
					errorLines.add("Error line content: " + line);
					errorLines.add("check the format");
				}
			}
			if (!normal) {
				System.out.println("Error occured importing from jobseekers.txt");
				for (String str : errorLines) {
					System.out.println(str);
				}
				System.out.println(DISPLAY_LINE_SEPARATOR);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("No existing file found!");
			System.out.println("A new file will be created.");
		}
		return result;
	}

	public static boolean writeJobSeekers(ArrayList<JobSeeker> jobSeekers) {
		boolean success = false;
		try {
			FileWriter fileWriter = new FileWriter(FILE_PATH_JOBSEEKERS);
			PrintWriter writer = new PrintWriter(fileWriter);
			for (JobSeeker jobSeeker : jobSeekers) {
				ArrayList<String> skillset = jobSeeker.getSkillset();
				String skillsetStr = "";
				for (String i : skillset) {
					skillsetStr = skillsetStr + i + ARRAYLIST_SEPARATOR;
				}
				skillsetStr = skillsetStr.substring(0, skillsetStr.length() - 1);
				writer.println(jobSeeker.getJobSeekerId() + ATTRIBUTE_SEPARATOR + skillsetStr);
			}
			writer.close();
			success = true;
		} catch (IOException e) {
			System.out.println("Error writing to file!");
			success = false;
		}
		return success;
	}
	
}
