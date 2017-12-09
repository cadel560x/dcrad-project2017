package com.geog.dao;

import com.geog.model.HeadOfState;
import com.geog.model.Region;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;


public class HeadOfStateDao {
//	Instance variables
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> headsOfState;
	List<HeadOfState> headsOfStateList;
	
	
	
	
//	Constructors
	public HeadOfStateDao() {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("headsOfStateDB");
		headsOfState = database.getCollection("headsOfState");
		
	}
	
	
	
	
//	Methods
	public List<HeadOfState> getHeadsOfState() {
		headsOfStateList = new ArrayList<>();
		Gson gson = new Gson();
		
		FindIterable<Document> users = headsOfState.find();
		
		for (Document d: users) {
			HeadOfState headOfState = gson.fromJson(d.toJson(), HeadOfState.class);
			headsOfStateList.add(headOfState);
		}
		
		return headsOfStateList;
		
	} // getHeadsOfState

	
//	public int add(HeadOfState headOfState) throws SQLException {
//		int rs;
//		query.append("INSERT INTO region VALUES(?, ?, ?, ?)");
//		
//		try {
//			PreparedStatement myStmt = conn.prepareStatement(query.toString());
//			
//			myStmt.setString(1, regionAddObject.getCo_code());
//			myStmt.setString(2, regionAddObject.getCode());
//			myStmt.setString(3, regionAddObject.getName());
//			myStmt.setString(4, regionAddObject.getDesc());
//			
//			rs = myStmt.executeUpdate();
//		} finally {
//			// Reset the StringBuilder		
//			query.setLength(0);
//			
//		} // try - finally
//		
//		return rs;
//		
//	} // add
	
} // class HeadOfStateDao
