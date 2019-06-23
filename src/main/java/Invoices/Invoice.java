package Invoices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private double invoiceId;
    private Date date;
    private String description;
    private ArrayList<InvoiceLine> invoiceLines;
    private double customerId;

    public Invoice(double id, Date date, String description, double customerId, ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceId = id;
        this.date = date;
        this.description = description;
        this.customerId = customerId;
        this.invoiceLines = invoiceLines;
    }

    public double getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(double id) {
        this.invoiceId = id;
    }

    public Date getDate() {
        return this.date;
    }

    public int parsedDate(){
        SimpleDateFormat format = new SimpleDateFormat("DDMMYY");

        Date date = this.getDate();
        try {
            date = format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(date.toString());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public double getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(double customerId) {
        this.customerId = customerId;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (InvoiceLine invoiceLine: invoiceLines) {
            result.append(invoiceLine.toString());
        }

        return "Factuurnummer: " + getInvoiceId() +
                "\nDatum: " + getDate() +
                "\nOmschrijving: " + getInvoiceId() +
                "\nKlant: " + getCustomerId() +
                "\nFactuurregels:" + result.toString();
    }

}
