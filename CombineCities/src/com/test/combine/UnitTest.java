package com.test.combine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UnitTest {

	private static CitiesLink city;
	{
		city = new CitiesLink();
		city.setStartCity("CityTesT");
		city.setLeftCities(Arrays.asList("OneTesT", "TestTwO"));
	}

	@Test
	public void testCalculateLetters() throws Exception {
		List<CitiesLink> winners = new ArrayList<>();
		CitiesLink link = new CitiesLink();
		link.setWordLink(Arrays.asList("m", "n", "k"));
		winners.add(link);
		List<CitiesLink> result = Main.calculateLongestLink(winners);
		assertEquals(3, result.get(0).getNumberLetters());
	}

	@Test
	public void testCalculateLettersTwo() throws Exception {
		List<CitiesLink> winners = new ArrayList<>();
		CitiesLink link = new CitiesLink();
		link.setWordLink(Arrays.asList("m", "n", "k"));
		CitiesLink link2 = new CitiesLink();
		link.setWordLink(Arrays.asList("m", "n", "k", "f"));
		winners.add(link);
		winners.add(link2);
		List<CitiesLink> result = Main.calculateLongestLink(winners);
		assertEquals(4, result.get(0).getNumberLetters());
	}

	@Test
	public void testCalculateLettersThree() throws Exception {
		List<CitiesLink> winners = new ArrayList<>();
		CitiesLink link = new CitiesLink();
		link.setWordLink(Arrays.asList("m", "n", "k"));
		CitiesLink link2 = new CitiesLink();
		link2.setWordLink(Arrays.asList("m", "n", "f"));
		winners.add(link);
		winners.add(link2);
		List<CitiesLink> result = Main.calculateLongestLink(winners);
		assertEquals(3, result.get(0).getNumberLetters());
		assertEquals(2, result.size());
	}

	@Test(expected = Exception.class)
	public void testCalculateLettersExeption() throws Exception {
		List<CitiesLink> winners = null;
		Main.calculateLongestLink(winners);
	}

	@Test(expected = Exception.class)
	public void testCalculateLettersExeptionTwo() throws Exception {
		List<CitiesLink> winners = new ArrayList<>();
		Main.calculateLongestLink(winners);
	}

	@Test(expected = Exception.class)
	public void testCreateCombineCitiesExeption() throws Exception {
		CitiesLink citiesLink = null;
		Main.createCombineCities(citiesLink);
	}

	@Test(expected = Exception.class)
	public void testCreateCombineCitiesExeptionDataOne() throws Exception {
		city.setStartCity(null);
		Main.createCombineCities(city);
		city.setStartCity("CityTesT");
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testCreateCombineCitiesExeptionDataTwo() throws Exception {
		city.setLeftCities(null);
		expectedEx.expectMessage("CitiesLink missing data");
		Main.createCombineCities(city);
		city.setLeftCities(Arrays.asList("OneTesT", "TestTwO"));
	}

	@Test
	public void combineWordsOne() throws Exception {
		Main.createCombineCities(city);
		assertEquals("CityTesT,TestTwO", Main.listOfCitiesTemp.get(0).getStartCity());
		assertEquals("OneTesT", Main.listOfCitiesTemp.get(0).getLeftCities().get(0));
	}

	@Test
	public void combineWordsTwoFail() throws Exception {
		Main.listOfCitiesTemp.clear();
		CitiesLink test = new CitiesLink();
		test.setStartCity("dfe");
		test.setLeftCities(Arrays.asList("abc", "fga", " mnj"));
		Main.createCombineCities(test);
		assertEquals(0, Main.listOfCitiesTemp.size());

	}
}
