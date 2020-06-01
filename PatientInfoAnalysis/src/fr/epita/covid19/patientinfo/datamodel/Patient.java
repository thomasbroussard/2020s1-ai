package fr.epita.covid19.patientinfo.datamodel;

import java.util.Date;

public class Patient {
	
	//age (determined by the combination of the "age" and "birth_year" columns)
	private Integer age;
	
	//sex
	private String sex;
	
	//infectionCase
	private String infectionCase;
	
	//symptomOnsetDate
	private Date symptomOnSetDate;
	
	//confirmedDate
	private Date confirmedDate;

	
	
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getInfectionCase() {
		return infectionCase;
	}

	public void setInfectionCase(String infectionCase) {
		this.infectionCase = infectionCase;
	}

	public Date getSymptomOnSetDate() {
		return symptomOnSetDate;
	}

	public void setSymptomOnSetDate(Date symptomOnSetDate) {
		this.symptomOnSetDate = symptomOnSetDate;
	}

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	@Override
	public String toString() {
		return "Patient [age=" + age + ", sex=" + sex + ", infectionCase=" + infectionCase + ", symptomOnSetDate="
				+ symptomOnSetDate + ", confirmedDate=" + confirmedDate + "]";
	}
	
	
	
	

}
