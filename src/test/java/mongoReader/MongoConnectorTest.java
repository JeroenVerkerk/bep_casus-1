package mongoReader;

import invoices.Invoice;
import invoices.dao.InvoiceDAO;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @Test
    void testInvoiceById() {
        double invoiceId = 1;
        Invoice invoice = invoiceDAO.getInvoiceById(invoiceId);
        assertTrue(invoice.getInvoiceId() == invoiceId);

    }

    @Disabled
    @Test
    void testGetInvoiceInfo() {
        ArrayList<Invoice> invoices = invoiceDAO.getInvoices();
        for (Invoice invoice : invoices) {
            System.out.println(invoice.toString());
        }
        assertTrue(0 == 0);

    }

    @Test
    void testInvoiceByMonth() {
        int month = 5;
        ArrayList<Invoice> invoices = invoiceDAO.getInvoicesByMonth(month);
        for (Invoice invoice : invoices) {
            assertTrue(invoice.getDate().getMonth() == month);
        }
    }
}
