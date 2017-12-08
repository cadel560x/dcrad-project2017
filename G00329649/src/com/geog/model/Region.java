package com.geog.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Region {
//	Instance variables	
	private String code;
	private String name;
	private String desc;
	private String co_code;
	
	
	
	
//	Constructors
	public Region() {
	}
	
	public Region(String code, String name, String desc, String co_code) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		
		this.co_code = co_code;
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
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCo_code() {
		return co_code;
	}

	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}
	
} // class Region
