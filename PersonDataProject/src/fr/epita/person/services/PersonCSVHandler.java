package fr.epita.person.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.epita.person.datamodel.Patient;

public class PersonCSVHandler {

	public static List<Patient> readFromFile(String fileLocation) throws IOException {

		// This is the same as Files.readAllLines() method.
//		FileInputStream fileInputStream = new FileInputStream(new File(fileLocation));
//		Scanner scanner = new Scanner(fileInputStream);
//		List<String> lines = new ArrayList<String>();
//		while (scanner.hasNext()) {
//			String nextLine = scanner.nextLine();
//			lines.add(nextLine);
//		}
//		scanner.close();

		List<String> rawPersons = Files.readAllLines(new File(fileLocation).toPath());

		List<Patient> persons = new ArrayList<Patient>();

		rawPersons.remove(0);
		for (String line : rawPersons) {

			String[] parts = line.split(",");
			Patient person = new Patient();
			person.setName(parts[0].replaceAll("\"", "").strip());
			person.setSex(parts[1].replaceAll("\"", "").strip());
			person.setAge(Integer.valueOf(parts[2].strip()));
			person.setHeight(Double.valueOf(parts[3].strip()));
			person.setWeight(Double.valueOf(parts[4].strip()));
			persons.add(person);
		}
		return persons;

	}

	public static void writeToFile(String fileLocation, List<Patient> personsToWrite) throws IOException {

		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileLocation), false);
		PrintStream ps = new PrintStream(fileOutputStream);
		ps.println("\"Name\", \"Sex\", \"Age\", \"Height (in)\", \"Weight (lbs)\"");
		for (Patient person : personsToWrite) {
			
			//print the content of a person as csv
			//print header
		
			ps.print("\"" + person.getName() + "\", " );
			ps.print("\"" + person.getSex() +"\", " );
			ps.print(person.getAge() + ", " );
			ps.print(person.getHeight().intValue() + ", " );
			ps.println(person.getWeight().intValue());
			
			//ps.println();
			ps.flush();
		}
		ps.close();

	}
}
