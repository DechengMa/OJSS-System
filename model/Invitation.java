package model;

public class Invitation {
	
	private int invitationId;
	private String message;
	private int senderId;
	private int receiverId;
	
	public Invitation(int invitationId, String message, int senderId, int receiverId) {
		super();
		this.invitationId = invitationId;
		this.message = message;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}
	public int getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(int invitationId) {
		this.invitationId = invitationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public void display() {
		System.out.println("invitationId:" + invitationId + " message:" + message + " senderId:" + senderId + " receiverId:" + receiverId);
		System.out.println();
	}
	
	
}
