package CLIReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadCLITest {
    private ReadCLI readCLI;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        readCLI = new ReadCLI();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void testReturnMonth(){
        readCLI.returnMonth(2);
        assertEquals("Februari\r\n", outContent.toString());
    }
    @Test
    void testMonth1() {
        String[] args = {"1"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 1\r\n" +
                "Januari\r\n", outContent.toString());
    }
    @Test
    void testMonth2() {
        String[] args = {"2"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 2\r\n" +
                "Februari\r\n", outContent.toString());
    }
    @Test
    void testMonth3() {
        String[] args = {"3"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 3\r\n" +
                "Maart\r\n", outContent.toString());
    }
    @Test
    void testMonth4() {
        String[] args = {"4"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 4\r\n" +
                "April\r\n", outContent.toString());
    }
    @Test
    void testMonth5() {
        String[] args = {"5"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 5\r\n" +
                "Mei\r\n", outContent.toString());
    }
    @Test
    void testMonth6() {
        String[] args = {"6"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 6\r\n" +
                "Juni\r\n", outContent.toString());
    }
    @Test
    void testMonth7() {
        String[] args = {"7"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 7\r\n" +
                "Juli\r\n", outContent.toString());
    }
    @Test
    void testMonth8() {
        String[] args = {"8"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 8\r\n" +
                "Augustus\r\n", outContent.toString());
    }
    @Test
    void testMonth9() {
        String[] args = {"9"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 9\r\n" +
                "September\r\n", outContent.toString());
    }
    @Test
    void testMonth10() {
        String[] args = {"10"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 10\r\n" +
                "Oktober\r\n", outContent.toString());
    }
    @Test
    void testMonth11() {
        String[] args = {"11"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 11\r\n" +
                "November\r\n", outContent.toString());
    }
    @Test
    void testMonth12() {
        String[] args = {"12"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 12\r\n" +
                "December\r\n", outContent.toString());
    }

    @Test
    void testMultipleArguments() {
        String[] args = {"5", "6"};
        ReadCLI.main(args);
        assertEquals("Zero or multiple arguments supplied, must be one\r\n", outContent.toString());
    }

    @Test
    void testInvalidMonth() {
        String[] args = {"23"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 23\r\n" +
                "Error, 23 is not a valid month\r\n", outContent.toString());
    }

    @Test
    void testNotAnInt() {
        String[] args = {"string"};
        ReadCLI.main(args);
        assertEquals("Argument string is not an integer\r\n", outContent.toString());
    }

    @Test
    void notZeroArguments(){
        String[] args = {};
        ReadCLI.main(args);
        assertEquals("Zero or multiple arguments supplied, must be one\r\n", outContent.toString());
    }

    @Test
    void notNull(){
        String[] args = {};
        args = null;
        ReadCLI.main(args);
        assertEquals("Null is not a month\r\n", outContent.toString());
    }


    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}