package application;


public abstract class Person {
	private String name;
	private String surname;
	private short weight;
	private short height;
	private Date birthdate;
	private double salary;
	private double expenditure;
	private short star;
	private String phoneNumber;
	private String emailAddress;
	private static String[] headers = {"Name" , "Surname" , "Weight" , "Height" , "Birthdate" ,"Salary" , "Expenditure" , "Star" , "Phone Number" , "E-Mail Address"};
	
	public Person(String name, String surname, double salary) {
		this.name = name;
		this.surname = surname;
		this.weight = -1;
		this.height = -1;
		this.birthdate = new Date("0", "0", "0");
		this.salary = salary;
		this.expenditure = -1;
		this.star = -1;
		this.phoneNumber = "-";
		this.emailAddress = "-";
	}
	
	public Person(String[] personInfo) {
		this.name = personInfo[0];
		this.surname = personInfo[1];
		this.weight = Short.parseShort(personInfo[2]);
		this.height = Short.parseShort(personInfo[3]);
		this.birthdate = new Date(personInfo[4].split(":")[0],personInfo[4].split(":")[1],personInfo[4].split(":")[2]);
		this.salary = Double.parseDouble(personInfo[5]);
		this.expenditure = Double.parseDouble(personInfo[6]);
		this.star = Short.parseShort(personInfo[7]);
		this.phoneNumber = personInfo[8];
		this.emailAddress = personInfo[9];
	}	
	
	public String[] tableRow() {
		String[] st = {name , surname , Integer.toString(weight) , Integer.toString(height) , birthdate.toString() ,Double.toString(salary) , Double.toString(expenditure) , Short.toString(star) , phoneNumber , emailAddress};
		return st;
	}
	
	public String toTextForWriteFile() {
		return name + ";" + surname + ";" + weight + ";" + height + ";" + birthdate.toString() +  ";" + salary + ";" + expenditure + ";" + star + ";" + phoneNumber + ";" + emailAddress;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public short getWeight() {
		return weight;
	}
	public void setWeight(short weight) {
		this.weight = weight;
	}
	public short getHeight() {
		return height;
	}
	public void setHeight(short height) {
		this.height = height;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public short getStar() {
		return star;
	}
	public void setStar(short star) {
		this.star = star;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public static String[] getHeaders() {
		return headers;
	}


}