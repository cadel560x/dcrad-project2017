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

//import com.geog.model.Country;
import com.geog.model.Region;

public class RegionDao {
//	Instance variables
	private DataSource mysqlDS;
	private Connection conn;
	private Statement myStmt;
	private StringBuilder query;
	private ResultSet rs;
	List<Region> regions;

	
	
	
//	Constructor
	public RegionDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
//	    Instantiate the StringBuffer
	    query = new StringBuilder("");
	    
	    getRegions();
	}
	
	
	
	
//	Methods
	public List<Region> getRegions() throws SQLException {
		query.append("SELECT * FROM region;");
		
	    try {
			rs = myStmt.executeQuery(query.toString());
			regions = new ArrayList<>();
			
			while (rs.next()) {
				regions.add(new Region(rs.getString("reg_code"), rs.getString("reg_name"),
						rs.getString("reg_desc"), rs.getString("co_code")));
			} // while
		} finally {
			// Reset the StringBuilder
		    query.setLength(0);
		    
		} // try - finally

	    return regions;

	} // getRegions
	
	
	public Region searchRegion(String reg_code) throws SQLException {
		query.append("SELECT * FROM region WHERE reg_code = ?");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			myStmt.setString(1, reg_code);
			rs = myStmt.executeQuery();
			rs.next();
		} finally {
		// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return new Region(rs.getString("reg_code"), rs.getString("reg_name"), rs.getString("reg_desc"), rs.getString("co_code"));
		
	} // getRegion	


	public int add(Region regionAddObject) throws SQLException {
		int rs;
		query.append("INSERT INTO region VALUES(?, ?, ?, ?)");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			
			myStmt.setString(1, regionAddObject.getCo_code());
			myStmt.setString(2, regionAddObject.getCode());
			myStmt.setString(3, regionAddObject.getName());
			myStmt.setString(4, regionAddObject.getDesc());
			
			rs = myStmt.executeUpdate();
		} finally {
			// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return rs;
		
	} // add


	public List<Region> listRegions(String co_code) throws SQLException {
		query.append("SELECT reg_code, reg_name FROM region WHERE co_code = ?");
		
	    try {
	    	PreparedStatement myStmt = conn.prepareStatement(query.toString());
	    	
	    	myStmt.setString(1, co_code);
	    	
			rs = myStmt.executeQuery();
			regions = new ArrayList<>();
			
			while (rs.next()) {
				regions.add(new Region(rs.getString("reg_code"), rs.getString("reg_name")));
			} // while
		} finally {
			// Reset the StringBuilder
		    query.setLength(0);
		    
		} // try - finally
		
		return regions;
		
	} // listRegions
	
} // class RegionDao
