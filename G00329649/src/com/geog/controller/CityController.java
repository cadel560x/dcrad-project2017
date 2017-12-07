package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import com.geog.dao.CityDao;
import com.geog.dao.CountryDao;
import com.geog.dao.RegionDao;
import com.geog.model.City;
import com.geog.model.Country;
import com.geog.model.Region;

@ManagedBean
@SessionScoped
public class CityController {
//	Instance variables
	private List<City> cities;
	private CityDao cityDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private City city;
	private Region region;
	private Country country;
	private String populationCriteria;
	
	
	
	
//	Constructor
	public CityController() {
		country = new Country();
		region = new Region();
		city = new City();
		
		try {
			cityDao = new CityDao();
			countryDao = new CountryDao();
			regionDao =  new RegionDao();
			
			cities = cityDao.getCities();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} // try - catch
		
	}



	
//	Accessors and mutators
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getPopulationCriteria() {
		return populationCriteria;
	}

	public void setPopulationCriteria(String populationCriteria) {
		this.populationCriteria = populationCriteria;
	}




//	Methods
	public String showDetails(City city) {
		this.city = city;
		try {
			setCountry(countryDao.searchCountry(city.getCo_code()));
			setRegion(regionDao.searchRegion(city.getReg_code()));
		} catch (SQLException e) {
			e.printStackTrace();
		} // try - catch

		return "show_city_details.xhtml";
		
	} // showDetails
	
	
	public String searchCities(String population) {
		try {
			// 'population' is a 'String' method parameter, not an integer
			// This way, we can know if the user typed something into the corresponding input textbox.
			if ( ! population.equals("") ) {
				this.city.setPopulation(Integer.parseInt(population));
			}
			this.cities = cityDao.searchCities(this.city, population, this.populationCriteria);
		} catch (SQLException e) {
			e.printStackTrace();
		} // try - catch
		
		return "search_results.xhtml";
		
	} // searchCities
	
	
	public void resetSearch() {
		this.city = new City();
		this.city.setCoastal(true);
		this.setPopulationCriteria("lt");
		
	} // resetSearch
	
} // class CityController
