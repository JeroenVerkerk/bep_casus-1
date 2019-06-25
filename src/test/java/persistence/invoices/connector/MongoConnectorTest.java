package persistence.invoices.connector;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MongoConnectorTest {
    private static MongoConnector connection = MongoConnector.getInstance();
    private static MongoCollection<Document> collection;

    @BeforeEach
    void getCollection() {
        collection = connection.getCollection();
    }

    @Test
    void testCollectionHasValue() {
        assertTrue(collection != null);
    }
}
