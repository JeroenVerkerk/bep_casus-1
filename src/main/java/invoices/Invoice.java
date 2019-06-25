package invoices;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private double invoiceId;
    private Date date;
    private String description;
    private List<InvoiceLine> invoiceLines;
    private double customerId;

    public Invoice(double id, Date date, String description, double customerId, List<InvoiceLine> invoiceLines) {
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

    public int getParsedDate() {
        DateFormat formatter = new SimpleDateFormat("ddMMyy");
        String formattedDate = formatter.format(this.getDate());
        return Integer.parseInt(formattedDate);

    }

    public int getParsedTime() {
        DateFormat formatter = new SimpleDateFormat("HHmm");
        String formattedTime = formatter.format(this.getDate());
        return Integer.parseInt(formattedTime);
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

    public List<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
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

        for (InvoiceLine invoiceLine : invoiceLines) {
            result.append(invoiceLine.toString());
        }

        return "Factuurnummer: " + getInvoiceId() +
                "\nDatum: " + getParsedDate() +
                "\nOmschrijving: " + getInvoiceId() +
                "\nKlant: " + getCustomerId() +
                "\nFactuurregels:" + result.toString();
    }

}