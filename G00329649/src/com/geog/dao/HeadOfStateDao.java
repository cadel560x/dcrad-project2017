package com.geog.dao;

import com.geog.model.HeadOfState;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

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

}
