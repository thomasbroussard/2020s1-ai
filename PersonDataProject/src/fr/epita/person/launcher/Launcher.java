package fr.epita.person.launcher;

import java.io.IOException;
import java.util.List;
import java.util.OptionalDouble;

import fr.epita.person.datamodel.Person;
import fr.epita.person.services.PersonCSVHandler;

public class Launcher {
	
	public static void main(String[] args) throws IOException {
		
		List<Person> persons = PersonCSVHandler.readFromFile("persons.csv");
		
		System.out.println("average calculated by regular loop");
		calculateAverageAge(persons);
		
		System.out.println("average calculated by map and reduce (functionnal API)");
		OptionalDouble averageAge = persons.stream()
				.mapToInt(Person::getAge)
				.average();
		System.out.println(averageAge.getAsDouble());
		
	}

	private static void calculateAverageAge(List<Person> persons) {
		Integer ageSum = 0;
		for (Person person : persons) {
			ageSum = ageSum + person.getAge(); //ageSum += person.getAge();
		}
		int size = persons.size();
		Double averageAge = Double.valueOf(ageSum) / Double.valueOf(size);
		int averageAgeAsInteger = ageSum / size;
		
		System.out.println("averageAge :" + averageAge);
		System.out.println("averageAgeAsInteger :" + averageAgeAsInteger);
	}

}
