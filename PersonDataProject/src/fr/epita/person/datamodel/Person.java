package fr.epita.person.datamodel;

public class Person {

	private String name;
	private String sex;
	private Integer indexedSex;
	private Integer age;
	private Double height;
	private Double weight;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	
	public Integer getIndexedSex() {
		return indexedSex;
	}
	public void setIndexedSex(Integer indexedSex) {
		this.indexedSex = indexedSex;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", indexedSex=" + indexedSex + ", age=" + age + ", height="
				+ height + ", weight=" + weight + "]\n";
	}

	
	
	
}
