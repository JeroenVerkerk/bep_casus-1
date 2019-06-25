package invoices.dao;

import enums.Vat;
import invoices.Invoice;
import invoices.InvoiceLine;
import com.mongodb.client.model.Filters;
import monogreader.MongoConnector;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    public List<Invoice> getInvoices() {
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
        return this.fillInvoice(iterator.next());
    }

    public List<Invoice> getInvoicesByMonth(int month) {
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

            Vat vat;
            if (line.getString(BTWCODE).equals("hoog")) {
                vat = Vat.HIGH;
            }
            else if (line.getString(BTWCODE).equals("laag")) {
                vat = Vat.HIGH;
            }
            else if (line.getString(BTWCODE).equals("geen")) {
                vat = Vat.NONE;
            }
            else {
                // exception no valid btw code
                vat = Vat.NONE;
            }

            InvoiceLine invoiceLine = new InvoiceLine(productName, amount, totalPrice, unit, vat);
            invoiceLines.add(invoiceLine);
        }

        return new Invoice(id, date, description, customerId, invoiceLines);
    }
}