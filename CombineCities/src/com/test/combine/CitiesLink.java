package com.test.combine;

import java.util.ArrayList;
import java.util.List;


public class CitiesLink implements Comparable<CitiesLink>{

	private int prevSize;
	private int size;
	private int numberLetters;
	private String startCity;
	private List<String> wordLink = new ArrayList<>();
	private List<String> leftCities = new ArrayList<>();

	public int getPrevSize() {
		return prevSize;
	}

	public void setPrevSize(int prevSize) {
		this.prevSize = prevSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<String> getWordLink() {
		return wordLink;
	}

	public void setWordLink(List<String> wordLink) {
		this.wordLink = wordLink;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public List<String> getLeftCities() {
		return leftCities;
	}

	public void setLeftCities(List<String> leftCities) {
		this.leftCities = leftCities;
	}

	@Override
	public int compareTo(CitiesLink o) {
		if(numberLetters > o.getNumberLetters()) {return -1;}
		if(numberLetters < o.getNumberLetters()) {return 1;}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftCities == null) ? 0 : leftCities.hashCode());
		result = prime * result + prevSize;
		result = prime * result + size;
		result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
		result = prime * result + ((wordLink == null) ? 0 : wordLink.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CitiesLink other = (CitiesLink) obj;
		if (leftCities == null) {
			if (other.leftCities != null)
				return false;
		} else if (!leftCities.equals(other.leftCities))
			return false;
		if (prevSize != other.prevSize)
			return false;
		if (size != other.size)
			return false;
		if (startCity == null) {
			if (other.startCity != null)
				return false;
		} else if (!startCity.equals(other.startCity))
			return false;
		if (wordLink == null) {
			if (other.wordLink != null)
				return false;
		} else if (!wordLink.equals(other.wordLink))
			return false;
		return true;
	}

	public int getNumberLetters() {
		return numberLetters;
	}

	public void setNumberLetters(int numberLetters) {
		this.numberLetters = numberLetters;
	}






}
