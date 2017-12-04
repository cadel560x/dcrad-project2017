package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import com.geog.dao.CountryDao;
import com.geog.model.Country;
import com.geog.model.Region;

@ManagedBean
public class CountryController {
//	Instance variables
	private List<Country> countries;
	private List<Region> regions;
	private CountryDao countryDao;
	
	
	
	
//	Contructor
	public CountryController() {
		try {
			countryDao = new CountryDao();
//			countries = countryDao.getCountries();
//		} catch (NamingException | SQLException e) {
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
//	Methods
	public void loadCountries() {
		try {
			countries = countryDao.getCountries();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // loadCountries
	
} // class CountryController
