package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

import com.geog.dao.CountryDao;
import com.geog.model.Country;

@ManagedBean
@SessionScoped
public class CountryController {
//	Instance variables
	private List<Country> countries;
	private CountryDao countryDao;
	
	
	
	
//	Constructor
	public CountryController() {
		
		try {
			countryDao = new CountryDao();
			countries = countryDao.getCountries();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
//	Accessors and mutators
    public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
	
	
//	Methods
	public String add(Country country) {
		try {
			countryDao.add(country);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "list_countries.xhtml";
		
	} // add
	
} // class CountryController
