package com.scb.batch.model;

public class Country {

	private String countryCode;
	private String description;

	public Country() {

	}

	public Country(String countryCode, String description) {
		super();
		this.countryCode = countryCode;
		this.description = description;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", description=" + description + "]";
	}



}
