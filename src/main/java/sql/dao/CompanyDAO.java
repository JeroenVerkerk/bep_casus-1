package sql.dao;

import sql.connector.DBConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.models.Adress;
import sql.models.Bank;
import sql.models.Company;

public class CompanyDAO {
    private DBConnector dbConnector = DBConnector.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    private static CompanyDAO singleInstance;

    public static CompanyDAO getInstance() {
        if (singleInstance == null) {
            singleInstance = new CompanyDAO();
        }
        return singleInstance;
    }

    public Company selectCompanyInfomation(int customerId, String adressType) {
        List<Company> companies = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Adres AS adres, bifi.Klant AS klant WHERE adres.klantid = ? AND klant.klantid = ? AND adres.type = ?");){
            stmt.setInt(1, customerId);
            stmt.setInt(2, customerId);
            stmt.setString(3, adressType);

            companies.add(createInformation(stmt));

        } catch (SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return companies.get(0);
    }

    private Company createInformation(PreparedStatement stmt) {
        AdressMaker adressMaker;
        Company company = null;

        try(ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                adressMaker = new AdressMaker(rs.getString("straat"), rs.getString("plaats"), rs.getString("huisnummer"), rs.getString("postcode"));

                String street = adressMaker.getStreet();
                String houseNumber = adressMaker.getHouseNumber();
                String postalcode = adressMaker.getPostalcode();
                String city = adressMaker.getCity();
                String companyName = rs.getString("bedrijfsnaam");
                String vatNumber = rs.getString("vat");
                String iban;
                if (rs.getString("bankrek") != null) {
                    iban = rs.getString("bankrek");
                }
                else {
                    iban = rs.getString("giro");
                }
                String bic = rs.getString("bik");

                Adress adress = new Adress(street, postalcode, city, houseNumber);
                Bank bank = new Bank(iban, bic);
                company = new Company(companyName, vatNumber, adress, bank);
            }
        }catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return company;
    }
}
