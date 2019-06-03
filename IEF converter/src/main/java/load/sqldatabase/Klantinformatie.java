package load.sqldatabase;

public class Klantinformatie {
    private String type;
    private String bedrijfsnaam;
    private String aanhef;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String straat;
    private String postcode;
    private String plaats;
    private String btwNummer;
    private String IBAN;
    private String BIC;

    public Klantinformatie(String type, String bedrijfsnaam, String aanhef, String voornaam, String achternaam, String tussenvoegsel, String straat, String postcode, String plaats, String btwNummer, String IBAN, String BIC) {
        this.type = type;
        this.bedrijfsnaam = bedrijfsnaam;
        this.aanhef = aanhef;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.straat = straat;
        this.postcode = postcode;
        this.plaats = plaats;
        this.btwNummer = btwNummer;
        this.IBAN = IBAN;
        this.BIC = BIC;
    }

    public String getType() {
        return type;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public String getAanhef() {
        return aanhef;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getStraat() {
        return straat;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getBIC() {
        return BIC;
    }
}
