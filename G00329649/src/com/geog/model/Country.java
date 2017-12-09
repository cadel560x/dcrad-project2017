package com.geog.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Country {
//	Instance variables
	private String code;
	private String name;
	private String details;
	
	
	
	
//	Constructors
	public Country() {
	}

	public Country(String code, String name, String details) {
		this.code = code;
		this.name = name;
		this.details = details;
	}

	
	
	
//	Accessors and mutators
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
} // class Country
