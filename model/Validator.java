package model;


public class Validator {

	public static boolean jobTitle(String in) {
		return true;
	}
	
	public static boolean jobJobId(String in) {
		return isInt(in);
	}
	
	public static boolean jobKeywords(String in) {
		return true;
	}
	
	public static boolean jobCategories(String in) {
		return true;
	}
	
	public static boolean jobDescription(String in) {
		return true;
	}
	
	public static boolean jobAdvertisement(String in) {
		if (in.equalsIgnoreCase("true") || in.equalsIgnoreCase("false")) {
			return true;
		}
		return false;
	}
	
	public static boolean invatationInvatationId(String in) {
		return isInt(in);
	}
	
	public static boolean invatationMessage(String in) {
		return true;
	}
	
	public static boolean invatationSenderId(String in) {
		return isInt(in);
	}
	
	public static boolean invatationReceiverId(String in) {
		return isInt(in);
	}
	
	public static boolean applicationApplicationId(String in) {
		return isInt(in);
	}
	
	public static boolean applicationJobId(String in) {
		return isInt(in);
	}
	
	public static boolean applicationJobSeekerId(String in) {
		return isInt(in);
	}
	
	public static boolean applicationRecruiterId(String in) {
		return isInt(in);
	}
	
	public static boolean applicationFirstname(String in) {
		return true;
	}
	
	public static boolean applicationSurname(String in) {
		return true;
	}
	
	public static boolean applicationDob(String in) {
		return true;
	}
	
	public static boolean applicationMobile(String in) {
		return true;
	}
	
	public static boolean applicationCurrentOccupation(String in) {
		return true;
	}
	
	public static boolean applicationFilePaths(String in) {
		return true;
	}
	
	public static boolean accountUserId(String in) {
		return isInt(in);
	}

	public static boolean accountUsername(String in) {
		return true;
	}
	
	public static boolean accountPassword(String in) {
		return true;
	}
	
	public static boolean accountUserType(String in) {
		return true;
	}
	
	public static boolean accountEmail(String in) {
		return true;
	}
	
	public static boolean administratorAdministratorId(String in) {
		return isInt(in);
	}

	public static boolean administratorName(String in) {
		return true;
	}
	
	public static boolean recruiterRecruiterId(String in) {
		return isInt(in);
	}

	public static boolean recruiterCreatedJobIds(String in) {
		return isInt(in);
	}
	
	public static boolean jobseekerJobSeekerId(String in) {
		return isInt(in);
	}
	
	public static boolean jobseekerSkillset(String in) {
		return true;
	}
	
	
	
	private static boolean isInt(String in) {
		try {
			Integer.parseInt(in);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
