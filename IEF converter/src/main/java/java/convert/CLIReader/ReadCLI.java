package java.convert.CLIReader;

// compile class: javac ReadCLI.java
// run class: java java.convert.CLIReader.ReadCLI [argument]
public class ReadCLI {
    public static void main(String[] args) {
        int firstArg;
        String month;
        if (args.length == 1) {
            try {
                firstArg = Integer.parseInt(args[0]);
                System.out.println("Finding data for month " + firstArg);
                switch (firstArg) {
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
                        System.out.println("Error, " + firstArg + " is not a valid month");

                }

            } catch (NumberFormatException e) {
                System.out.println("Argument " + args[0] + " must be an integer.");
            }

        } else {
            System.out.println("Zero or multple arguments supplied, must be one");
        }
    }
}
