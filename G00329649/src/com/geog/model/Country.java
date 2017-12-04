package com.geog.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Country {
//	Instance variables
	private String code;
	private String name;
	private StringBuilder details;
	
	
	
	
//	Constructor
	public Country() {
	}

	public Country(String code, String name, StringBuilder details) {
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

	public StringBuilder getDetails() {
		return details;
	}

	public void setDetails(StringBuilder details) {
		this.details = details;
	}

} // class Country
