package persistence.people.models;

public class Adress {
    private String street;
    private String postalcode;
    private String city;
    private String houseNumber;

    public Adress(String street, String postalcode, String city, String houseNumber) {
        this.street = street;
        this.postalcode = postalcode;
        this.city = city;
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
}
