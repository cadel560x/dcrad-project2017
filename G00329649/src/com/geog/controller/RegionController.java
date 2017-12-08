package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.geog.dao.CountryDao;
import com.geog.dao.RegionDao;
import com.geog.model.Country;
import com.geog.model.Region;

@ManagedBean
@SessionScoped
public class RegionController {
//	Instance variables
	private List<Region> regions;
	private RegionDao regionDao;
	private CountryDao countryDao;
	private List<Country> countries;
	private String errMessage;
	
	
	
	
//	Constructor
	public RegionController() {
		try {
			countryDao = new CountryDao();
			regionDao = new RegionDao();			
			
			regions = regionDao.getRegions();
			errMessage = "";
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}	
	}



	
//	Accessors and mutators
	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
	
	
//	Methods
	public void loadRegions() {
		try {
			setRegions(regionDao.getRegions());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // loadRegions
	
	
	public void loadCountries() {
		try {
			setCountries(countryDao.getCountries());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // loadCountries
	
	
	public String add(Region region) {
		try {
			regionDao.add(region);
		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		finally {
			if (errMessage.length() != 0 ) {
				FacesMessage message = new FacesMessage(errMessage);
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				return "add_region.xhtml";
			}
		} // try - catch - finally
		
		return "list_regions.xhtml";
		
	} // add
	
} // class RegionController
