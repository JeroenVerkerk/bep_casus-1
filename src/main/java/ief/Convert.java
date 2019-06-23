package ief;

import invoices.Invoice;
import invoices.InvoiceLine;
import invoices.dao.InvoiceDAO;
import sql.dao.CompanyDAO;
import sql.dao.CustomerDAO;
import sql.models.Company;
import sql.models.Customer;

import java.util.ArrayList;

public class Convert {
    private InvoiceDAO invoiceDAO = InvoiceDAO.getInstance();
    private CustomerDAO customerDAO = new CustomerDAO();
    private CompanyDAO companyDAO = new CompanyDAO();

    public String combineInfoToIEF(int maandNummer) {
        StringBuilder finalString = new StringBuilder();
        for (int ID : getCustomerIDByMonth(maandNummer)) {
            finalString.append(getCompanyInfo(ID, "F"));
            finalString.append(getCustomerInfo(ID, "F"));
            finalString.append(getInvoiceInfo(maandNummer));
        }
        FileHandler fileHandler = new FileHandler();
        fileHandler.generateFile(finalString.toString(), "./Invoice" + maandNummer + ".txt");

        return finalString.toString();
    }

    public ArrayList<Integer> getCustomerIDByMonth(int maandNummer) {
        ArrayList<Invoice> invoices = invoiceDAO.getInvoicesByMonth(maandNummer);
        ArrayList<Integer> customerIDs = new ArrayList<>();
        for (Invoice invoice : invoices) {
            int id = (int) invoice.getCustomerId();
            customerIDs.add(id);
        }
        return customerIDs;
    }

    public String getCompanyInfo(int klantID, String addressType) {
        StringBuilder companyStringBuilder = new StringBuilder();
        Company company = companyDAO.selectCompanyInfomation(klantID, addressType);
        companyStringBuilder.append("B");

        String companyName = paddOrSnip(60, company.getCompanyName());
        companyStringBuilder.append(companyName);

        String companyStreetName = paddOrSnip(60, company.getAdress().getStreet());
        companyStringBuilder.append(companyStreetName);

        String companyHouseNumber = paddOrSnip(10, company.getAdress().getHouseNumber());
        companyStringBuilder.append(companyHouseNumber);

        String companyPostalCode = paddOrSnip(6, company.getAdress().getPostalcode());
        companyStringBuilder.append(companyPostalCode);

        String companyCity = paddOrSnip(20, company.getAdress().getCity());
        companyStringBuilder.append(companyCity);

        String companyVatNumber = paddOrSnip(13, company.getBtwNumber());
        companyStringBuilder.append(companyVatNumber);

        String companyIban = paddOrSnip(64, company.getBank().getIban());
        companyStringBuilder.append(companyIban);

        String companyBic = paddOrSnip(10, company.getBank().getBic());
        companyStringBuilder.append(companyBic);

        companyStringBuilder.append("\n");
        return companyStringBuilder.toString();
    }

    public String getCustomerInfo(int klantID, String addressType) {
        StringBuilder customerStringBuilder = new StringBuilder();
        Customer customer = customerDAO.selectCustomerInformation(klantID, addressType);
        customerStringBuilder.append("K");


        String companyName = paddOrSnip(40, customer.getCompany().getCompanyName());
        customerStringBuilder.append(companyName);

        String salutation = paddOrSnip(6, customer.getName().getSalutation().salutationValue);
        customerStringBuilder.append(salutation);

        String firstName = paddOrSnip(20, customer.getName().getFirstName());
        customerStringBuilder.append(firstName);

        String middleName = paddOrSnip(7, customer.getName().getMiddleName());
        customerStringBuilder.append(middleName);

        String lastName = paddOrSnip(40, customer.getName().getLastName());
        customerStringBuilder.append(lastName);

        String streetName = paddOrSnip(60, customer.getAdress().getStreet());
        customerStringBuilder.append(streetName);

        String houseNumber = paddOrSnip(10, customer.getAdress().getHouseNumber());
        customerStringBuilder.append(houseNumber);

        String postalCode = paddOrSnip(6, customer.getAdress().getPostalcode());
        customerStringBuilder.append(postalCode);

        String cityName = paddOrSnip(20, customer.getAdress().getCity());
        customerStringBuilder.append(cityName);

        String vatNumber = paddOrSnip(13, customer.getCompany().getBtwNumber());
        customerStringBuilder.append(vatNumber);

        String iban = paddOrSnip(64, customer.getBank().getIban());
        customerStringBuilder.append(iban);

        String bic = paddOrSnip(10, customer.getBank().getBic());
        customerStringBuilder.append(bic);

        customerStringBuilder.append("\n");
        return customerStringBuilder.toString();
    }

    public String getInvoiceInfo(int maandNummer) {
        StringBuilder invoiceStringBuilder = new StringBuilder();
        ArrayList<Invoice> invoices = invoiceDAO.getInvoicesByMonth(maandNummer);
        for (Invoice invoice : invoices) {
            invoiceStringBuilder.append("F");

            int date = invoice.getParsedDate();
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
