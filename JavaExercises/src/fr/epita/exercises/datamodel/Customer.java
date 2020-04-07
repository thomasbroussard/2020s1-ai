package fr.epita.exercises.datamodel;

public class Customer {
	private String name; 
	//4 levels of visibility : 
	/* (nothing) : package protected, reachable from classes of the same package
	 *public : reachable from anywhere in the code
	 *private : reachable only from the same class
	 *protected : reachable from classes of the same package + inheriting classes
	 * */
	private String address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if ("".equals(name)) {
			return;
		}
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
