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

import com.geog.model.Country;

public class CountryDao {
//	Instance variables
	private DataSource mysqlDS;
	private Connection conn;
	private Statement myStmt;
	private String query;
	private ResultSet rs;

	
	
	
//	Constructor
	public CountryDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
	}
	
	
	
	
//	Methods
	public List<Country> getCountries() throws SQLException {
		List<Country> countries = new ArrayList<>();
		
//		Statement myStmt = conn.createStatement();
		query = "select * from country;";
	    rs = myStmt.executeQuery(query);

	    while ( rs.next() ) {
	    	String code = rs.getString("co_code");
	    	String name = rs.getString("co_name");
	    	StringBuilder details = new StringBuilder(rs.getString("co_details"));
	    	
	    	countries.add(new Country(code, name, details));
	    } // while
	    
	    return countries;

	} // getCountries
	
	
	static public Country getCountry(String co_code) {
//		query = "select * from country;";
//	    rs = myStmt.executeQuery(query);
	    
		Country country = new Country();
		
		return country;
		
	} // getCountry

} // CountryDao
