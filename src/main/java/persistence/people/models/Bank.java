package persistence.people.models;

public class Bank {
    private String iban;
    private String bic;

    public Bank(String iban, String bic) {
        this.iban = iban;
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }
}
