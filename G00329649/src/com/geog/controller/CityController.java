package com.geog.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
		try {
			cityDao = new CityDao();
			countryDao = new CountryDao();
			regionDao =  new RegionDao();
			cities = cityDao.getCities();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}



	
//	Accessors and mutators
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
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
	public void setCountry(String co_code) { // overloaded setter, it has a 'String' parameter
		try {
			this.country = countryDao.searchCountry(co_code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // setCountry(String co_code)
	
	
	public void setRegion(String reg_code) { // overloaded setter, it has a 'String' parameter
		try {
			this.region = regionDao.searchRegion(reg_code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // setRegion(String reg_code)
	
	
	public String showDetails(City city) {
		this.city = city;
		setCountry(city.getCo_code());
		setRegion(city.getReg_code());
		
		return "show_city_details.xhtml";
		
	} // showDetails
	
	
	public String findCities() {
		
		
		
		
		return "search_results.xhtml";
		
	}
	
} // class CityController
