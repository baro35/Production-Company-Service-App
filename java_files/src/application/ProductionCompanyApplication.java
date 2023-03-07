package application;

import java.util.ArrayList;

public class ProductionCompanyApplication {
	private ArrayList<Person> people;
	private ArrayList<Actor> actors;
	private ArrayList<Manager> manager;
	private ArrayList<Studio> studio;
	private ArrayList<Producer> producer;
	
	private Admin admin;
	
	public ArrayList<Person> getPeople() {
		return people;
	}
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	public ArrayList<Actor> getActors() {
		return actors;
	}
	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}
	public ArrayList<Manager> getManager() {
		return manager;
	}
	public void setManager(ArrayList<Manager> manager) {
		this.manager = manager;
	}
	public ArrayList<Studio> getStudio() {
		return studio;
	}
	public void setStudio(ArrayList<Studio> studio) {
		this.studio = studio;
	}
	public ArrayList<Producer> getProducer() {
		return producer;
	}
	public void setProducer(ArrayList<Producer> producer) {
		this.producer = producer;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}