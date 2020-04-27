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
		//ps.println();
		ps.flush();
		
		
		
		
		
		ps.close();
		
		
		
	}

}
