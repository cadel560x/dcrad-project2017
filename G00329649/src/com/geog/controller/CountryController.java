package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.geog.dao.CountryDao;
import com.geog.model.Country;

@ManagedBean
@SessionScoped
public class CountryController {
//	Instance variables
	private List<Country> countries;
	private CountryDao countryDao;
	private Country country;
	private String errMessage;
	
	
	
	
//	Constructor
	public CountryController() {
		try {
			countryDao = new CountryDao();
			countries = countryDao.getCountries();
			errMessage = "";
		} catch (NamingException | SQLException e) {
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
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}




//	Methods
	public void loadCountries() {
		try {
			setCountries(countryDao.getCountries());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // loadCountries
	
	
	public String add(Country country) {
		try {
			countryDao.add(country);
		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		finally {
			if (errMessage.length() != 0 ) {
				FacesMessage message = new FacesMessage(errMessage);
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				return "add_country.xhtml";
			}
		} // try - catch - finally
		
		return "list_countries.xhtml";
		
	} // add
	
	
	public String update(Country country) {
		setCountry(country);
		
		return "update_country.xhtml";
		
	} // update
	
	
	public String updateCountry(Country country) {
//		setCountry(country);
		try {
			countryDao.update(country);
		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		finally {
			if (errMessage.length() != 0 ) {
				FacesMessage message = new FacesMessage(errMessage);
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				return "update_country.xhtml";
			}
		} // try - catch - finally
		
		return "list_countries.xhtml";
		
	} // update
	
	
	public String delete(Country country) {
		try {
			countryDao.delete(country);
		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		finally {
			if (errMessage.length() != 0 ) {
				FacesMessage message = new FacesMessage(errMessage);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} // try - catch - finally
		
		return "list_countries.xhtml";
	}
	
} // class CountryController
