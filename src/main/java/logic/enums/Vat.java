package logic.enums;

public enum Vat {
    HIGH("Hoog"),
    LOW("Laag"),
    NONE("0");

    public final String value;

    Vat(String vat) {
        this.value = vat;
    }
}