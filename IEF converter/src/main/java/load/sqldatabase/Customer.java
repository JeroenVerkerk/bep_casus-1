package load.sqldatabase;

public class Customer {
    private Name name;
    private Adress adress;
    private Bank bank;
    private Company company;

    public Customer(Name name, Adress adress, Bank bank, Company company  ) {
        this.name = name;
        this.adress = adress;
        this.bank = bank;
        this.company = company;
    }

    public Customer(Name name, Adress adress, Bank bank) {
        this.name = name;
        this.adress = adress;
        this.bank = bank;
    }

    public Name getName() {
        return name;
    }

    public Adress getAdress() {
        return adress;
    }

    public Bank getBank() {
        return bank;
    }

    public Company getCompany() {
        return company;
    }
}
