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
	private City city;
	private Region region;
	private Country country;
	
	
	
	
//	Constructor
	public CityController() {
		try {
			cityDao = new CityDao();
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




//	Methods
	public String showDetails(City city) {
		try {
			this.city = city;
			this.country = new CountryDao().getCountry(city.getCo_code());
			this.region = new RegionDao().getRegion(city.getReg_code());
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "show_city_details.xhtml";
	} // showDetails
	
} // class CityController
