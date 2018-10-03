package model;

import java.util.ArrayList;

public class DataStorage {

	private static DataStorage instance;
	
	private static String currentUserType;
	private static int currentUserId;
	
	private static ArrayList<Account> accounts;

	private static ArrayList<JobSeeker> jobSeekers;
	private static ArrayList<Recruiter> recruiters;
	private static ArrayList<Administrator> administrators;

	private static ArrayList<Application> applications;
	private static ArrayList<Invitation> invitations;
	private static ArrayList<Job> jobs;

	static {
		instance = new DataStorage();
    }
	
	public static DataStorage getInstance() {
        return instance;
    }
	
	private DataStorage() {
		super();
	}
	
	public static void setCurrentUserType(String in) {
		currentUserType = in;
	}


	public static String getCurrentUserType() {
		return currentUserType;
	}


	public static int getCurrentUserId() {
		return currentUserId;
	}

	public static void setCurrentUserId(int in) {
		currentUserId = in;
	}

	public static void open() {
		accounts = TxtHandler.readAccounts();
		jobSeekers = TxtHandler.readJobSeekers();
		recruiters = TxtHandler.readRecruiters();
		administrators = TxtHandler.readAdministrators();
		applications = TxtHandler.readApplications();
		invitations = TxtHandler.readInvitations();
		jobs = TxtHandler.readJobs();
	}

	public static void close() {
		TxtHandler.writeAccounts(accounts);
		TxtHandler.writeJobSeekers(jobSeekers);
		TxtHandler.writeRecruiters(recruiters);
		TxtHandler.writeAdministrators(administrators);
		TxtHandler.writeApplications(applications);
		TxtHandler.writeInvitations(invitations);
		TxtHandler.writeJobs(jobs);
	}

	// Account/Authentication
	public static Account validateAccount(String username, String password) {
		for (Account account : accounts) {
			if (username.equals(account.getUsername() + "") && account.getPassword().equals(password)) {
				return account;
			}
		}
		return null;
	}

	public static String getUsertype(String username) {
		for (Account account : accounts) {
			if (username.equals(account.getUserId() + "")) {
				return account.getUserType();
			}
		}
		return null;
	}

	public static boolean register(Account accountIn) {
		int id = generateAccountId();
		for (Account account : accounts) {
			if (account.getUsername().equals(accountIn.getUsername())) {
				return false;
			}
			
		}
		accountIn.setUserId(id);
		accounts.add(accountIn);
		return true;
	}

	public static int generateAccountId() {
		int result = 0;
		while (true) {
			boolean exist = false;
			for (Account account : accounts) {
				if (account.getUserId() == result) {
					exist = true;
				}
			}
			if (exist) {
				result++;
			} else {
				return result;
			}
		}
	}

	// Job
	
	public static int generateJobId() {
		int result = 0;
		while (true) {
			boolean exist = false;
			for (Job job : jobs) {
				if (job.getJobId() == result) {
					exist = true;
				}
			}
			if (exist) {
				result++;
			} else {
				return result;
			}
		}
	}
	
	public static boolean createJob(Job job) {
		job.setJobId(generateJobId());
		jobs.add(job);
		Recruiter recruiter = searchRecruiterbyId(currentUserId);
		ArrayList<Integer> createdJobs = recruiter.getCreatedJobIds();
		createdJobs.add(job.getJobId());
		recruiter.setCreatedJobIds(createdJobs);
		editRecruiter(recruiter);
		return true;
	}

	public static ArrayList<Job> getCreatedJob(int userid) {
		Recruiter recruiter = searchRecruiterbyId(userid);
		ArrayList<Integer> jobIds = recruiter.getCreatedJobIds();
		ArrayList<Job> result = new ArrayList<>();
		if (jobIds == null || jobIds.isEmpty()) {
			return null;
		} else {
			for (int jobId : jobIds) {
				result.add(searchJobbyId(jobId));
			}
			return result;
		}
	}

	public static ArrayList<Job> searchJobbytitle(String title) {
		ArrayList<Job> result = new ArrayList<>();
		for (Job job : jobs) {
			if (job.getTitle().equals(title)) {
				result.add(job);
			}
		}
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result;
	}

	public static ArrayList<Job> searchJobbykeywords(ArrayList<String> inList) {
		ArrayList<Job> result = new ArrayList<>();
		for (Job job : jobs) {
			ArrayList<String> keywords = job.getKeywords();
			if (keywords.containsAll(inList)) {
				result.add(job);
			}
		}
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result;
	}

	public static ArrayList<Job> searchJobbycategories(ArrayList<String> inList) {
		ArrayList<Job> result = new ArrayList<>();
		for (Job job : jobs) {
			ArrayList<String> categories = job.getCategories();
			if (categories.containsAll(inList)) {
				result.add(job);
			}
		}
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result;
	}
	
	public static ArrayList<Job> findAppliedJobs(int jobSeekerId) {
		ArrayList<Job> result = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		for (Application application : applications) {
			if (application.getJobSeekerId() == jobSeekerId) {
				temp.add(application.getJobId());
			}
		}
		if (temp == null || temp.isEmpty()) {
			return null;
		}
		for (int jobId : temp) {
			result.add(searchJobbyId(jobId));
		}
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result;
	}

	public static boolean editJob(Job jobIn) {
		int index;
		for (index = 0; index < jobs.size(); index++) {
			if (jobs.get(index).getJobId() == jobIn.getJobId()) {
				jobs.set(index, jobIn);
				return true;
			}
		}
		return false;
	}

	public static boolean deleteJob(int jobId) {
		boolean step1 = false;
		for (int index = 0; index < jobs.size(); index++) {
			if (jobs.get(index).getJobId() == jobId) {
				jobs.remove(index);
				step1 = true;
			}
		}
		if (!step1) {
			return false;
		}
		boolean step2 = false;
		for (Recruiter recruiter : recruiters) {
			if (recruiter.getCreatedJobIds().contains(jobId)) {
				ArrayList<Integer> temp = recruiter.getCreatedJobIds();
				for (int i=0; i<temp.size();i++) {
					if (temp.get(i) == jobId) {
						temp.remove(i);
						step2 = true;
					}
				}
			}
		}
		if (!step2) {
			return false;
		}
		return false;
	}
	
	public static int generateInvitationId() {
		int result = 0;
		while (true) {
			boolean exist = false;
			for (Invitation invitation : invitations) {
				if (invitation.getInvitationId() == result) {
					exist = true;
				}
			}
			if (exist) {
				result++;
			} else {
				return result;
			}
		}
	}
	
	
	public static boolean createInviation(Invitation invitation) {
		invitation.setInvitationId(generateInvitationId());
		invitations.add(invitation);
		return true;
	}

	public static Job searchJobbyId(int id) {
		for (Job job : jobs) {
			if (id == job.getJobId()) {
				return job;
			}
		}
		return null;
	}

	public static Recruiter searchRecruiterbyId(int id) {
		for (Recruiter recruiter : recruiters) {
			if (id == recruiter.getRecruiterId()) {
				return recruiter;
			}
		}
		return null;
	}

	public static JobSeeker searchJobSeekerbyId(int id) {
		for (JobSeeker jobSeeker : jobSeekers) {
			if (jobSeeker.getJobSeekerId() == id) {
				return jobSeeker;
			}
		}
		return null;
	}
	
	public static Recruiter findJobCreator(int jobId) {
		for (Recruiter recruiter : recruiters) {
			if (recruiter.getCreatedJobIds().contains(jobId)) {
				return recruiter;
			}
		}
		return null;
	}
	
	public static void printAccount() {
		for (Account account : accounts) {
			System.out.println(account.getUserId() + "    " + account.getUsername() + "    " + account.getPassword());
		}
	}
	
	public static void printJob() {
		for (Job job : jobs) {
			System.out.println(job.getJobId() + "    " + job.getTitle() + "    " + job.getDescription());
		}
	}
	
	public static ArrayList<Invitation> findInvitation(int jobSeekerId) {
		ArrayList<Invitation> result = new ArrayList<>();
		for (Invitation invitation : invitations) {
			if (invitation.getReceiverId() == jobSeekerId) {
				result.add(invitation);
			}
		}
		return result;
	}
	
	public static boolean editJobSeeker(JobSeeker jobSeekerIn) {
		int index;
		for (index = 0; index < jobSeekers.size(); index++) {
			if (jobSeekers.get(index).getJobSeekerId() == jobSeekerIn.getJobSeekerId()) {
				jobSeekers.set(index, jobSeekerIn);
				return true;
			}
		}
		return false;
	}
	
	public static boolean editRecruiter(Recruiter recruiterIn) {
		int index;
		for (index = 0; index < recruiters.size(); index++) {
			if (recruiters.get(index).getRecruiterId() == recruiterIn.getRecruiterId()) {
				recruiters.set(index, recruiterIn);
				return true;
			}
		}
		return false;
	}
	
	public static int generateApplicationId() {
		int result = 0;
		while (true) {
			boolean exist = false;
			for (Application application : applications) {
				if (application.getApplicationId() == result) {
					exist = true;
				}
			}
			if (exist) {
				result++;
			} else {
				return result;
			}
		}
	}
	
	public static boolean createApplication(Application application) {
		application.setApplicationId(generateApplicationId());
		applications.add(application);
		return true;
	}
	
}
