package ief;

import enums.NegativeTokens;
import invoices.Invoice;
import invoices.InvoiceLine;
import invoices.dao.InvoiceDAO;
import sql.dao.CompanyDAO;
import sql.dao.CustomerDAO;
import sql.models.Adress;
import sql.models.Bank;
import sql.models.Company;
import sql.models.Customer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Convert {
    private InvoiceDAO invoiceDAO = InvoiceDAO.getInstance();
    private CustomerDAO customerDAO = CustomerDAO.getInstance();
    private CompanyDAO companyDAO = CompanyDAO.getInstance();

    public void combineInfoToIEF(int maandNummer) throws IOException {
        String finalString = getInvoiceInfo(maandNummer);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice" + maandNummer + ".txt"))) {
            writer.write(finalString);
        }
    }


    String getCompanyInfo(int klantID) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Company company = companyDAO.selectCompanyInfomation(klantID);
        stringBuilder.append("B");

        String companyName = paddOrSnip(60, company.getCompanyName());
        stringBuilder.append(companyName);
        String adress = getAdress(company.getAdress());
        stringBuilder.append(adress);
        String companyVatNumber = paddOrSnip(13, company.getVatNumber());
        stringBuilder.append(getBankInfo(companyVatNumber, company.getBank()));
        return stringBuilder.toString();
    }

    String getBankInfo(String vatNumber, Bank bank) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(vatNumber);

        String companyIban = paddOrSnip(64, bank.getIban());
        stringBuilder.append(companyIban);

        String companyBic = paddOrSnip(10, bank.getBic());
        stringBuilder.append(companyBic);

        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    String getAdress(Adress adress) {
        StringBuilder stringBuilder = new StringBuilder();
        String streetName = paddOrSnip(60, adress.getStreet());
        stringBuilder.append(streetName);

        String houseNumber = paddOrSnip(10, adress.getHouseNumber());
        stringBuilder.append(houseNumber);

        String postalCode = paddOrSnip(6, adress.getPostalcode());
        stringBuilder.append(postalCode);

        String city = paddOrSnip(20, adress.getCity());
        stringBuilder.append(city);
        return stringBuilder.toString();
    }

    String getCustomerInfo(int klantID) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Customer customer = customerDAO.selectCustomerInformation(klantID);
        stringBuilder.append("K");

        String companyName = paddOrSnip(40, customer.getCompany().getCompanyName());
        stringBuilder.append(companyName);
        String salutation = paddOrSnip(6, customer.getName().getSalutation().value);
        stringBuilder.append(salutation);

        String firstName = paddOrSnip(20, customer.getName().getFirstName());
        stringBuilder.append(firstName);

        String middleName = paddOrSnip(7, customer.getName().getMiddleName());
        stringBuilder.append(middleName);

        String lastName = paddOrSnip(40, customer.getName().getLastName());
        stringBuilder.append(lastName);
        String adress = getAdress(customer.getAdress());
        stringBuilder.append(adress);

        String vatNumber = paddOrSnip(13, customer.getCompany().getVatNumber());
        stringBuilder.append(getBankInfo(vatNumber, customer.getBank()));
        return stringBuilder.toString();
    }

    String getInvoiceInfo(int maandNummer) throws IOException {
        StringBuilder invoiceStringBuilder = new StringBuilder();
        List<Invoice> invoices = invoiceDAO.getInvoicesByMonth(maandNummer);
        for (Invoice invoice : invoices) {
            double id = invoice.getCustomerId();
            int intID = (int) id;
            invoiceStringBuilder.append(getCompanyInfo(intID));
            invoiceStringBuilder.append(getCustomerInfo(intID));
            invoiceStringBuilder.append("F");

            int date = invoice.getParsedDate();
            String strDate = paddOrSnip(6, String.valueOf(date));
            int finalDate = Integer.parseInt(strDate);
            invoiceStringBuilder.append(finalDate);

            String invoiceID = String.valueOf(invoice.getInvoiceId());
            invoiceID = paddOrSnip(10, invoiceID);
            invoiceStringBuilder.append(invoiceID);

            List<InvoiceLine> invoiceLines = invoice.getInvoiceLines();
            invoiceStringBuilder.append(getInvoiceLinesFromInvoice(invoiceLines, invoice));
            invoiceStringBuilder.append("\n");
        }
        return invoiceStringBuilder.toString();
    }

    private String getInvoiceLinesFromInvoice(List<InvoiceLine> invoiceLines, Invoice invoice) {
        StringBuilder lineStringBuilder = new StringBuilder();
        for (InvoiceLine line : invoiceLines) {
            lineStringBuilder.append("\n");
            lineStringBuilder.append("R");

            String productName = splitProductDescription(line.getProductName());
            lineStringBuilder.append(productName);
            lineStringBuilder.append(convertPrice(line));

            int date = invoice.getParsedDate();
            String strDate = paddOrSnip(6, String.valueOf(date));
            lineStringBuilder.append(Integer.parseInt(strDate));

            int time = invoice.getParsedTime();
            String strTime = paddOrSnip(4, String.valueOf(time));
            lineStringBuilder.append(Integer.parseInt(strTime));
            String unit = paddOrSnip(6, line.getUnit());
            lineStringBuilder.append(unit);
        }
        return lineStringBuilder.toString();
    }

    private String convertPrice(InvoiceLine line) {
        StringBuilder stringBuilder = new StringBuilder();
        if (line.getAmount() >= 0) {
            String ammount = padDouble(3, line.getAmount());
            stringBuilder.append(ammount).append(" ");
            try {
                if (line.getTotalPrice() < 0) {
                    stringBuilder.append(generateNegativePrice(stringBuilder, line));
                }
                String price = padDouble(5, line.getTotalPrice() / Integer.parseInt(ammount));
                stringBuilder.append(price).append(" ");
            } catch (ArithmeticException e) {
                stringBuilder.append("div 0");
            }
        } else {
            stringBuilder.append(generateErrorForPrice(stringBuilder));

        }
        return stringBuilder.toString();

    }

    private String generateNegativePrice(StringBuilder stringBuilder, InvoiceLine line) {
        int number = (int) line.getTotalPrice();
        char charToConvert = getDigitForNegativeNumber(number);
        char convertedChar = negativeNumberConverter(charToConvert);
        String convertedDouble = padDouble(5, (line.getTotalPrice() / line.getAmount()));
        String finalString = convertedDouble.replaceFirst(String.valueOf(charToConvert), String.valueOf(convertedChar));
        stringBuilder.append(finalString);

        return stringBuilder.toString();
    }

    private String generateErrorForPrice(StringBuilder stringBuilder) {
        String errorAmmount = paddOrSnip(3, "err");
        stringBuilder.append(errorAmmount).append(" ");
        String errorPrice = paddOrSnip(5, "error");
        stringBuilder.append(errorPrice).append(" ");
        return stringBuilder.toString();
    }

    char getDigitForNegativeNumber(int number) {
        return ("" + number).charAt(1);
    }

    private String splitProductDescription(String productDescription) {
        if (productDescription.length() > 60) {
            return "\n" +
                    "T" +
                    paddOrSnip(120, productDescription);
        }
        return paddOrSnip(60, productDescription);
    }

    String padDouble(int prefixLength, double getal) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        int getalWithoutDecimal = (int) getal;
        int numberLength = String.valueOf(getalWithoutDecimal).length();
        StringBuilder sb = new StringBuilder();
        if (numberLength < prefixLength) {
            sb.append("0".repeat(prefixLength-numberLength));
        }
        String padded = sb.toString() + getalWithoutDecimal;

        String fraction = decimalFormat.format(getal % 1);
        String substring = fraction.length() > 2 ? fraction.substring(fraction.length() - 2) : fraction;


        return padded + substring;
    }

    String paddOrSnip(int maxLength, String content) {
        if (content == null || content.equals("")) {
            return " ".repeat(Math.max(0, maxLength));
        }
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

    char negativeNumberConverter(int number) {
        char c;
        if (number >= 0 && number <= 9) {
            c = NegativeTokens.values()[number].value;
        } else {
            c = (char) number;
        }
        return c;
    }

}
