package CLIReader;

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
        String month;
        switch (monthNumber) {
            case 1:
                month = "Januari";
                System.out.println(month);
                break;
            case 2:
                month = "Februari";
                System.out.println(month);
                break;
            case 3:
                month = "Maart";
                System.out.println(month);
                break;
            case 4:
                month = "April";
                System.out.println(month);
                break;
            case 5:
                month = "Mei";
                System.out.println(month);
                break;
            case 6:
                month = "Juni";
                System.out.println(month);
                break;
            case 7:
                month = "Juli";
                System.out.println(month);
                break;
            case 8:
                month = "Augustus";
                System.out.println(month);
                break;
            case 9:
                month = "September";
                System.out.println(month);
                break;
            case 10:
                month = "Oktober";
                System.out.println(month);
                break;
            case 11:
                month = "November";
                System.out.println(month);
                break;
            case 12:
                month = "December";
                System.out.println(month);
                break;
            default:
                System.out.println("Error, " + monthNumber + " is not a valid month");

        }
    }
}
