package fr.epita.person.launcher;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fr.epita.person.datamodel.Person;
import fr.epita.person.services.PersonCSVHandler;

public class LauncherIndexer {

	public static void main(String[] args) throws IOException {
		List<Person> readFromFile = PersonCSVHandler.readFromFile("persons.csv");
		Map<String, Integer> index = new LinkedHashMap<>();

		List<Person> indexedList = readFromFile.stream().peek(p -> {
			String sex = p.getSex();
			Integer currentValue = index.get(sex);
			if (currentValue == null) {
				currentValue = index.size() + 1;
				index.put(sex, currentValue);
			}
			p.setIndexedSex(currentValue);
		}).collect(Collectors.toList());

		System.out.println(indexedList);
		Double[][] doubleMatrix = to2DDoubleMatrix(indexedList, p -> {
			return new Double[] { Double.valueOf(p.getIndexedSex()), Double.valueOf(p.getAge()), p.getHeight(),
					p.getWeight() };
		});

		printDoubleMatrix(doubleMatrix);

		
		// TODO reconstruct back the List of persons from the doublematrix
		
		
		System.out.println(index);

	}

	private static String resolveByValue(Map<String,Integer> map, Integer integer) {
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String,Integer> entry : entrySet) {
			if (entry.getValue().equals(integer)) {
				return entry.getKey();
			}
		}
		return null;
		
	}
	
	private static <T> Double[][] to2DDoubleMatrix(List<T> list, Function<T, Double[]> mapper) {

		List<Double[]> numericalList = list.stream().map(mapper).collect(Collectors.toList());

		Double[][] matrix = numericalList.toArray(new Double[0][]);

		return matrix;

	}

	private static void printDoubleMatrix(Double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
