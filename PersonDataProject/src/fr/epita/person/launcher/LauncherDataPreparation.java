package fr.epita.person.launcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import fr.epita.person.datamodel.Person;
import fr.epita.person.services.PersonAgeComparator;
import fr.epita.person.services.PersonCSVHandler;

public class LauncherDataPreparation {

	public static void main(String[] args) throws IOException {

		List<Person> persons = PersonCSVHandler.readFromFile("persons.csv");

		// averageOperations(persons);
		//calculateMedianAge(persons);
		List<Person> groupBySexList = groupBySex(persons);
		
		PersonCSVHandler.writeToFile("personsGroupBySex.csv", groupBySexList);
		
		
		persons.sort((p1,p2) -> p1.getAge().compareTo(p2.getAge()));
		PersonCSVHandler.writeToFile("personsSortedByAge.csv", persons);
		 
		
		
		
		
		
	}

	private static void averageOperations(List<Person> persons) {
		System.out.println("average calculated by regular loop");
		calculateAverageAge(persons);

		System.out.println("average calculated by map and reduce (functionnal API)");
		OptionalDouble averageAge = persons.stream().mapToInt(Person::getAge).average();

		System.out.println(averageAge.getAsDouble());
	}

	private static void calculateAverageAge(List<Person> persons) {
		Integer ageSum = 0;
		for (Person person : persons) {
			ageSum = ageSum + person.getAge(); // ageSum += person.getAge();
		}
		int size = persons.size();
		Double averageAge = Double.valueOf(ageSum) / Double.valueOf(size);
		int averageAgeAsInteger = ageSum / size;

		System.out.println("averageAge :" + averageAge);
		System.out.println("averageAgeAsInteger :" + averageAgeAsInteger);
	}

	private static void calculateMedianAge(List<Person> persons) {
		int size = persons.size();
		int halfPopulationIndex = size + 1 / 2 - 1;

		if (size % 2 != 0) {
			halfPopulationIndex += 1;
		}

		System.out.println("halfPopulationIndex :" + halfPopulationIndex);
		persons.sort(new PersonAgeComparator());

		System.out.println(persons);

		Person median = persons.get(halfPopulationIndex);

		System.out.println("median: " + median.getAge());

	}

	private static void calculateDecilesAge(List<Person> persons) {

		Map<Integer, List<Person>> map = new LinkedHashMap<Integer, List<Person>>();

		int size = persons.size();
		int decileIndex = size + 1 / 10 - 1;// TODO complete

		List<Person> decileList = map.get(1);
		if (decileList == null) {
			decileList = new ArrayList<Person>();
			map.put(1, decileList);
		}
		decileList.add(persons.get(1));

	}

	private static void groupBySexBeforeJava8(List<Person> persons) {

		Map<String, List<Person>> mapBySex = new LinkedHashMap<String, List<Person>>();

		for (Person person : persons) {
			String sex = person.getSex();
			List<Person> list = mapBySex.get(sex);
			if (list == null) {
				list = new ArrayList<Person>();
				mapBySex.put(sex, list);
			}
			list.add(person);

		}

		System.out.println(mapBySex);
	}
	private static List<Person> groupBySex(List<Person> persons) {
		
		Map<String, List<Person>> mapBySex = persons.stream()
				.collect(Collectors.groupingBy(Person::getSex));
		
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
