package clireader;

import logic.enums.Month;
import logic.ief.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// compile class: javac ReadCLI.java
// run class: java clireader.ReadCLI [argument]
public class ReadCLI {
    public static void main(String[] args) throws IOException {
        try {
            if (args.length == 0) {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(System.in));
                System.out.println("For what month do you need invoices? ");
                String month = reader.readLine();
                args = new String[]{month};
            }

            tryConvertAndPrint(args[0]);
        } catch (NullPointerException e) {
            System.out.println("Null is not a month");
        }
    }

    private static void tryConvertAndPrint(String arg) throws IOException {
        try {
            Convert convert = new Convert();
            int monthNumber = Integer.parseInt(arg);
            System.out.println("Finding data for month " + monthNumber);
            if (!returnMonth(monthNumber).equals("error")) {
                convert.combineInfoToIEF(monthNumber);
                System.out.println("File created: invoice" + monthNumber + ".txt");
            }
        } catch (NumberFormatException e) {
            System.out.println("Argument " + arg + " is not an integer");

        }
    }

    static String returnMonth(int monthNumber) {
        if (monthNumber > 0 && monthNumber < 13) {
            // monthNumber minus 1 because the enum index starts at 0
            String month = Month.values()[monthNumber - 1].value;
            System.out.println(month);
            return month;
        } else {
            System.out.println("Error, " + monthNumber + " is not a valid month");
            return "error";
        }
    }
}
