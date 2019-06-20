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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBtwNumber(String btwNumber) {
        this.btwNumber = btwNumber;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
