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
    private DBConnector dbConnector = DBConnector.getInstance();
    private Connection connection;
    //private Company company;
    private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    public List<Company> selectCompanyInfomation(int customerId) {
        connection = dbConnector.getConnection();
        List<Company> companies = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Adres AS adres, bifi.Klant AS klant WHERE adres.klantid = ? AND klant.klantid = ? AND adres.type = 'F'");
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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
                Company company = new Company(companyName, btwNumber, adress, bank);
                companies.add(company);
            }


            rs.close();
            connection.close();
        }catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return companies;
    }
}
