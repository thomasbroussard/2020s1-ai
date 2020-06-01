package fr.epita.person.services;

import java.util.Comparator;

import fr.epita.person.datamodel.Patient;

public class PersonAgeComparator implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getAge().compareTo(o2.getAge());
	}


}
