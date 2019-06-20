package Invoices;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
    private double id;
    private Date date;
    private String description;
    private ArrayList<InvoiceLine> invoiceLines;
    private double customerId;

    public Invoice(double id, Date date, String description, double customerId, ArrayList<InvoiceLine> invoiceLines) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.customerId = customerId;
        this.invoiceLines = invoiceLines;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public double getCustomerId() {
        return customerId;
    }

    public void setCustomerId(double customerId) {
        this.customerId = customerId;
    }
}
