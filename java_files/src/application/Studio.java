package application;

import java.util.ArrayList;

public class Studio {
	private int studioID;
	private static int studioIDcounter;
	private static String[] headers = {"Trade Name","Headquarters","Industry","Founded","Founders","Movie IDs"};
	private String tradeName;
	private String headquarters;
	private String industry;
	private Date founded;
	private String founders;
	private ArrayList<Integer> movieIDs;
	
	public Studio(String[] studioInfo) {

		studioID = studioIDcounter;
		studioIDcounter++;

		this.tradeName = studioInfo[0];
		this.headquarters = studioInfo[1];
		this.industry = studioInfo[2];
		this.founded = new Date(studioInfo[3].split(":")[0],studioInfo[3].split(":")[1],studioInfo[3].split(":")[2]);
		this.founders = studioInfo[4];

		movieIDs = new ArrayList<>();
		if(!studioInfo[5].equals("-")) {
			String[] mvIDs = studioInfo[5].split(" ");
			for (int i = 0; i < mvIDs.length; i++) {
				movieIDs.add(Integer.parseInt(mvIDs[i]));
			}
		}
	}
	
	public static int getStudioIDcounter() {
		return studioIDcounter;
	}

	public static void setStudioIDcounter(int studioIDcounter) {
		Studio.studioIDcounter = studioIDcounter;
	}

	public String[] tableRow() {
		String[] st = {tradeName,headquarters,industry,founded.toString(),founders,integerListToTextForWriteFile(movieIDs)};
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
	
	public String toTextForWriteFile() {
		return this.tradeName + ";" + this.headquarters + ";" + this.industry + ";" + this.founded.toString() + ";" + this.founders + ";" + integerListToTextForWriteFile(movieIDs)  + "\n";
	}

	public int getStudioID() {
		return studioID;
	}

	public void setStudioID(int studioID) {
		this.studioID = studioID;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public String getFounders() {
		return founders;
	}

	public void setFounders(String founders) {
		this.founders = founders;
	}

	public ArrayList<Integer> getMovieIDs() {
		return movieIDs;
	}

	public void setMovieIDs(ArrayList<Integer> movieIDs) {
		this.movieIDs = movieIDs;
	}

	public static String[] getHeaders() {
		return headers;
	}
	
}