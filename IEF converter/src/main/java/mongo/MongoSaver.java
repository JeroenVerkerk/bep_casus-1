package mongo;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaver {

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		String database = "";

		boolean success = true;

		try {

			MongoClientURI uri = new MongoClientURI(

					"your mongodb connection link");


			MongoClient mongoClient = new MongoClient(uri);

			MongoDatabase db = mongoClient.getDatabase( database );

//			MongoCollection<Document> c = db.getCollection("email");
//
//			Document  doc = new Document ("to", to)
//					.append("from", from)
//					.append("subject", subject)
//					.append("text", text)
//					.append("asHtml", html);
//			c.insertOne(doc);
		} catch (MongoException mongoException) {
			System.out.println("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			success = false;
		}

		return success;

	}

}