package load.sqldatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import enums.Enums.*;


public class CustomerDAO {
    private DBConnector connector = DBConnector.getInstance();
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    public Customer selectKlantinformatie(int customerId) {
        connection = connector.getConnection();
        Customer customer = null;
        Name name;
        Company company;
        Adress adress;
        Bank bank;
        Salutation salutation;
        String IBAN;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Persoon AS persoon, bifi.Klant AS klant, bifi.Adres AS adres WHERE persoon.klantid = ? AND adres.klantid = ? AND klant.klantid = ? AND adres.type = 'F'");
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerId);
            stmt.setInt(3, customerId);
            ResultSet rs = stmt.executeQuery();

            String firstName = rs.getString("voornaam");
            String lastName = rs.getString("achternaam");
            String middleName = rs.getString("tussenvoegsel");
            String street = rs.getString("straat");
            String houseNumber = rs.getString("huisnummer");
            String postalcode = rs.getString("postcode");
            String city = rs.getString("plaats");
            String bic = rs.getString("bic");


            if (rs.getString("geslacht") == "m") {
                salutation = Salutation.DHR;
            }
            else {
                salutation = Salutation.MVR;
            }

            if (rs.getString("bankrek") != null) {
                IBAN = rs.getString("bankrek");
            }
            else {
                IBAN = rs.getString("giro");
            }

            if (rs.getString("bedrijfsnaam") == null) {
                name = new Name(salutation, firstName, lastName, middleName);
                bank = new Bank(IBAN, bic);
                adress = new Adress(street, postalcode, city, houseNumber);
                customer = new Customer(name, adress, bank);
            }
            else {
                String companyName = rs.getString("bedrijfsnaam");
                String btwNumber = rs.getString("vat");

                name = new Name(salutation, firstName, lastName, middleName);
                bank = new Bank(IBAN, bic);
                adress = new Adress(street, postalcode, city, houseNumber);
                company = new Company(companyName, btwNumber, adress, bank);
                customer = new Customer(name, adress, bank, company);
            }

            rs.close();
            connection.close();
        }catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return customer;
    }
}
