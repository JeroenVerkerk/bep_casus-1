package invoices.dao;

import invoices.Invoice;
import invoices.InvoiceLine;
import com.mongodb.client.model.Filters;
import mongoReader.MongoConnector;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceDAO {
    private final MongoConnector connection = MongoConnector.getInstance();
    private static InvoiceDAO singleInstance;

    public static InvoiceDAO getInstance() {
        if (singleInstance == null) {
            singleInstance = new InvoiceDAO();
        }
        return singleInstance;
    }

    public ArrayList<Invoice> getInvoices() {
        Iterator<Document> iterator = connection.getCollection().find().iterator();
        ArrayList<Invoice> invoices = new ArrayList<>();

        while (iterator.hasNext()) {
            Invoice newInvoice = this.fillInvoice(iterator.next());
            invoices.add(newInvoice);
        }
        return invoices;
    }

    public Invoice getInvoiceById(double invoiceId) {
        Iterator<Document> iterator = connection.getCollection().find(Filters.and(Filters.eq("invoiceId", invoiceId))).limit(1).iterator();
        Invoice newInvoice = this.fillInvoice(iterator.next());
        return newInvoice;
    }

    public ArrayList<Invoice> getInvoicesByMonth(int month) {
        ArrayList<Invoice> invoices = new ArrayList<>();
        Iterator<Invoice> iterator = this.getInvoices().iterator();

        while (iterator.hasNext()) {
            Invoice invoice = iterator.next();
            if (invoice.getDate().getMonth() == month) {
                invoices.add(invoice);
            }
        }
        return invoices;
    }


    private Invoice fillInvoice(Document document) {

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
            String btw = line.getString("btwCode");

            InvoiceLine invoiceLine = new InvoiceLine(productName, amount, totalPrice, unit, btw);
            invoiceLines.add(invoiceLine);
        }

        Invoice invoice = new Invoice(id, date, description, customerId, invoiceLines);
        return invoice;
    }
}