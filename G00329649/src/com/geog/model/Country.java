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
