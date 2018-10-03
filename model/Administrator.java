package model;

public class Administrator {

	private int administratorId;
	private String name;
	
	public Administrator(int administratorId, String name) {
		super();
		this.administratorId = administratorId;
		this.name = name;
	}
	public int getAdministratorId() {
		return administratorId;
	}
	public void setAdministratorId(int administratorId) {
		this.administratorId = administratorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
