package com.geog.controller;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import com.geog.dao.RegionDao;
import com.geog.model.Region;

@ManagedBean
public class RegionController {
//	Instance variables
	private List<Region> regions;
	
	
	
	
//	Contructor
	public RegionController() {
		RegionDao regionDao;
		try {
			regionDao = new RegionDao();
			regions = regionDao.getRegions();
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
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
	
} // class RegionController
