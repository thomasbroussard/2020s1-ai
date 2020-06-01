package fr.epita.covid19.patientinfo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.epita.covid19.patientinfo.datamodel.Patient;

public class PatientCSVHandler {

	public static List<Patient> readFromFile(String fileLocation) throws IOException, ParseException {

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

		List<Patient> patients = new ArrayList<Patient>();

		rawPersons.remove(0);
		for (String line : rawPersons) {

			String[] parts = line.split(",");
			Patient patient = new Patient();
			// age calculation
			Integer age = computeAge(parts);
			patient.setAge(age);
			patient.setSex(parts[2]);
			patient.setInfectionCase(parts[9]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!"".equals(parts[13].strip())) {
				patient.setSymptomOnSetDate(sdf.parse(parts[13]));
			}
			if (!"".equals(parts[14].strip())){
				patient.setConfirmedDate(sdf.parse(parts[14]));
			}
			patients.add(patient);
		
		}
		return patients;

	}

	private static Integer computeAge(String[] parts) {
		String birthYear = parts[3];
		String ageApproximation = parts[4];
		Integer age = null;
		if ("".equals(birthYear.strip())) {
			if ("".equals(ageApproximation.strip())){
				System.out.println("missing value for age");
			}else {
				age = Integer.valueOf(ageApproximation.substring(0, 1));
			}
		}else {
			Integer year = Integer.valueOf(birthYear);
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
			age = currentYear - year;
		}
		return age;
	}

}
