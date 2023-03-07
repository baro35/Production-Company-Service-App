package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Producer extends Person {
	private int producerID;
	private ArrayList<Integer> movieIDs; 
	private ArrayList<Integer> contractIDs;
	private static int producerIDcounter;	
	private String username;
	private String password;
	private static String[] headers = {"Name" , "Surname" , "Weight" , "Height" , "Birthdate" ,"Salary" , "Expenditure" , "Star" , "Phone Number" , "E-Mail Address","Movie IDs", "Contract IDs", "Username", "Password"};

	public Producer(String name, String surname, double budget, String username, String password) {
		super(name,surname,budget);
		this.username = username;
		this.password = password;
		producerID = producerIDcounter;
		producerIDcounter++;
		movieIDs = new ArrayList<>();
		contractIDs = new ArrayList<>();
	}

	public Producer(String[] producerInfo) {
		super(producerInfo);
		producerID = producerIDcounter;
		producerIDcounter++;
		movieIDs = new ArrayList<>();
		contractIDs = new ArrayList<>();
		if(!producerInfo[10].equals("-")) {
			String[] mvIDs = producerInfo[10].split(" ");
			for (int i = 0; i < mvIDs.length; i++) {
				movieIDs.add(Integer.parseInt(mvIDs[i]));
			}
		}
		
		if(!producerInfo[11].equals("-")) {
			String[] ctIDs = producerInfo[11].split(" ");
			for (int i = 0; i < ctIDs.length; i++) {
				contractIDs.add(Integer.parseInt(ctIDs[i]));
			}
		}
		
		this.username = producerInfo[12];
		this.password = producerInfo[13];
	}
	
	public String[] tableRow() {
        String[] str = {integerListToTextForWriteFile(movieIDs), integerListToTextForWriteFile(contractIDs), username, password};
        String[] st = mergeTwoStringArray(super.tableRow(),str);
        return st;
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
	
	public static int getProducerIDcounter() {
		return producerIDcounter;
	}

	public static void setProducerIDcounter(int producerIDcounter) {
		Producer.producerIDcounter = producerIDcounter;
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
	
	public String toTextForWriteFile() {
		return super.toTextForWriteFile() + ";" + integerListToTextForWriteFile(movieIDs) + ";" + integerListToTextForWriteFile(contractIDs) + ";" + username + ";" + password + "\n";
	}	
	
	public boolean hireStudio(Studio studio) {return false;}
	public boolean hireActor(Actor actor) {return false;}
	public boolean hirePerson(Person Person) {return false;}
	public boolean produceMovie(Movie movie) {return false;}


	public int getProducerID() {
		return producerID;
	}
	public void setProducerID(int producerID) {
		this.producerID = producerID;
	}
	public ArrayList<Integer> getMovieIDs() {
		return movieIDs;
	}
	public void setMovieIDs(ArrayList<Integer> movieIDs) {
		this.movieIDs = movieIDs;
	}
	public ArrayList<Integer> getContractIDs() {
		return contractIDs;
	}
	public void setContractIDs(ArrayList<Integer> contractIDs) {
		this.contractIDs = contractIDs;
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

	public static String[] getHeaders() {
		return headers;
	}

	
}