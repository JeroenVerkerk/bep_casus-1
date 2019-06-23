package sql.dao;

import ADDRLOOKUPER.LOOKUP_AdDDR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class AdressMaker {
    private static final Logger logger = LoggerFactory.getLogger(AdressMaker.class);
    private String street;
    private String city;
    private String houseNumber;
    private String postalcode;

    public AdressMaker(String street, String city, String houseNumber, String postalcode) {
        this.street = street;
        this.postalcode = postalcode;
        this.houseNumber = houseNumber;
        this.city = city;

        checkIfOldAddress(street);
    }

    public void checkIfOldAddress(String street) {
        if (street.contains("-MOATA") || street.contains("-NIPJK") || street.contains("-KDLRA")) {
            findOldAddress(street);
        }
    }


    public void findOldAddress(String oldAdress) {
        Map<String, String> lookedUpAdress = null;
        try {
            oldAdress = oldAdress.replace("-", "");
            oldAdress = oldAdress.replace(" ", "");
            LOOKUP_AdDDR.scanStart();
            lookedUpAdress = LOOKUP_AdDDR.scanForward(oldAdress);
            LOOKUP_AdDDR.scanStop();

            this.street = lookedUpAdress.get("STRAAT");
            this.city = lookedUpAdress.get("plaats");
            this.houseNumber = lookedUpAdress.get("HUISNUMMER");
            this.postalcode = lookedUpAdress.get("POSTCODE");
        }catch (Exception ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE GETTING OLD ADRESS XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalcode() {
        return postalcode;
    }
}
