package fr.epita.person.launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import fr.epita.person.datamodel.Patient;
import fr.epita.person.services.PersonAgeComparator;
import fr.epita.person.services.PersonCSVHandler;

public class LauncherDataPreparation {

	public static void main(String[] args) throws IOException {

		List<Patient> persons = PersonCSVHandler.readFromFile("persons.csv");

		// averageOperations(persons);
		//calculateMedianAge(persons);
		List<Patient> groupBySexList = groupBySex(persons);
		
		PersonCSVHandler.writeToFile("personsGroupBySex.csv", groupBySexList);
		
		
		persons.sort((p1,p2) -> p1.getAge().compareTo(p2.getAge()));
		PersonCSVHandler.writeToFile("personsSortedByAge.csv", persons);
		 
		
		
		
		
		
	}

	private static void averageOperations(List<Patient> persons) {
		System.out.println("average calculated by regular loop");
		calculateAverageAge(persons);

		System.out.println("average calculated by map and reduce (functionnal API)");
		OptionalDouble averageAge = persons.stream().mapToInt(Patient::getAge).average();

		System.out.println(averageAge.getAsDouble());
	}

	private static void calculateAverageAge(List<Patient> persons) {
		Integer ageSum = 0;
		for (Patient person : persons) {
			ageSum = ageSum + person.getAge(); // ageSum += person.getAge();
		}
		int size = persons.size();
		Double averageAge = Double.valueOf(ageSum) / Double.valueOf(size);
		int averageAgeAsInteger = ageSum / size;

		System.out.println("averageAge :" + averageAge);
		System.out.println("averageAgeAsInteger :" + averageAgeAsInteger);
	}

	private static void calculateMedianAge(List<Patient> persons) {
		int size = persons.size();
		int halfPopulationIndex = size + 1 / 2 - 1;

		if (size % 2 != 0) {
			halfPopulationIndex += 1;
		}

		System.out.println("halfPopulationIndex :" + halfPopulationIndex);
		persons.sort(new PersonAgeComparator());

		System.out.println(persons);

		Patient median = persons.get(halfPopulationIndex);

		System.out.println("median: " + median.getAge());

	}

	private static void calculateDecilesAge(List<Patient> persons) {

		Map<Integer, List<Patient>> map = new LinkedHashMap<Integer, List<Patient>>();

		int size = persons.size();
		int decileIndex = size + 1 / 10 - 1;// TODO complete

		List<Patient> decileList = map.get(1);
		if (decileList == null) {
			decileList = new ArrayList<Patient>();
			map.put(1, decileList);
		}
		decileList.add(persons.get(1));

	}

	private static void groupBySexBeforeJava8(List<Patient> persons) {

		Map<String, List<Patient>> mapBySex = new LinkedHashMap<String, List<Patient>>();

		for (Patient person : persons) {
			String sex = person.getSex();
			List<Patient> list = mapBySex.get(sex);
			if (list == null) {
				list = new ArrayList<Patient>();
				mapBySex.put(sex, list);
			}
			list.add(person);

		}

		System.out.println(mapBySex);
	}
	private static List<Patient> groupBySex(List<Patient> persons) {
		
		Map<String, List<Patient>> mapBySex = persons.stream()
				.collect(Collectors.groupingBy(Patient::getSex));
		
		//1 line only
		persons.sort((p1,p2) -> p1.getSex().compareTo(p2.getSex()) );
		
		
//		persons.sort((p1,p2) -> {
//			//several lines
//			return	p1.getAge().compareTo(p2.getAge());
//		} );
//		
//		
//		//with anonymous implementation of comparator
//		persons.sort(new Comparator<Person>() {
//
//			@Override
//			public int compare(Person o1, Person o2) {
//				// TODO Auto-generated method stub
//				return	o1.getAge().compareTo(o1.getAge());
//			}
//		});
//		
		System.out.println(persons);
		return persons;
	}

}
