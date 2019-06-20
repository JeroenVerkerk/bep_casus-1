package load.sqldatabase;

public class Company {
    private String companyName;
    private String btwNumber;
    private Adress adress;
    private Bank bank;

    public Company(String companyName, String btwNumber, Adress adress, Bank bank) {
        this.companyName = companyName;
        this.btwNumber = btwNumber;
        this.adress = adress;
        this.bank = bank;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBtwNumber() {
        return btwNumber;
    }

    public Adress getAdress() {
        return adress;
    }

    public Bank getBank() {
        return bank;
    }
}
