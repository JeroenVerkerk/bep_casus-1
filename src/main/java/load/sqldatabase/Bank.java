package load.sqldatabase;

public class Bank {
    private String IBAN;
    private String BIC;

    public Bank() {

    }

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

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setBIC(String BIC) {
        this.BIC = BIC;
    }
}
