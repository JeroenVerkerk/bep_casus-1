package sql.models;

public class Company {
    private String companyName;
    private String vat;
    private Adress adress;
    private Bank bank;

    public Company(String companyName, String btwNumber, Adress adress, Bank bank) {
        this.companyName = companyName;
        this.vat = btwNumber;
        this.adress = adress;
        this.bank = bank;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getVat() {
        return vat;
    }

    public Adress getAdress() {
        return adress;
    }

    public Bank getBank() {
        return bank;
    }
}
