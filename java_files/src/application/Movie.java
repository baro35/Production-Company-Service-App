package application;

import java.util.ArrayList;

public class Movie {
	
	private String title;
	private Date startDate;
	private Date endDate;
	private String genre;
	private short duration;
	private int budget;
	private int boxOffice;
	private int expectedStar;
	private int producerID; 
	private int studioID; 
	private ArrayList<Integer> actorIDs;
	private ArrayList<Integer> employeeIDs;
	private static String[] headers = {"Title","Start Date","End Date","Genre","Duration","Budget","Box Office","Expected Star","Producer ID","Studio ID","Actor IDs","Employee IDs"};
	
	private int movieID;
	private static int movieIDcounter;
	public Movie(String[] movieInfo) {
		this.title = movieInfo[0];
		this.startDate = new Date(movieInfo[1].split(":")[0],movieInfo[1].split(":")[1],movieInfo[1].split(":")[2]);
		this.endDate = new Date(movieInfo[2].split(":")[0],movieInfo[2].split(":")[1],movieInfo[2].split(":")[2]);
		this.genre = movieInfo[3];
		this.duration = Short.parseShort(movieInfo[4]);
		this.budget = Integer.parseInt(movieInfo[5]);
		this.boxOffice = Integer.parseInt(movieInfo[6]);
		this.expectedStar = Integer.parseInt(movieInfo[7]);
		this.producerID = Integer.parseInt(movieInfo[8]);
		this.studioID = Integer.parseInt(movieInfo[9]);

		
		actorIDs = new ArrayList<>();
		if(!movieInfo[10].equals("-")) {
			String[] arIDs = movieInfo[10].split(" ");
			for (int i = 0; i < arIDs.length; i++) {
				actorIDs.add(Integer.parseInt(arIDs[i]));
			}
		}
		
		employeeIDs = new ArrayList<>();
		if(!movieInfo[11].equals("-")) {
			String[] arIDs = movieInfo[11].split(" ");
			for (int i = 0; i < arIDs.length; i++) {
				employeeIDs.add(Integer.parseInt(arIDs[i]));
			}
		}
		
		this.movieID = movieIDcounter;
		movieIDcounter++;
	}
	public String[] tableRow() {
        String[] st = {title,startDate.toString(),endDate.toString(),genre,Short.toString(duration),Integer.toString(budget),Integer.toString(boxOffice),Integer.toString(expectedStar),Integer.toString(producerID),Integer.toString(this.studioID),integerListToTextForWriteFile(actorIDs),integerListToTextForWriteFile(employeeIDs)};
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
		return this.title + ";" + this.startDate.toString() + ";" + this.endDate.toString() + ";" +this.genre + ";" + this.duration + ";" + this.budget + ";" + this.boxOffice + ";" + this.expectedStar + ";" + producerID + ";" + this.studioID + ";" + integerListToTextForWriteFile(actorIDs) + ";" + integerListToTextForWriteFile(employeeIDs) + "\n";
	}
	
	public static String[] getHeaders() {
		return headers;
	}

	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public static int getMovieIDcounter() {
		return movieIDcounter;
	}
	public static void setMovieIDcounter(int movieIDcounter) {
		Movie.movieIDcounter = movieIDcounter;
	}
	public ArrayList<Integer> getEmployeeIDs() {
		return employeeIDs;
	}
	public void setEmployeeIDs(ArrayList<Integer> employeeIDs) {
		this.employeeIDs = employeeIDs;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public short getDuration() {
		return duration;
	}
	public void setDuration(short duration) {
		this.duration = duration;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getBoxOffice() {
		return boxOffice;
	}
	public void setBoxOffice(int boxOffice) {
		this.boxOffice = boxOffice;
	}
	public int getExpectedStar() {
		return expectedStar;
	}
	public void setExpectedStar(int expectedStar) {
		this.expectedStar = expectedStar;
	}

	public int getProducerID() {
		return producerID;
	}
	public void setProducerID(int producerID) {
		this.producerID = producerID;
	}

	public int getStudioID() {
		return studioID;
	}
	public void setStudioID(int studioID) {
		this.studioID = studioID;
	}
	public ArrayList<Integer> getActorIDs() {
		return actorIDs;
	}
	public void setActorIDs(ArrayList<Integer> actorIDs) {
		this.actorIDs = actorIDs;
	}
	
}
