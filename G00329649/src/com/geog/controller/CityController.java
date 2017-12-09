package com.geog.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.naming.NamingException;

import com.geog.dao.CityDao;
import com.geog.dao.CountryDao;
import com.geog.dao.RegionDao;
import com.geog.model.City;
import com.geog.model.CityDetails;
import com.geog.model.Country;
import com.geog.model.Region;

@ManagedBean
@SessionScoped
public class CityController {
//	Instance variables
	private CityDao cityDao;
	private CountryDao countryDao;
	private RegionDao regionDao;
	private City city;
	private CityDetails cityDetails;
	private Region region;
	private Country country;
	private String populationCriteria;
	private List<City> cities;
	private List<Region> regions;
	private List<Region> availableRegions;
	private List<Country> countries;
	private String errMessage;
	
	
	
	
//	Constructor
	public CityController() {
		country = new Country();
		region = new Region();
		city = new City();
//		cityDetails = new CityDetails();
		
		try {
			cityDao = new CityDao();
			regionDao =  new RegionDao();
			countryDao = new CountryDao();
			
			cities = cityDao.getCities();
			regions = regionDao.getRegions();
			countries = countryDao.getCountries();
			errMessage = "";
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

	public List<Region> getRegions() {
		return regions;
	}
	
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Region> getAvailableRegions() {
		return availableRegions;
	}

	public void setAvailableRegions(List<Region> availableRegions) {
		this.availableRegions = availableRegions;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CityDetails getCityDetails() {
		return cityDetails;
	}

	public void setCityDetails(CityDetails cityDetails) {
		this.cityDetails = cityDetails;
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

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}




//	Methods
	public void load() {
		loadCities();
		loadRegions();
		loadCountries();
		city = new City();
		
//		try {
//			setAvailableRegions(regionDao.listRegions(countries.get(1).getCode()));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	} // load
	
	
	public void loadCities() {
		try {
			setCities(cityDao.getCities());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // loadCities
	
	
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
	
	
	public void changeRegion(AjaxBehaviorEvent event) {
        try {
			setAvailableRegions(regionDao.listRegions(city.getCo_code()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    } // changeCountry
	
	
	public String add(City city) {
		try {
			cityDao.add(city);
		} catch (SQLException e) {
			e.printStackTrace();
			errMessage = e.getMessage();
		}
		finally {
			if (errMessage.length() != 0 ) {
				FacesMessage message = new FacesMessage(errMessage);
				FacesContext.getCurrentInstance().addMessage(null, message);
				
				return "add_city.xhtml";
			}
		} // try - catch - finally
		
		return "list_cities.xhtml";
		
	} // add
	
	
	public String showDetails(City city) {
		setCity(city);
		try {
			for(Country needle: countryDao.getCountries()) {
				if (city.getCo_code().equalsIgnoreCase(needle.getCode())) {
					// Set the 'Country' object of this 'CityController'
					setCountry(needle);
					break;
				}
			}
			
			for(Region needle: regionDao.getRegions()) {
				if (city.getReg_code().equalsIgnoreCase(needle.getCode())) {
					// Set the 'Region' object of this 'CityController'
					setRegion(needle);
					break;
				}
			}
//			setCountry(countryDao.searchCountry(city.getCo_code()));
//			setRegion(regionDao.searchRegion(city.getReg_code()));
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
			setCities(cityDao.searchCities(this.city, population, this.populationCriteria));
			
			// Show a message if no cities were found
			if ( cities.size() == 0 ) {
				FacesMessage message = new FacesMessage("No cities found.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
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
