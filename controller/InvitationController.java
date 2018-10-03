package controller;

import java.util.ArrayList;

import model.DataStorage;
import model.Invitation;

public class InvitationController {
	public static boolean createdInvitation(DataStorage dataStorage,String message,int jobseekerid){
		Invitation invitation = new Invitation(dataStorage.generateInvitationId(),message,dataStorage.getCurrentUserId(),jobseekerid);
		boolean result = dataStorage.createInviation(invitation);
		return result;
	}
	
	public static ArrayList<Invitation> getInvitation(){
		ArrayList<Invitation> inviatationList  = DataStorage.getInstance().findInvitation(DataStorage.getInstance().getCurrentUserId());
		return inviatationList;
	}
}
