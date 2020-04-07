package fr.epita.exercises.launcher;

import fr.epita.exercises.datamodel.Customer;

public class Launcher {

	public static void main(String[] args) {
		Customer thomas = new Customer();
		thomas.setAddress("paris");
		thomas.setName("thomas");
		System.out.println(thomas.getName());
	}

}
