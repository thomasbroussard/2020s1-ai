package fr.epita.covid19.patientinfo.tests;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import fr.epita.covid19.patientinfo.datamodel.Patient;
import fr.epita.covid19.patientinfo.services.PatientCSVHandler;
import fr.epita.covid19.patientinfo.services.PatientEncoder;

public class TestDeserialization {
	
	public static void main(String[] args) throws IOException, ParseException {
		List<Patient> patientList = PatientCSVHandler.readFromFile("PatientInfo.csv");
		
		System.out.println(patientList);
		System.out.println(patientList.size());
		
		PatientEncoder encoder = new PatientEncoder();
		Double[][] matrix = new Double[patientList.size()][5];
		System.out.println("[");
		for (int i = 0; i < patientList.size(); i++) {
			Patient currentPatient = patientList.get(i);
			Double[] encodedLine = encoder.convertToDoubleArray(currentPatient);
		
			matrix[i] = encodedLine;
			System.out.println(Arrays.asList(encodedLine));
		}
		System.out.println("]");
		
		System.out.println(encoder.getCaseEncodedValues());
		System.out.println(encoder.getSexEncodedValues());
	}

}
