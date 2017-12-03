package com.geog.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Region {
//	Instance variables	
	private String code;
	private String name;
	private StringBuilder desc;
	
	
	
	
//	Constructor	
	public Region() {
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
	
	public StringBuilder getDesc() {
		return desc;
	}
	
	public void setDesc(StringBuilder desc) {
		this.desc = desc;
	}
	
} // class Region
