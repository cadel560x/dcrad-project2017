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

import com.geog.model.Region;

public class RegionDao {
//	Instance variables
	private DataSource mysqlDS;
	private Connection conn;
	private Statement myStmt;
	private StringBuilder query;
	private ResultSet rs;

	
	
	
//	Constructor
	public RegionDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
//	    Instantiate the StringBuffer
	    query = new StringBuilder("");
	}
	
	
	
	
//	Methods
	public List<Region> getRegions() throws SQLException {
		List<Region> regions = new ArrayList<>();

		query.append("SELECT * FROM region;");
	    rs = myStmt.executeQuery(query.toString());

	    while ( rs.next() ) {
	    		regions.add(new Region(rs.getString("reg_code"), rs.getString("reg_name"), new StringBuilder(rs.getString("reg_desc")), rs.getString("co_code")));
	    } // while
	    
//	    Reset the StringBuilder
	    query.setLength(0);	    
	    
	    return regions;

	} // getRegions
	
	
	public Region searchRegion(String reg_code) throws SQLException {
		query.append("SELECT * FROM region WHERE reg_code = ?");
		PreparedStatement myStmt = conn.prepareStatement(query.toString());
		myStmt.setString(1, reg_code);
		
		ResultSet rs = myStmt.executeQuery();
		rs.next();
		
//	    Reset the StringBuilder		
		query.setLength(0);
		
		return new Region(rs.getString("reg_code"), rs.getString("reg_name"), new StringBuilder(rs.getString("reg_desc")), rs.getString("co_code"));
		
	} // getRegion	
	
} // class RegionDao
