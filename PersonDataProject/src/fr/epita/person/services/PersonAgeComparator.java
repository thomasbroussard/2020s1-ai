package fr.epita.person.services;

import java.util.Comparator;

import fr.epita.person.datamodel.Person;

public class PersonAgeComparator implements Comparator<Person>{

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getAge().compareTo(o2.getAge());
	}


}
