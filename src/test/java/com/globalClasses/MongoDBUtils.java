package com.globalClasses;

public class MongoDBUtils {
	public static boolean existID(String env, String mDataBase, String collection, String id) {
		MongoDBConnection db = new MongoDBConnection(env, mDataBase);
		boolean exist = db.compareID(collection, id);
		db.close();
		return exist;
	}
	
	public static String getRandomID(String env, String mDataBase, String collection) {
		MongoDBConnection db = new MongoDBConnection(env, mDataBase);
		String id = db.foundRandomID(collection);
		db.close();
		return id;
	}
}
