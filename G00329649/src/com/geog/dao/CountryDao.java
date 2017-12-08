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

import com.geog.model.Country;

public class CountryDao {
//	Instance variables
	private DataSource mysqlDS;
	private Connection conn;
	private Statement myStmt;
	private StringBuilder query;
	private ResultSet rs;
	List<Country> countries;

	
	
	
//	Constructor
	public CountryDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
	    // Instantiate the StringBuffer
	    query = new StringBuilder("");
	    
	    getCountries();
	}
	
	
	
	
//	Methods
	public List<Country> getCountries() throws SQLException {
//		List<Country> countries;
		
		query.append("SELECT * FROM country;");
		
	    try {
			rs = myStmt.executeQuery(query.toString());
			countries = new ArrayList<>();
			
			while (rs.next()) {
				countries.add(new Country(rs.getString("co_code"), rs.getString("co_name"),
						rs.getString("co_details")));
			} // while
		} finally {
			// Reset the StringBuilder
		    query.setLength(0);
		    
		} // try - finally
		
	    return countries;

	} // getCountries
	
	
	public Country searchCountry(String co_code) throws SQLException {
		query.append("SELECT * FROM country WHERE co_code = ?");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			myStmt.setString(1, co_code);
			rs = myStmt.executeQuery();
			rs.next();
		} finally {
			// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return new Country(rs.getString("co_code"), rs.getString("co_name"), rs.getString("co_details"));
		
	} // searchCountry
	
	
	// 'countryAddObject' is a kinda hibernate paradigm
	public int add(Country countryAddObject) throws SQLException {
		int rs;
		query.append("INSERT INTO country VALUES(?, ?, ?)");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			myStmt.setString(1, countryAddObject.getCode());
			myStmt.setString(2, countryAddObject.getName());
//			if ( country.getDetails().length() > 399 ) {
//				myStmt.setString(3, country.getDetails().substring(0, 399));
//			}
//			else {
//				myStmt.setString(3, country.getDetails());
//			}
			myStmt.setString(3, countryAddObject.getDetails());
			rs = myStmt.executeUpdate();
		} finally {
			// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return rs;
		
	} // add
	
	
	// 'countryUpdateObject' is a kinda hibernate paradigm
	public int update(Country countryUpdateObject) throws SQLException {
		int rs;
		query.append("UPDATE country SET co_name = ?, co_details = ? WHERE co_code = ?");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			myStmt.setString(3, countryUpdateObject.getCode());
			myStmt.setString(1, countryUpdateObject.getName());
//			if ( country.getDetails().length() > 399 ) {
//				myStmt.setString(3, country.getDetails().substring(0, 399));
//			}
//			else {
//				myStmt.setString(3, country.getDetails());
//			}
			myStmt.setString(2, countryUpdateObject.getDetails());
			rs = myStmt.executeUpdate();
		} finally {
			// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return rs;
		
	} // update

} // CountryDao
