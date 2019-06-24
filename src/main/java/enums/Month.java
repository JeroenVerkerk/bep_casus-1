package enums;

public enum Month {
    January("Januari"),
    February("Februari"),
    March("Maart"),
    April("April"),
    May("Mei"),
    June("Juni"),
    July("Juli"),
    August("Augustus"),
    September("September"),
    October("Oktober"),
    November("November"),
    December("December");

    public final String value;

    Month(String Month) {
        this.value = Month;
    }
}
