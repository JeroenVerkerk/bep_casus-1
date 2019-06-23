package invoices;

public class InvoiceLine {
    private String productName;
    private double amount;
    private double totalPrice;
    private String unit;
    private String vat;

    public InvoiceLine(String productName, double amount, double totalPrice, String unit, String vat) {
        this.productName = productName;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.unit = unit;
        this.vat = vat;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getVat() {
        return this.vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String toString() {
        return "\nProductnaam: " + getProductName() +
                "\nAantal: " + getAmount() +
                "\nPrijs: " + getTotalPrice() +
                "\nEenheid: " + getUnit() +
                "\nBTW: " + getVat() + "\n";
    }
}
