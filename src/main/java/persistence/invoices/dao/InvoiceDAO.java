package persistence.invoices.dao;

import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import logic.enums.Vat;
import org.bson.Document;
import persistence.invoices.connector.MongoConnector;
import persistence.invoices.models.Invoice;
import persistence.invoices.models.InvoiceLine;

import java.util.*;

public class InvoiceDAO {
    private final MongoConnector connection = MongoConnector.getInstance();
    private static InvoiceDAO singleInstance;
    private static final String BTWCODE = "btwCode";

    public static InvoiceDAO getInstance() {
        if (singleInstance == null) {
            singleInstance = new InvoiceDAO();
        }
        return singleInstance;
    }

    public Invoice getInvoiceById(double invoiceId) {
        Iterator<Document> iterator = connection.getCollection().find(Filters.and(Filters.eq("invoiceId", invoiceId))).limit(1).iterator();
        return this.fillInvoice(iterator.next());
    }

    public List<Invoice> getInvoicesByMonth(int month) {
        ArrayList<Invoice> invoices = new ArrayList<>();
        Iterator<Invoice> iterator = this.getInvoices().iterator();
        Calendar cal = Calendar.getInstance();

        while (iterator.hasNext()) {
            Invoice invoice = iterator.next();
            cal.setTime(invoice.getDate());
            if (cal.get(Calendar.MONTH) == month) {
                invoices.add(invoice);
            }
        }
        return invoices;
    }

    public List<Invoice> getInvoices() {
        Iterator<Document> iterator = connection.getCollection().find().iterator();
        ArrayList<Invoice> invoices = new ArrayList<>();

        while (iterator.hasNext()) {
            Invoice newInvoice = this.fillInvoice(iterator.next());
            invoices.add(newInvoice);
        }
        return invoices;
    }

    private Invoice fillInvoice(Document document) throws MongoException {
        double id = document.getDouble("invoiceId");
        Date date = document.getDate("date");
        String description = document.getString("note");
        double customerId = document.getDouble("customerId");

        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        List<Document> lines = (List<Document>) document.get("invoiceLines");
        for (Document line : lines) {

            String productName = line.getString("productName");
            double amount = line.getDouble("quantity");
            double totalPrice = line.getDouble("totalPrice");
            String unit = line.getString("unit");

            Vat vat;
            if (line.getString(BTWCODE).equals("hoog")) {
                vat = Vat.HIGH;
            }
            else if (line.getString(BTWCODE).equals("laag")) {
                vat = Vat.LOW;
            }
            else if (line.getString(BTWCODE).equals("geen")) {
                vat = Vat.NONE;
            }
            else {
                throw new MongoException("Invalid VAT code");
            }

            InvoiceLine invoiceLine = new InvoiceLine(productName, amount, totalPrice, unit, vat);
            invoiceLines.add(invoiceLine);
        }

        return new Invoice(id, date, description, customerId, invoiceLines);
    }
}