package fr.epita.person.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.epita.person.datamodel.Person;

public class PersonCSVHandler {

	
	
	public static List<Person> readFromFile(String fileLocation) throws IOException {

		//This is the same as Files.readAllLines() method.
//		FileInputStream fileInputStream = new FileInputStream(new File(fileLocation));
//		Scanner scanner = new Scanner(fileInputStream);
//		List<String> lines = new ArrayList<String>();
//		while (scanner.hasNext()) {
//			String nextLine = scanner.nextLine();
//			lines.add(nextLine);
//		}
//		scanner.close();
		
		List<String> rawPersons = Files.readAllLines(new File(fileLocation).toPath());

		List<Person> persons = new ArrayList<Person>();

		rawPersons.remove(0);
		for (String line : rawPersons) {

			String[] parts = line.split(",");
			Person person = new Person();
			person.setName(parts[0]);
			person.setSex(parts[1]);
			person.setAge(Integer.valueOf(parts[2].strip()));
			person.setHeight(Double.valueOf(parts[3].strip()));
			person.setWeight(Double.valueOf(parts[4].strip()));
			persons.add(person);
		}
		return persons;

	}
}
