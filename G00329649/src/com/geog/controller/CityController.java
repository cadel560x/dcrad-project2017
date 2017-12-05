package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import com.geog.dao.CityDao;
import com.geog.model.City;

@ManagedBean
@SessionScoped
public class CityController {
//	Instance variables
	private List<City> cities;
	private CityDao cityDao;
	private City city;
	
	
	
	
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


//	Methods
	public String showDetails(City city) {
		this.city = city;
		CountryDao countryDao
		
		return "show_city_details.xhtml";
	}
	
} // class CityController
