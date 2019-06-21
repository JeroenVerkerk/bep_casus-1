package mongoReader;

import Invoices.Invoice;
import Invoices.InvoiceDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MongoConnectorTest {
    private static MongoConnector connection;
    private static InvoiceDAO invoiceDAO;
    private static MongoCollection<Document> collection;

    @BeforeEach
    void getConnection() {
        connection = MongoConnector.getInstance();
        invoiceDAO = InvoiceDAO.getInstance();
        collection = connection.getCollection();
    }

    @Test
    void testCollectionHasValue() {
        assertTrue(collection != null);
    }

    @Test
    void testInvoiceAreFilled() {
        ArrayList<Invoice> invoices = invoiceDAO.getInvoices();
        assertTrue(collection.count() == invoices.size());
    }
}
