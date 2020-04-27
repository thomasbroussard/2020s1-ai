package fr.epita.person.launcher.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import fr.epita.person.datamodel.Person;

public class TestFileWriteOperations {
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("personsOut.csv");
		FileOutputStream fileOutputStream = new FileOutputStream(file, false);
		PrintStream ps = new PrintStream(fileOutputStream);
		Person person = new Person();
		person.setAge(30);
		person.setSex("F");
		person.setHeight(75.0);
		person.setWeight(160.0);
		person.setName("Test");
		
		
		//print the content of a person as csv
		//print header
		ps.println("\"Name\", \"Sex\", \"Age\", \"Height (in)\", \"Weight (lbs)\"");
		ps.print("\"" + person.getName() + "\", " );
		ps.print("\"" + person.getSex() +"\", " );
		ps.print(person.getAge() + ", " );
		ps.print(person.getHeight().intValue() + ", " );
		ps.print(person.getWeight().intValue());
		
		//ps.println();
		ps.flush();
		
		
		
		
		
		ps.close();
		
		
		
	}

}
