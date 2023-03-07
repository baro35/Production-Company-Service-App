package application;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employee extends Person{
	private int employeeID;
	private static int employeeIDCounter;
	private String workArea;
	private ArrayList<Integer> movieIDs;
	private static String[] headers = {"Name" , "Surname" , "Weight" , "Height" , "Birthdate" ,"Salary" , "Expenditure" , "Star" , "Phone Number" , "E-Mail Address","Work Area","Movie IDs"};
	
	public Employee(String[] employeeInfo) {
		super(employeeInfo);
		this.workArea = employeeInfo[10];
		employeeID = employeeIDCounter;
		employeeIDCounter++;
		
		movieIDs = new ArrayList<>();
		if(!employeeInfo[11].equals("-") && !employeeInfo[11].equals("-1")) {
			String[] mvIDs = employeeInfo[11].split(" ");
			for (int i = 0; i < mvIDs.length; i++) {
				movieIDs.add(Integer.parseInt(mvIDs[i]));
			}
		}
	}
	
	public String[] tableRow() {
        String[] str = {workArea,integerListToTextForWriteFile(movieIDs)};
        String[] st = mergeTwoStringArray(super.tableRow(),str);
        return st;
    }
	
	private String[] movieTable() {
		String[] str ;
		if(movieIDs.size() != 0) {
			str = new String[movieIDs.size()];
			for (int i = 0; i < str.length; i++) {
				str[i] = application.DB.getMovies().get(movieIDs.get(i)).getTitle();
			}
		}else {
			str = new String[1];
			str[0] = "-";
		}
		return str;
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
	
	public static String[] getHeaders() {
		return headers;
	}
	
	public static int getEmployeeIDCounter() {
		return employeeIDCounter;
	}

	public static void setEmployeeIDCounter(int employeeIDCounter) {
		Employee.employeeIDCounter = employeeIDCounter;
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
		return super.toTextForWriteFile() + ";" + this.workArea +";"+ integerListToTextForWriteFile(movieIDs) +"\n";
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public ArrayList<Integer> getMovieIDs() {
		return movieIDs;
	}

	public void setMovieIDs(ArrayList<Integer> movieIDs) {
		this.movieIDs = movieIDs;
	}
	
	
}
