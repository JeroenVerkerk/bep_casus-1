package monogreader;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MongoConnector {

	static Logger logger = LoggerFactory.getLogger(MongoConnector.class);
	private static MongoConnector singleInstance;
	private final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://admin:admin@bepcasus-u1lfa.mongodb.net/test?retryWrites=true&w=majority"));

	public static MongoConnector getInstance() {
		if (singleInstance == null) {
			singleInstance = new MongoConnector();
		}
		return singleInstance;
	}

	public MongoCollection<Document> getCollection () {
		String database = "bifi";
		MongoCollection<Document> collection;


		try {

			MongoDatabase db = mongoClient.getDatabase(database);
			collection = db.getCollection("factuur");

		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			logger.debug("Result{}", mongoException.getStackTrace());
			collection = null;
		}

		return collection;
	}
}