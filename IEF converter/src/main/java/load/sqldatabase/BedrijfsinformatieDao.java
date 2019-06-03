package load.sqldatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class BedrijfsinformatieDao {
    private DBConnector connector;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(BedrijfsinformatieDao.class);

    public BedrijfsinformatieDao() {
        connector = new DBConnector();
    }

    public List<Bedrijfsinformatie> selectBedfrijfsinformatie(int klantid) {
        connection = connector.getConnection();
        List<Bedrijfsinformatie> bedrijfsinformaties = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Adres AS adres, bifi.Klant AS klant WHERE adres.klantid = ? AND klant.klantid = ? AND adres.type = 'F'");
            stmt.setInt(1, klantid);
            stmt.setInt(2, klantid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String straat = rs .getString("straat");
                String huisnummer = rs.getString("huisnummer");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String bedrijfsnaam = rs.getString("bedrijfsnaam");
                String btwNummer = rs.getString("vat");
                String IBAN;
                if (rs.getString("bankrek") != null) {
                    IBAN = rs.getString("bankrek");
                }
                else {
                    IBAN = rs.getString("giro");
                }
                String BIC = rs.getString("bik");

                Bedrijfsinformatie bedrijfsinformatie = new Bedrijfsinformatie("b", bedrijfsnaam, straat, huisnummer, postcode, plaats, btwNummer, IBAN, BIC);

                bedrijfsinformaties.add(bedrijfsinformatie);
            }

            rs.close();
            connection.close();

        } catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return bedrijfsinformaties;
    }
}
