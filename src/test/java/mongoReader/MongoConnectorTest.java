package mongoReader;

import Invoices.Invoice;
import Invoices.InvoiceDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MongoConnectorTest {
    private final MongoConnector connection = MongoConnector.getInstance();
    private final InvoiceDAO invoiceDAO = InvoiceDAO.getInstance();

    @Test
    void testCollectionHasValue() {
        MongoCollection<Document> collection = connection.getCollection();
        assertTrue(collection != null);
    }

    @Test
    void testInvoice() {
        // temporary test
        MongoCollection<Document> collection = connection.getCollection();
        ArrayList<Invoice> invoices = invoiceDAO.getInvoices();
        assertTrue(collection.count() == invoices.size());
    }
}
