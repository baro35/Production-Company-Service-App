package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DB {
	private static DB dataBase;
	private static ArrayList<Movie> movies;
	private static ArrayList<Producer> producers;
	private static ArrayList<Actor> actors;
	private static ArrayList<Manager> managers;
	private static ArrayList<Studio> studios;
	private static ArrayList<Admin> admins;
	private static ArrayList<Employee> employees;
	private static ArrayList<Contract> contracts;
	private static int producerID;
	private static int adminID;

	private DB() {
		movies = new ArrayList<>();
		producers = new ArrayList<>();
		actors = new ArrayList<>();
		managers = new ArrayList<>();
		studios = new ArrayList<>();
		admins = new ArrayList<>();
		employees = new ArrayList<>();
		contracts = new ArrayList<>();
		producerID = -1;
		adminID = -1;
	}
	
	public static DB getDataObject() {
		if(dataBase == null) {
			dataBase = new DB();
		}
		return dataBase;
	}

	public static void checkAccount(String username, String password) {
		for (int i = 0; i < producers.size(); i++) {
			if (username.equals(producers.get(i).getUsername()) && password.equals(producers.get(i).getPassword())) {
				producerID = i;
				break;
			}
		}

		for (int i = 0; i < admins.size(); i++) {
			if (username.equals(admins.get(i).getUsername()) && password.equals(admins.get(i).getPassword())) {
				adminID = i;
				break;
			}
		}
	}

	public void readAllFiles() throws IOException {
		readDBFile("producers.txt", "producer");
		readDBFile("movies.txt", "movie");
		readDBFile("actors.txt", "actor");
		readDBFile("managers.txt", "manager");
		readDBFile("studios.txt", "studio");
		readDBFile("admins.txt", "admin");
		readDBFile("employees.txt", "employee");
		readDBFile("contracts.txt","contract");
	}

	public static void writeAllFile() throws IOException {
		writeDBFile("producers.txt", "producer");
		writeDBFile("movies.txt", "movie");
		writeDBFile("actors.txt", "actor");
		writeDBFile("managers.txt", "manager");
		writeDBFile("studios.txt", "studio");
		writeDBFile("admins.txt", "admin");
		writeDBFile("employees.txt", "employee");
		writeDBFile("contracts.txt","contract");
	}
	public static void deleteAllArrayList() throws IOException {
		deleteDB("producer");
		deleteDB( "movie");
		deleteDB("actor");
		deleteDB("manager");
		deleteDB("studio");
		deleteDB("admin");
		deleteDB("employee");
		deleteDB("contract");
	}
	public void readDBFile(String fileName, String type) throws IOException {

		File file = new File(fileName);
		if (!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = " ";
		String[] tempArr;
		try {
			while ((line = br.readLine()) != null) {
				tempArr = line.split(";");
				if (type.equalsIgnoreCase("producer")) {
					Producer pr = new Producer(tempArr);
					producers.add(pr);
				} else if (type.equalsIgnoreCase("actor")) {
					Actor ar = new Actor(tempArr);
					actors.add(ar);
				} else if (type.equalsIgnoreCase("manager")) {
					Manager mr = new Manager(tempArr);
					managers.add(mr);
				} else if (type.equalsIgnoreCase("studio")) {
					Studio st = new Studio(tempArr);
					studios.add(st);
				} else if (type.equalsIgnoreCase("movie")) {
					Movie mv = new Movie(tempArr);
					movies.add(mv);
				} else if (type.equalsIgnoreCase("admin")) {
					Admin ad = new Admin(tempArr);
					admins.add(ad);
				} else if (type.equalsIgnoreCase("employee")) {
					Employee em = new Employee(tempArr);
					employees.add(em);
				} else if (type.equalsIgnoreCase("contract")) {
					Contract ct = new Contract(tempArr);
					contracts.add(ct);
				} 
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteDB(String type) {
		if (type.equalsIgnoreCase("producer")) {
			producers.clear();
			application.Producer.setProducerIDcounter(0);
		} else if (type.equalsIgnoreCase("actor")) {
			actors.clear();
			application.Actor.setActorIDcounter(0);
		} else if (type.equalsIgnoreCase("manager")) {
			managers.clear();
			application.Manager.setManagerIDcounter(0);
		} else if (type.equalsIgnoreCase("studio")) {
			studios.clear();
			application.Studio.setStudioIDcounter(0);
		} else if (type.equalsIgnoreCase("movie")) {
			movies.clear();
			application.Movie.setMovieIDcounter(0);
		} else if (type.equalsIgnoreCase("admin")) {
			admins.clear();
			application.Admin.setAdminIdCounter(0);
		} else if (type.equalsIgnoreCase("employee")) {
			employees.clear();
			application.Employee.setEmployeeIDCounter(0);
		} else if(type.equalsIgnoreCase("contract")) {
			contracts.clear();
			application.Contract.setContractIDcounter(0);
		}
	}

	public static void writeDBFile(String fileName, String type) throws FileNotFoundException {

		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			if (type.equalsIgnoreCase("producer")) {
				for (int i = 0; i < producers.size(); i++) {
					bw.write(producers.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("actor")) {
				for (int i = 0; i < actors.size(); i++) {
					bw.write(actors.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("manager")) {
				for (int i = 0; i < managers.size(); i++) {
					bw.write(managers.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("studio")) {
				for (int i = 0; i < studios.size(); i++) {
					bw.write(studios.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("movie")) {
				for (int i = 0; i < movies.size(); i++) {
					bw.write(movies.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("admin")) {
				for (int i = 0; i < admins.size(); i++) {
					bw.write(admins.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("employee")) {
				for (int i = 0; i < employees.size(); i++) {
					bw.write(employees.get(i).toTextForWriteFile());
				}
			} else if (type.equalsIgnoreCase("contract")) {
				for (int i = 0; i < contracts.size(); i++) {
					bw.write(contracts.get(i).toTextForWriteFile());
				}
			}
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static ArrayList<Movie> getMovies() {
		return movies;
	}

	public static void setMovies(ArrayList<Movie> movies) {
		DB.movies = movies;
	}

	public static ArrayList<Producer> getProducers() {
		return producers;
	}

	public static void setProducers(ArrayList<Producer> producers) {
		DB.producers = producers;
	}

	public static ArrayList<Actor> getActors() {
		return actors;
	}

	public static void setActors(ArrayList<Actor> actors) {
		DB.actors = actors;
	}

	public static ArrayList<Manager> getManagers() {
		return managers;
	}

	public static void setManagers(ArrayList<Manager> managers) {
		DB.managers = managers;
	}

	public static ArrayList<Studio> getStudios() {
		return studios;
	}

	public static void setStudios(ArrayList<Studio> studios) {
		DB.studios = studios;
	}

	public static ArrayList<Admin> getAdmins() {
		return admins;
	}

	public static void setAdmins(ArrayList<Admin> admins) {
		DB.admins = admins;
	}

	public static int getProducerID() {
		return producerID;
	}

	public static void setProducerID(int producerID) {
		DB.producerID = producerID;
	}

	public static int getAdminID() {
		return adminID;
	}

	public static void setAdminID(int adminID) {
		DB.adminID = adminID;
	}

	public static ArrayList<Employee> getEmployees() {
		return employees;
	}

	public static void setEmployees(ArrayList<Employee> employees) {
		DB.employees = employees;
	}

	public static ArrayList<Contract> getContracts() {
		return contracts;
	}

	public static void setContracts(ArrayList<Contract> contracts) {
		DB.contracts = contracts;
	}
	
}