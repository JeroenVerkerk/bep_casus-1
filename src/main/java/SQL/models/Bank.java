package SQL.models;

public class Bank {
    private String IBAN;
    private String BIC;

    public Bank(String IBAN, String BIC) {
        this.IBAN = IBAN;
        this.BIC = BIC;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getBIC() {
        return BIC;
    }
}
