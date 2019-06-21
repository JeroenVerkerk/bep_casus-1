package IEF;

import Invoices.Invoice;
import Invoices.InvoiceDAO;
import Invoices.InvoiceLine;

import java.util.ArrayList;

public class Convert {
    private InvoiceDAO invoiceDAO = InvoiceDAO.getInstance();

    public void combineInfoToIEF(int maandNummer) {
        StringBuilder finalString = new StringBuilder();
        finalString.append(getInvoiceInfo(maandNummer));
        FileHandler fileHandler = new FileHandler();
        fileHandler.generateFile(finalString.toString(), "./Invoice" + maandNummer + ".txt");
    }

    public String getCompanyInfo() {
        StringBuilder companyStringBuilder = new StringBuilder();
        companyStringBuilder.append("B");


        companyStringBuilder.append("\n");
        return companyStringBuilder.toString();
    }

    public String getCustomerInfo() {
        StringBuilder customerStringBuilder = new StringBuilder();
        customerStringBuilder.append("K");


        return customerStringBuilder.toString();
    }

    public String getInvoiceInfo(int maandNummer) {
        StringBuilder invoiceStringBuilder = new StringBuilder();
        ArrayList<Invoice> invoices = invoiceDAO.getInvoicesByMonth(maandNummer);
        invoiceStringBuilder.append("F");
        for (Invoice invoice : invoices) {
            invoiceStringBuilder.append("F");

            int date = invoice.parsedDate();
            String strDate = paddOrSnip(6, String.valueOf(date));
            int finalDate = Integer.parseInt(strDate);
            invoiceStringBuilder.append(finalDate);

            String invoiceID = String.valueOf(invoice.getInvoiceId());
            invoiceID = paddOrSnip(10, invoiceID);
            invoiceStringBuilder.append(invoiceID);
            invoiceStringBuilder.append("\n");

            ArrayList<InvoiceLine> invoiceLines = invoice.getInvoiceLines();
            invoiceStringBuilder.append(getInvoiceLines(invoiceLines));
        }
        return invoiceStringBuilder.toString();
    }

    public String getInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        StringBuilder lineStringBuilder = new StringBuilder();
        for (InvoiceLine line : invoiceLines) {
            lineStringBuilder.append("R");

            String productName = splitProductDescription(line.getProductName());
            lineStringBuilder.append(productName);

            double ammount = doubleConverter(3, 2, line.getAmount());
            lineStringBuilder.append(ammount);

            double price = doubleConverter(5, 2, line.getTotalPrice() / ammount);
            lineStringBuilder.append(price);

            // TODO: BTW type, regel datum, regel tijd
            String unit = paddOrSnip(6, line.getUnit());
            lineStringBuilder.append(unit);
            lineStringBuilder.append("\n");
        }
        return lineStringBuilder.toString();
    }

    public String splitProductDescription(String productDescription) {
        if (productDescription.length() > 60) {
            StringBuilder descStringBuilder = new StringBuilder();
            descStringBuilder.append("\n");
            descStringBuilder.append("T");
            descStringBuilder.append(productDescription);
            return descStringBuilder.toString();
        }
        return productDescription;
    }

    public double doubleConverter(int prefixLength, int decimals, double getal) {
        //todo: implementation & test

        return getal;
    }

    public String paddOrSnip(int maxLength, String content) {
        int actualLength = content.length();
        if (actualLength >= maxLength) {
            try {
                content = content.substring(0, maxLength);
            } catch (StringIndexOutOfBoundsException e) {
                return content;
            }
        }
        if (actualLength < maxLength) {
            content = content + " ".repeat(Math.max(0, maxLength - actualLength));
        }
        return content;
    }

    public char negativeNumberConverter(int number) {
        char c;
        switch (number) {
            case 0:
                c = ' ';
                break;
            case 1:
                c = '!';
                break;
            case 2:
                c = '"';
                break;
            case 3:
                c = '#';
                break;
            case 4:
                c = '$';
                break;
            case 5:
                c = '%';
                break;
            case 6:
                c = '&';
                break;
            case 7:
                c = '\\';
                break;
            case 8:
                c = '(';
                break;
            case 9:
                c = ')';
                break;
            default:
                c = (char) number;
                break;
        }
        return c;
    }

}
