package CLIReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadCLITest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Disabled
    @Test
    void testAMonth() {
        String[] args = {"5"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 5\r\n" +
                "Mei\r\n", outContent.toString());
    }

    @Disabled
    @Test
    void testMultipleArguments() {
        String[] args = {"5", "6"};
        ReadCLI.main(args);
        assertEquals("Zero or multple arguments supplied, must be one\r\n", outContent.toString());
    }

    @Disabled
    @Test
    void testInvalidMonth() {
        String[] args = {"23"};
        ReadCLI.main(args);
        assertEquals("Finding data for month 23\r\n" +
                "Error, 23 is not a valid month\r\n", outContent.toString());
    }

    @Disabled
    @Test
    void testNotAnInt() {
        String[] args = {"string"};
        ReadCLI.main(args);
        assertEquals("Argument string must be an integer.\r\n", outContent.toString());
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}