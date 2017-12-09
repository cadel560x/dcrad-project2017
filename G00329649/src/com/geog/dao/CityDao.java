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
import com.geog.model.CityDetails;
//import com.geog.model.Region;

public class CityDao {
//	Instance variables
	private DataSource mysqlDS;
	private Connection conn;
	private Statement myStmt;
	private StringBuilder query;
	private ResultSet rs;
	private List<City> cities;

	
	
	
//	Constructor
	public CityDao() throws NamingException, SQLException {
		Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/geography";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	    conn = mysqlDS.getConnection();
	    myStmt = conn.createStatement();
//	    Instantiate the StringBuffer
	    query = new StringBuilder("");
	    
	    getCities();
	}
	
	
	

//	Accessors and mutators
	public List<City> getCities() throws SQLException {
//		List<City> cities = new ArrayList<>();
		cities = new ArrayList<>();
		
		query.append("SELECT cty.*, co.co_name AS 'co.co_name', reg.reg_name AS 'reg.reg_name' FROM city cty INNER JOIN country co ON cty.co_code = co.co_code INNER JOIN region reg ON cty.reg_code = reg.reg_code;");
	    rs = myStmt.executeQuery(query.toString());

	    while ( rs.next() ) {
	    		cities.add(new CityDetails(rs.getString("cty_code"), rs.getString("cty_name"),
	    				rs.getInt("population"), rs.getBoolean("isCoastal"),
	    				rs.getDouble("areaKm"), rs.getString("co_code"),
	    				rs.getString("reg_code"), rs.getString("co.co_name"), rs.getString("reg.reg_name")));
	    } // while
	    
	    // Reset the StringBuilder
	    query.setLength(0);
	    
	    return cities;

	} // getCities

	public void setCities(List<City> cities) {
		this.cities = cities;
	}




	//	Methods
	public int add(City cityAddObject) throws SQLException {
		int rs;
		query.append("INSERT INTO city VALUES(?, ?, ?, ?, ?, ?, ?)");
		
//		if ( cityQueryObject.isCoastal() ) {
//			query.append("1");
//		}
//		else {
//			query.append("2");
//		}
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query.toString());
			
			myStmt.setString(1, cityAddObject.getCode());
			myStmt.setString(2, cityAddObject.getCo_code());
			myStmt.setString(3, cityAddObject.getReg_code());
			myStmt.setString(4, cityAddObject.getName());
			myStmt.setInt(5, cityAddObject.getPopulation());
			myStmt.setInt(6, (cityAddObject.isCoastal()) ? 1 : 2);
			myStmt.setDouble(7, cityAddObject.getArea());
			
			rs = myStmt.executeUpdate();
		} finally {
			// Reset the StringBuilder		
			query.setLength(0);
			
		} // try - finally
		
		return rs;
	} // add
	
	
	// 'cityQueryObject' is a kinda hibernate paradigm
	public List<City> searchCities(City cityQueryObject, String populationAsString, String populationCriteria) throws SQLException {
		PreparedStatement myStmt;
		int stmtIndex = 0;
		List<City> cities = new ArrayList<>();
//		List<String> country_names = new ArrayList<>();
		String co_code = cityQueryObject.getCo_code();
		
		query.append("SELECT cty.*, co.co_name AS 'co.co_name', reg.reg_name AS 'reg.reg_name' FROM city cty INNER JOIN country co ON cty.co_code = co.co_code INNER JOIN region reg ON cty.reg_code = reg.reg_code WHERE isCoastal = ");
		
		if ( cityQueryObject.isCoastal() ) {
			query.append("1");
		}
		else {
			query.append("2");
		}
		
		if ( ! co_code.equals("") ) {
			query.append(" AND cty.co_code = ?");
		}
		
		if ( ! populationAsString.equals("") ) {
			query.append(" AND population ");
			
			if ( populationCriteria.equalsIgnoreCase("lt") ) {
				query.append("< ?");
			}
			else if ( populationCriteria.equalsIgnoreCase("gt") ) {
				query.append("> ?");
			}
			else if ( populationCriteria.equalsIgnoreCase("eq") ) {
				query.append("= ?");
			} // if - else if - else if
			
		} // if ( ! population.equals("") )
		
		try {
			myStmt = conn.prepareStatement(query.toString());
			if (!co_code.equals("")) {
				myStmt.setString(++stmtIndex, co_code);
			}
			
			if (!populationAsString.equals("")) {
				myStmt.setInt(++stmtIndex, cityQueryObject.getPopulation());
			}
			
			rs = myStmt.executeQuery();
			
			while (rs.next()) {
				cities.add(new CityDetails(rs.getString("cty_code"), rs.getString("cty_name"), rs.getInt("population"),
						rs.getBoolean("isCoastal"), rs.getDouble("areaKm"), rs.getString("co_code"),
						rs.getString("reg_code"), rs.getString("co.co_name"), rs.getString("reg.reg_name")));
//				country_names.add(rs.getString("co.co_name"));
			} // while
		} finally {
			// Reset the StringBuilder
		    query.setLength(0);
		} // try - finally
		
		return cities;
		
	} // searchCity
	
} // class CityDao
