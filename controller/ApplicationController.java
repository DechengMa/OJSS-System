package controller;

import model.Application;
import model.DataStorage;

public class ApplicationController {
	public static boolean createApplication(int jobId, int recruiterId,String firstname,String surname,String dob,String mobile,String currentOccupation,String filePaths){
		Application application = new Application(DataStorage.getInstance().generateInvitationId(), jobId, DataStorage.getInstance().getCurrentUserId(), recruiterId, firstname, surname, dob, mobile, currentOccupation, filePaths);
		boolean result = DataStorage.getInstance().createApplication(application);
		return result;
	}
}
