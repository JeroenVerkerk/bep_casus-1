package load.sqldatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlantinformatieDao {
    private DBConnector connector;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(BedrijfsinformatieDao.class);

    public KlantinformatieDao() {connector = new DBConnector();}

    public List<Klantinformatie> selectKlantinformatie(int klantid) {
        connection = connector.getConnection();
        List<Klantinformatie> klantinformaties = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bifi.Persoon AS persoon, bifi.Klant AS klant, bifi.Adres AS adres WHERE persoon.klantid = ? AND adres.klantid = ? AND klant.klantid = ? AND adres.type = 'F'");
            stmt.setInt(1, klantid);
            stmt.setInt(2, klantid);
            stmt.setInt(3, klantid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String bedrijfsnaam = rs.getString("bedrijfsnaam");
                String aanhef;
                if(rs.getString("geslacht") == "m") {
                    aanhef = "Dhr";
                }
                else {
                    aanhef = "Mvr";
                }
                String voornaam = rs.getString("voornaam");
                String achternaam = rs.getString("achternaam");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String straat = rs.getString("straat");
                String postcode = rs.getString("postcode");
                String plaats = rs.getString("plaats");
                String btwNummer = rs.getString("vat");
                String IBAN;
                if (rs.getString("giro") != null) {
                    IBAN  = rs.getString("giro");
                }
                else {
                    IBAN = rs.getString("bankrek");
                }
                String BIC = rs.getString("bic");

                Klantinformatie klantinformatie = new Klantinformatie("K", bedrijfsnaam, aanhef, voornaam, achternaam, tussenvoegsel, straat, postcode, plaats, btwNummer, IBAN, BIC);

                klantinformaties.add(klantinformatie);
            }

            rs.close();
            connection.close();

        }catch(SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE EXECUTING TO STATEMENT XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return klantinformaties;
    }
}
