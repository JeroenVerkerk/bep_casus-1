package clireader;

import enums.Month;
import ief.Convert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// compile class: javac ReadCLI.java
// run class: java clireader.ReadCLI [argument]
public class ReadCLI {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.println("For what month do you need invoices? ");
            String month = reader.readLine();
            args = new String[]{month};
        }

        try {
            Convert convert = new Convert();
            int monthNumber = Integer.parseInt(args[0]);
            System.out.println("Finding data for month " + monthNumber);
            returnMonth(monthNumber);
            convert.combineInfoToIEF(monthNumber);
            System.out.println("File created: invoice" + monthNumber + ".txt");
        } catch (NullPointerException e) {
            System.out.println("Null is not a month");
        } catch (NumberFormatException e) {
            System.out.println("Argument " + args[0] + " is not an integer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void returnMonth(int monthNumber) {
        if (monthNumber > 0 && monthNumber < 13) {

            // monthNumber minus 1 because the enum index starts at 0
            System.out.println(Month.values()[monthNumber - 1]);
        } else {
            System.out.println("Error, " + monthNumber + " is not a valid month");
        }
    }
}
