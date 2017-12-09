package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.geog.dao.CountryDao;
import com.geog.dao.HeadOfStateDao;
import com.geog.model.Country;
import com.geog.model.HeadOfState;

@ManagedBean
@SessionScoped
public class HeadOfStateController {
//	Instance variables
	private List<HeadOfState> headsOfState;
	private List<Country> countries;
	private CountryDao countryDao;
	private HeadOfStateDao headOfStateDao;
	private String errMessage;
	
	
	
	
//	Constructor
	public HeadOfStateController() {
		try {
			headOfStateDao = new HeadOfStateDao();
			countryDao = new CountryDao();
			
			headsOfState = headOfStateDao.getHeadsOfState();
			errMessage = "";
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
//	Accessors and mutators
	public List<HeadOfState> getHeadsOfState() {
		return headsOfState;
	}

	public void setHeadsOfState(List<HeadOfState> headsOfState) {
		this.headsOfState = headsOfState;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}




//	Methods
	public void load() {
		
	} // load
	
	
	public void loadHeadsOfState() {
		setHeadsOfState(headOfStateDao.getHeadsOfState());
		
	} // loadHeadsOfState
	
	
	public void loadCountries() {
		try {
			setCountries(countryDao.getCountries());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} // loadCountries
	
	
//	public String add(HeadOfState headOfState) {
//		try {
//			headOfStateDao.add(headOfState);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errMessage = e.getMessage();
//		}
//		finally {
//			if (errMessage.length() != 0 ) {
//				FacesMessage message = new FacesMessage(errMessage);
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				
//				return "add_head_of_state.xhtml";
//			}
//		} // try - catch - finally
//		
//		return "list_heads_of_state.xhtml";
//		
//	} // add
//	
//	
//	public String delete(HeadOfState headOfState) {
//		try {
//			countryDao.delete(headOfState);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			errMessage = e.getMessage();
//		}
//		finally {
//			if (errMessage.length() != 0 ) {
//				FacesMessage message = new FacesMessage(errMessage);
//				FacesContext.getCurrentInstance().addMessage(null, message);
//			}
//		} // try - catch - finally
//		
//		return "list_heads_of_state.xhtml";
//		
//	} // delete
	
} // class CountryController
