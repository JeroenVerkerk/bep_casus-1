package Invoices;

public class InvoiceLine {
    private String productName;
    private double amount;
    private double totalPrice;
    private String unit;
    private String btw;

    public InvoiceLine(String productName, double amount, double totalPrice, String unit, String btw) {
        this.productName = productName;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.unit = unit;
        this.btw = btw;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBtw() {
        return btw;
    }

    public void setBtw(String btw) {
        this.btw = btw;
    }
}
