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
						new StringBuilder(rs.getString("co_details"))));
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
		
		return new Country(rs.getString("co_code"), rs.getString("co_name"), new StringBuilder(rs.getString("co_details")));
		
	} // searchCountry

} // CountryDao
