package com.test.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
	public final static Set<String> inputCities = new LinkedHashSet<>(Arrays.asList("London", "Milan", "Stockholm",
			"Paris", "NewYork", "Kyiv", "Odesa", "Lviv", "Berlin", "Vienna"));
	public final static List<String> valuesList = inputCities.stream().collect(Collectors.toList());
	public static List<CitiesLink> listOfCities = new ArrayList<CitiesLink>();
	public static List<CitiesLink> listOfCitiesTemp = new ArrayList<CitiesLink>();
	private static boolean flag = true;

	public static void main(String[] args) throws Exception {
		if (valuesList.contains(null)) {
			throw new Exception("Null is in List of Cities");
		}
		for (int i = 0; i < valuesList.size(); i++) { // init start City names obj for List<CitiesLink> listOfCities

			List<String> baseList = new ArrayList<String>();

			for (String city : valuesList) {
				baseList.add(city.toUpperCase());
			}
			CitiesLink citiesLink = new CitiesLink();
			String startName = valuesList.get(i).toUpperCase();
			baseList.remove(startName);
			citiesLink.setStartCity(startName);
			citiesLink.setSize(startName.length());
			citiesLink.setPrevSize(startName.length());
			citiesLink.setWordLink(new ArrayList<>());
			citiesLink.setLeftCities(baseList);
			listOfCities.add(citiesLink);

		}
		int iteration = 1;
		while (flag) {
			System.err.println("Iteration " + iteration++);
			for (CitiesLink city : listOfCities) {
				System.out.println("     " + city.getStartCity() + " " + city.getLeftCities().toString());
			}
			System.out.println();

			for (int i = 0; i < listOfCities.size(); i++) {

				createCombineCities(listOfCities.get(i));

				if (listOfCities.size() - 1 == i) { // last iteration true

					if (listOfCitiesTemp.isEmpty()) {
						flag = false;

					} else {

						listOfCities = new ArrayList<>(listOfCitiesTemp);
						listOfCitiesTemp = new ArrayList<CitiesLink>();
						break;
					}
				}
			}
		}

		for (CitiesLink city : listOfCities) {
			System.out.println("Result:  " + city.getStartCity() + ". Cities name that not combine : "
					+ city.getLeftCities().toString());
		}

		List<CitiesLink> winners = calculateLongestLink(listOfCities);
		for (CitiesLink citiesLink : winners) {
			System.out.println(citiesLink.getStartCity() + " " + citiesLink.getNumberLetters() + " letters");
		}

	}

	public static List<CitiesLink> calculateLongestLink(List<CitiesLink> results) throws Exception {

		if (results == null) {
			throw new Exception("List<CitiesLink> object is null");
		}
		if (results.isEmpty()) {
			throw new Exception("List<CitiesLink> object is empty");
		}

		List<CitiesLink> winners = new ArrayList<>();
		for (CitiesLink citiesLink : results) {
			StringBuilder sb = new StringBuilder();
			for (String name : citiesLink.getWordLink()) {
				sb.append(name);
			}
			int numberLetters = sb.toString().length();
			citiesLink.setNumberLetters(numberLetters);
			winners.add(citiesLink);
		}
		Collections.sort(winners);
		int maxLetters = winners.get(0).getNumberLetters();
		List<CitiesLink> result = new ArrayList<>();

		for (int i = 0; i < winners.size(); i++) {
			if (maxLetters == winners.get(i).getNumberLetters()) {
				result.add(winners.get(i));
			}

		}
		return result;
	}

	public static void createCombineCities(CitiesLink citiesLink) throws Exception {
		if (citiesLink == null) {
			throw new Exception("CitiesLink object is null");
		}
		if (citiesLink.getLeftCities() == null | citiesLink.getStartCity() == null) {
			throw new Exception("CitiesLink missing data");
		}

		for (int i = 0; i < citiesLink.getLeftCities().size(); i++) {
			char lastLetter = citiesLink.getStartCity().charAt(citiesLink.getStartCity().length() - 1);
			char firstLetterOtherCity = citiesLink.getLeftCities().get(i).charAt(0);
			if (lastLetter == firstLetterOtherCity) {
				CitiesLink citiesLinkNew = new CitiesLink();
				String link = citiesLink.getStartCity() + "," + citiesLink.getLeftCities().get(i);
				String[] mas = link.split(",");
				citiesLinkNew.setSize(link.length());
				citiesLinkNew.setStartCity(link);
				ArrayList<String> temp = new ArrayList<>(Arrays.asList(mas));
				citiesLinkNew.setWordLink(temp);
				List<String> temp2 = new ArrayList<>();
				temp2.addAll(citiesLink.getLeftCities());
				temp2.removeAll(temp);
				citiesLinkNew.setLeftCities(temp2);

				listOfCitiesTemp.add(citiesLinkNew);

			}
		}

	}

}
