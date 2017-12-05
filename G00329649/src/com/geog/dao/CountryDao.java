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
	private static DataSource mysqlDS;
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
//		String code;
//    	String name;
//    	StringBuilder details;
		
		query = "select * from country;";
	    rs = myStmt.executeQuery(query);

	    while ( rs.next() ) {
//	    	code = rs.getString("co_code");
//	    	name = rs.getString("co_name");
//	    	details = new StringBuilder(rs.getString("co_details"));
	    	
	    	countries.add(new Country(rs.getString("co_code"), rs.getString("co_name"), new StringBuilder(rs.getString("co_details"))));
	    } // while
	    
	    return countries;

	} // getCountries
	
	
	public static Country getCountry(String co_code) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * " +
			"from contry " +
			"where co_code = ?;");
		myStmt.setString(1, co_code);
		ResultSet rs = myStmt.executeQuery();
		
		return new Country(rs.getString("co_code"), rs.getString("co_name"), new StringBuilder(rs.getString("co_details")));
		
	} // getCountry

} // CountryDao
