package com.geog.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.geog.model.City;

public class CityDao {
//	Instance variables
	private DataSource mysqlDS;

	
	
	
//	Constructor
	public CityDao() throws NamingException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	
	
//	Methods
	public List<City> getCities() throws SQLException {
		List<City> cities = new ArrayList<>();
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from city;";
	    
	    ResultSet rs = myStmt.executeQuery(query);

	    while ( rs.next() ) {
    		String country_code = rs.getString("co_code");
    		String reg_code = rs.getString("reg_code");
	    	String code = rs.getString("cty_code");
	    	String name = rs.getString("cty_name");
	    	int population = rs.getInt("population");
	    	boolean coastal = rs.getBoolean("isCoastal");
	    	double areaKM = rs.getDouble("areaKm");
	    	
	    	cities.add(new City(code, name, population, coastal, areaKM, country_code, reg_code));
	    } // while
	    
	    return cities;

	} // getCities
	
} // class CityDao
