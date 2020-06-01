package fr.epita.covid19.patientinfo.tests;

import java.util.Arrays;
import java.util.Date;

import fr.epita.covid19.patientinfo.datamodel.Patient;
import fr.epita.covid19.patientinfo.services.PatientEncoder;

public class TestConversionToDouble {
	
	public static void main(String[] args) {
		PatientEncoder patientEncoder = new PatientEncoder();
		Patient patient = new Patient();
		patient.setAge(50);
		patient.setInfectionCase("overseas inflow");
		patient.setSex("male");
		patient.setSymptomOnSetDate(new Date());
		patient.setConfirmedDate(new Date());
		System.out.println(patient);
		
		Double[] doubleArray = patientEncoder.convertToDoubleArray(patient);
		System.out.println(Arrays.asList(doubleArray));
		
		
		System.out.println(patientEncoder.convertToPatient(doubleArray));
		
	}

}
