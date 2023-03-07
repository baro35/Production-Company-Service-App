package application;

public class Admin {
	private int adminID;
	private String username;
	private String password;
	private static String[] headers = {"Username","Password"};
	private static int adminIdCounter;

	public Admin(String[] adminInfo) {
		this.adminID = adminIdCounter;
		adminIdCounter++;
		this.username = adminInfo[0];
		this.password = adminInfo[1];
	}
	
	
	public static int getAdminIdCounter() {
		return adminIdCounter;
	}


	public static void setAdminIdCounter(int adminIdCounter) {
		Admin.adminIdCounter = adminIdCounter;
	}


	public String[] tableRow() {
        String[] st = {username,password};
        return st;
    }
	
	public String toTextForWriteFile() {
		return this.username + ";" + this.password + "\n";
	}

	
	public static String[] getHeaders() {
		return headers;
	}

	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean addActor(Actor actor) {return false;}
	public boolean addPerson(Person person) {return false;}
	public boolean addManager(Manager manager) {return false;}
	public boolean addStudio(Studio studio) {return false;}
}

