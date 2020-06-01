package fr.epita.person.launcher.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.epita.person.datamodel.Patient;
import fr.epita.person.services.PersonCSVHandler;

public class TestLoadCSVFile {
	
	private static final String FILE_LOCATION = "persons.csv";

	public static void main(String[] args) throws IOException {
		List<Patient> persons = PersonCSVHandler.readFromFile(FILE_LOCATION);
		System.out.println(persons);
		
		
	}

	private static void loopsDemonstration(List<String> rawPersons) {
		//while loop
		int i = 0;
		int size = rawPersons.size();
		while (i < size) {
			System.out.println(rawPersons.get(i));
			i++;
		}
		
		//do-while loop
		int j = 0;
		do {
			System.out.println(rawPersons.get(j));
			j++;
		} while (j < size);
		
		//for loop
		for (int k = 0 ; k <size ; k++) {
			System.out.println(rawPersons.get(k));
		}
		
		//for-each loop
		for (String line : rawPersons) {
			System.out.println(line);
		}
	}

}
