package com.geog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private Connection conn;
	private Statement myStmt;
	private StringBuilder query;
	private ResultSet rs;

	
	
	
//	Constructor
	public CityDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
//	    Instantiate the StringBuffer
	    query = new StringBuilder("");
	}
	
	
	
	
//	Methods
	public List<City> getCities() throws SQLException {
		List<City> cities = new ArrayList<>();
		
		query.append("SELECT * FROM city;");
	    rs = myStmt.executeQuery(query.toString());

	    while ( rs.next() ) {
	    	cities.add(new City(rs.getString("cty_code"), rs.getString("cty_name"), rs.getInt("population"), rs.getBoolean("isCoastal"), rs.getDouble("areaKm"), rs.getString("co_code"), rs.getString("reg_code")));
	    } // while
	    
//	    Reset the StringBuilder
	    query.setLength(0);
	    
	    return cities;

	} // getCities
	
	
	public List<City> searchCities(City cityQueryObject, String populationCriteria) throws SQLException {
		PreparedStatement myStmt; 
		List<City> cities = new ArrayList<>();
		String cty_code = cityQueryObject.getCode();
		
		query.append("SELECT * FROM city WHERE isCoastal = ? ");
		
		if ( cty_code != null ) {
			query.append("AND cty_code = ? ");
		}
		
		if ( ! populationCriteria.equals("") ) {
			query.append("AND population ");
			
			if ( populationCriteria.equalsIgnoreCase("lt") ) {
				query.append(" < ? ");
			}
			else if ( populationCriteria.equalsIgnoreCase("gt") ) {
				query.append(" > ? ");
			}
			else if ( populationCriteria.equalsIgnoreCase("eq") ) {
				query.append(" = ? ");
			}
		}
		
		myStmt = conn.prepareStatement(query.toString());
		myStmt.setBoolean(1, cityQueryObject.isCoastal());
		if ( cty_code != null ) {
			myStmt.setString(2, cty_code);
		}
		if ( ! populationCriteria.equals("") ) {
			myStmt.setInt(3, cityQueryObject.getPopulation());
		}
		
		rs = myStmt.executeQuery(query.toString());
		
		while ( rs.next() ) {
	    	cities.add(new City(rs.getString("cty_code"), rs.getString("cty_name"), rs.getInt("population"), rs.getBoolean("isCoastal"), rs.getDouble("areaKm"), rs.getString("co_code"), rs.getString("reg_code")));
	    } // while
		
		return cities;
		
	} // searchCity
	
} // class CityDao
