package persistence.invoices.dao;

import com.mongodb.client.MongoCollection;
import invoices.connector.MongoConnector;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import persistence.invoices.models.Invoice;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoiceDAOTest {
    private static InvoiceDAO invoiceDAO;
    private static MongoConnector connection;
    private static MongoCollection<Document> collection;

    @BeforeEach
    void getCollection() {
        connection = MongoConnector.getInstance();
        collection = connection.getCollection();
        invoiceDAO = InvoiceDAO.getInstance();
    }

    @Test
    void testInvoiceAreFilled() {
        List<Invoice> invoices = invoiceDAO.getInvoices();
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
        List<Invoice> invoices = invoiceDAO.getInvoices();
        for (Invoice invoice : invoices) {
            System.out.println(invoice.toString());
        }
        assertTrue(0 == 0);

    }

    @Test
    void testInvoiceByMonth() {
        int month = 5;
        List<Invoice> invoices = invoiceDAO.getInvoicesByMonth(month);
        for (Invoice invoice : invoices) {
            assertTrue(invoice.getDate().getMonth() == month);
        }
    }
}
