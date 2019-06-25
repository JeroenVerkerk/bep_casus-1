package persistence.invoices.dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.invoices.connector.MongoConnector;
import persistence.invoices.models.Invoice;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoiceDAOTest {
    private static InvoiceDAO invoiceDAO = InvoiceDAO.getInstance();
    private static MongoConnector connection = MongoConnector.getInstance();
    private static MongoCollection<Document> collection;

    @BeforeEach
    void getCollection() {
        collection = connection.getCollection();
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

    @Test
    void testInvoiceByMonth() {
        int month = 5;
        Calendar cal = Calendar.getInstance();
        List<Invoice> invoices = invoiceDAO.getInvoicesByMonth(month);
        for (Invoice invoice : invoices) {
            cal.setTime(invoice.getDate());
            assertTrue(cal.get(Calendar.MONTH) == month);
        }
    }
}
