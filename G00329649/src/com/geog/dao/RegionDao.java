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

import com.geog.model.Region;

public class RegionDao {
//	Instance variables
	private DataSource mysqlDS;

	
	
	
//	Constructor
	public RegionDao() throws NamingException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	
	
//	Methods
	public List<Region> getRegions() throws SQLException {
		List<Region> regions = new ArrayList<>();
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from region;";
	    
	    ResultSet rs = myStmt.executeQuery(query);

	    while ( rs.next() ) {
    		String country_code = rs.getString("co_code");
	    	String code = rs.getString("reg_code");
	    	String name = rs.getString("reg_name");
	    	StringBuilder desc = new StringBuilder(rs.getString("reg_desc"));
	    	
	    	regions.add(new Region(code, name, desc, country_code));
	    } // while
	    
	    return regions;

	} // getRegions
	
} // class RegionDao
