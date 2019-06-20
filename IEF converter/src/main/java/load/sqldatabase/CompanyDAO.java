package load.sqldatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    private DBConnector dbConnector;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    public CompanyDAO() {
        dbConnector = new DBConnector();
    }

    public Company selectCompanyInfomation(int customerId) {
        connection = dbConnector.getConnection();
        Company company = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Adres AS adres, bifi.Klant AS klant WHERE adres.klantid = ? AND klant.klantid = ? AND adres.type = 'F'");
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerId);
            ResultSet rs = stmt.executeQuery();

            String street = rs .getString("straat");
            String houseNumber = rs.getString("huisnummer");
            String postalcode = rs.getString("postcode");
            String city = rs.getString("plaats");
            String companyName = rs.getString("bedrijfsnaam");
            String btwNumber = rs.getString("vat");
            String IBAN;
            if (rs.getString("bankrek") != null) {
                IBAN = rs.getString("bankrek");
            }
            else {
                IBAN = rs.getString("giro");
            }
            String BIC = rs.getString("bik");

            Adress adress = new Adress(street, postalcode, city, houseNumber);
            Bank bank = new Bank(IBAN, BIC);
            company = new Company(companyName, btwNumber, adress, bank);

            rs.close();
            connection.close();
        }catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return company;
    }
}
