package application;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Manager extends Person {
	private int managerID;
	private ArrayList<Integer> contractIDs;
	private static int managerIDcounter;
	private static String[] headers = {"Name" , "Surname" , "Weight" , "Height" , "Birthdate" ,"Salary" , "Expenditure" , "Star" , "Phone Number" , "E-Mail Address", "Contract IDs"};
	
	public Manager(String[] managerInfo) {
		super(managerInfo);
		contractIDs = new ArrayList<>();
		managerID = managerIDcounter;
		managerIDcounter++;
		if(!managerInfo[10].equals("-")) {
			String[] ctIDs = managerInfo[10].split(" ");
			for (int i = 0; i < ctIDs.length; i++) {
				contractIDs.add(Integer.parseInt(ctIDs[i]));
			}
		}
	}
	
	
	public static int getManagerIDcounter() {
		return managerIDcounter;
	}


	public static void setManagerIDcounter(int managerIDcounter) {
		Manager.managerIDcounter = managerIDcounter;
	}


	public String[] tableRow() {
        String[] str = {integerListToTextForWriteFile(contractIDs)};
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
		return super.toTextForWriteFile() + ";" + integerListToTextForWriteFile(contractIDs) + "\n";
	}
	
	public boolean checkAvailable() {return false;}


	public static String[] getHeaders() {
		return headers;
	}

	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public ArrayList<Integer> getContractIDs() {
		return contractIDs;
	}
	public void setContractIDs(ArrayList<Integer> contractIDs) {
		this.contractIDs = contractIDs;
	}
	

}

