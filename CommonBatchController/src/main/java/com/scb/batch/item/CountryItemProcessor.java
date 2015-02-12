package com.scb.batch.item;

import org.springframework.batch.item.ItemProcessor;

import com.scb.batch.model.Country;


public class CountryItemProcessor implements ItemProcessor<Country, Country> {

	public Country process(final Country country) throws Exception {
		final String countryCode = country.getCountryCode().toUpperCase();
		final String description = country.getDescription().toUpperCase();
		final Country transformedCountry = new Country(countryCode, description);
		System.out.println("Converting (" + country + ") into (" + transformedCountry + ")");
		return transformedCountry;
	}

}
