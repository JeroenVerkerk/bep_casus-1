package Invoices;

import mongoReader.MongoConnector;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceDAO {
    private final MongoConnector connection = MongoConnector.getInstance();
    private static InvoiceDAO single_instance;

    public static InvoiceDAO getInstance() {
        if (single_instance == null)
        {
            single_instance = new InvoiceDAO();
        }
        return single_instance;
    }

    public ArrayList<Invoice> getInvoices() {
        Iterator<Document> iterator = connection.getCollection().find().iterator();
        ArrayList<Invoice> invoices = new ArrayList<>();

        while(iterator.hasNext())
        {
            Document document = iterator.next();

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
            invoices.add(invoice);
        }
        return invoices;
    }
}