package enums;

public enum Month {
    JANUARY("Januari"),
    FEBRUARY("Februari"),
    MARCH("Maart"),
    APRIL("April"),
    MAY("Mei"),
    JUNE("Juni"),
    JULY("Juli"),
    AUGUST("Augustus"),
    SEPTEMBER("September"),
    OCTOBER("Oktober"),
    NOVEMBER("November"),
    DECEMBER("December");

    public final String value;

    Month(String month) {
        this.value = month;
    }
}
