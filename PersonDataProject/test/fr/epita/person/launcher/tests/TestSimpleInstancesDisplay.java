package fr.epita.person.launcher.tests;

import java.util.Arrays;
import java.util.List;

import fr.epita.person.datamodel.Person;

public class TestSimpleInstancesDisplay {
	
	public static void main(String[] args) {
		
		Person person1 = new Person();
		person1.setName("Alex");
		person1.setSex("M");
		person1.setAge(41);
		person1.setHeight(74.0);
		person1.setWeight(170.0);
		
		Person person2 = new Person();
		person2.setName("Bert");
		person2.setSex("M");
		person2.setAge(42);
		person2.setHeight(68.0);
		person2.setWeight(166.0);
		
		List<Person> persons = Arrays.asList(person1, person2);

		System.out.println(persons);
		
		
		
		
	}

}
