package SQL.models;

import enums.Enums.*;

public class Name {
    private Salutation salutation;
    private String firstName;
    private String lastName;
    private String middleName;

    public Name(Salutation salutation, String firstName, String lastName, String middleName) {
        this.salutation = salutation;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
