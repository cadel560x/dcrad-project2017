package com.geog.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class City {
//	Instance variables
	private String code;
	private String name;
	private int population;
	private boolean coastal;
	private double area;
	
	private String co_code;
	private String reg_code;
	
	
	
	
//	Constructors
	public City() {
		coastal = true;
	}
	
	public City(String code, String name, int population, boolean coastal, double area, String co_code, String reg_code) {
		this.code = code;
		this.name = name;
		this.population = population;
		this.coastal = coastal;
		this.area = area;
		
		this.co_code = co_code;
		this.reg_code = reg_code;
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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	public boolean isCoastal() {
		return coastal;
	}

	public void setCoastal(boolean coastal) {
		this.coastal = coastal;
	}
	
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getCo_code() {
		return co_code;
	}

	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}

	public String getReg_code() {
		return reg_code;
	}

	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}
		
} // class City
