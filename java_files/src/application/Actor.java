package application;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Actor extends Person{
	private int actorID;
	private static int actorIDcounter;
	private static String[] headers = {"Name" , "Surname" , "Weight" , "Height" , "Birthdate" ,"Salary" , "Expenditure" , "Star" , "Phone Number" , "E-Mail Address","Manager ID","Available","Movie IDs","Genres","Image File"};

	private int managerID;
	private boolean available;
	private ArrayList<Integer> movieIDs;
	private String wellGenre;
	private String imageFile;
	
	public Actor(String[] actorInfo) {
		super(actorInfo);
		actorID = actorIDcounter;
		actorIDcounter++;
		
		if(!actorInfo[10].equals("-") && !actorInfo[12].equals("-1")) {
			this.managerID = Integer.parseInt(actorInfo[10]);
		}
		
		if(actorInfo[11].equalsIgnoreCase("true"))
			this.available = true;
		else if(actorInfo[11].equalsIgnoreCase("false"))
			this.available = false;
		
		movieIDs = new ArrayList<>();
		if(!actorInfo[12].equals("-") && !actorInfo[12].equals("-1")) {
			String[] mvIDs = actorInfo[12].split(" ");
			for (int i = 0; i < mvIDs.length; i++) {
				movieIDs.add(Integer.parseInt(mvIDs[i]));
			}
		}
		this.wellGenre = actorInfo[13];
		this.imageFile = actorInfo[14];
		
	}
	public String[] tableRow() {
        String[] str = {Integer.toString(managerID),Boolean.toString(available),integerListToTextForWriteFile(movieIDs),this.wellGenre,this.imageFile};
        String[] st = mergeTwoStringArray(super.tableRow(),str);
        return st;
    }
	
	public String integerListToTextForWriteFile(ArrayList<Integer> IDs) {
		String IDsText = null;
		boolean flag = true;
		for (int i = 0; i < IDs.size(); i++) {
			flag = false;
			if(IDsText != null) 
				IDsText = IDsText + IDs.get(i).toString();
			else
				IDsText =  IDs.get(i).toString();
				
			if(i+1<IDs.size())
				IDsText = IDsText + " ";
		}
		if(flag)
			IDsText = "-";
		return IDsText;
	}
	
	private String[] mergeTwoStringArray(String[] st, String[] st2) {
        int stlen = st.length;
        int st2len = st2.length;

        @SuppressWarnings("unchecked")
        String[] st3 = (String[]) Array.newInstance(st.getClass().getComponentType(), stlen + st2len);
        System.arraycopy(st, 0, st3, 0, stlen);
        System.arraycopy(st2, 0, st3, stlen, st2len);

        return st3;
	}
	
	public String toTextForWriteFile() {
		return super.toTextForWriteFile() + ";" + this.managerID + ";" + this.available + ";" + integerListToTextForWriteFile(movieIDs) + ";" + this.wellGenre + ";" + this.imageFile + "\n";
	}
	
	public int getActorID() {
		return actorID;
	}
	public void setActorID(int actorID) {
		this.actorID = actorID;
	}
	public ArrayList<Integer> getMovieIDs() {
		return movieIDs;
	}

	public void setMovieIDs(ArrayList<Integer> movieIDs) {
		this.movieIDs = movieIDs;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public static String[] getHeaders() {
		return headers;
	}
	public String getWellGenre() {
		return wellGenre;
	}
	public void setWellGenre(String wellGenre) {
		this.wellGenre = wellGenre;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public static int getActorIDcounter() {
		return actorIDcounter;
	}
	public static void setActorIDcounter(int actorIDcounter) {
		Actor.actorIDcounter = actorIDcounter;
	}
	
	
}
