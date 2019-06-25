package clireader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadCLITest {
    private final OutputStream os = new ByteArrayOutputStream();
    private final PrintStream ps = new PrintStream(os);

    @BeforeEach
    void setUpStreams() {
        System.setOut(ps);
    }


    @Test
    void testReturnMonth() {
        String test = ReadCLI.returnMonth(2);
        assertEquals("FEBRUARY", test);
    }


    @Test
    void testSingleArgument() throws IOException {
        String[] args = {"5", ""};
        ReadCLI.main(args);
        assertEquals("Finding data for month 5" + System.getProperty("line.separator") +
                "MAY" + System.getProperty("line.separator") +
                "File created: invoice5.txt" + System.getProperty("line.separator"), os.toString());
    }

    @Test
    void testMultipleArguments() throws IOException {
        String[] args = {"5", "6"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 5" + System.getProperty("line.separator") +
                "MAY" + System.getProperty("line.separator") +
                "File created: invoice5.txt" + System.getProperty("line.separator"), os.toString());
    }


    @Test
    void testInvalidMonth() throws IOException {
        String[] args = {"23"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 23" + System.getProperty("line.separator") +
                "Error, 23 is not a valid month" + System.getProperty("line.separator"), os.toString());
    }


    @Test
    void testNotAnInt() throws IOException {
        String[] args = {"string"};
        ReadCLI.main(args);
        assertEquals("Argument string is not an integer" + System.getProperty("line.separator"), os.toString());
    }


    @Test
    void notNull() throws IOException {
        ReadCLI.main(null);

        assertEquals("Null is not a month" + System.getProperty("line.separator"), os.toString());
    }
}