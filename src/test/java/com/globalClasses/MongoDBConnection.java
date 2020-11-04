package com.globalClasses;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBConnection {
	private static MongoClient mClient;
	private static MongoDatabase mDataBase;

	public MongoDBConnection(String env, String db) {
		Properties prop = new Properties();
		String propFileName = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			String uriString = prop.getProperty(env + "." + db);
			getMongoClient(uriString);
			mDataBase = getDB(db);
			System.out.println("Connection successful");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to make connection!");
		}
	}

	public void close() {
		try {
			if (mClient != null) {
				mClient.close();
				mClient = null;
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private MongoClient getMongoClient(String uriString) {
		if (mClient == null) {
			mClient = new MongoClient(new MongoClientURI(uriString));
		}
		return mClient;
	}

	private MongoDatabase getDB(String db) {
		return mClient.getDatabase(db);
	}

	public boolean compareID(String collection, String id) {
		boolean exist = false;
		MongoCollection<Document> coll = mDataBase.getCollection(collection);
		FindIterable<Document> findIterable = coll.find(Filters.eq("_id", new ObjectId(id)));
		try {
			for (Document doc : findIterable) {
				JSONObject mObject = new JSONObject(doc.toJson());
				String mongoID = mObject.getJSONObject("_id").get("$oid").toString();
				exist = mongoID.equals(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exist = false;
		}
		return exist;
	}
}
