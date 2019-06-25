package persistence.invoices.models;

import logic.enums.Vat;

public class InvoiceLine {
    private String productName;
    private double amount;
    private double totalPrice;
    private String unit;
    private Vat vat;

    public InvoiceLine(String productName, double amount, double totalPrice, String unit, Vat vat) {
        this.productName = productName;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.unit = unit;
        this.vat = vat;
    }

    public String getProductName() {
        return this.productName;
    }

    public double getAmount() {
        return this.amount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public String getUnit() {
        return this.unit;
    }

}
