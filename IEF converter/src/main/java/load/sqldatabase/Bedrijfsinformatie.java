package load.sqldatabase;

public class Bedrijfsinformatie {
    private String type;
    private String bedrijfsnaam;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String plaats;
    private String btwNummer;
    private String IBAN;
    private String BIC;

    public Bedrijfsinformatie(String type, String bedrijfsnaam, String straat, String huisnummer, String postcode, String plaats, String btwNummer, String IBAN, String BIC) {
        this.type = type;
        this.bedrijfsnaam = bedrijfsnaam;
        this.straat = straat;
        this.huisnummer = huisnummer;
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

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setBIC(String BIC) {
        this.BIC = BIC;
    }
}
