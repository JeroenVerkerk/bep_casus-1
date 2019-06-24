package ief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {
    private Convert convert;

    @BeforeEach
    void setUp() {
        convert = new Convert();
        assertNotNull(convert);
    }

    @Test
    void getCompanyInfo() {
        assertEquals("BEricson                                                     Ajax                                                        5         1901CDRotterdam           NL00123123120                                                                DABAIE2D  \n", convert.getCompanyInfo(2, "F"));
    }

    @Disabled
    @Test
    void getCustomerInfo() {
        assertEquals("F", convert.getCustomerInfo(1, "F"));
    }

    @Disabled
    @Test
    void getInvoiceInfo() {
        assertEquals("F", convert.getInvoiceInfo(4));
    }

    @Disabled
    @Test
    void getInvoiceLines() {
        //Mock list of invoicelines?
//        assertEquals("R", convert.getInvoiceLines());
    }


    @Test
    void testSplitProductDescription() {
//        assertEquals("T", convert.splitProductDescription());
    }

    @Test
    void testNegativeLengthConversion() {
        String negString = convert.paddOrSnip(-2, "String");
        assertEquals("String", negString);
    }

    @Test
    void testNoSnipNoPadd() {
        String correctString = convert.paddOrSnip(4, "vier");
        assertEquals("vier", correctString);
    }

    @Test
    void testPadd() {
        String paddedString = convert.paddOrSnip(6, "vier");
        assertEquals("vier  ", paddedString);
    }

    @Test
    void testSnip() {
        String snipString = convert.paddOrSnip(2, "vier");
        assertEquals("vi", snipString);

    }

    @Test
    void testConverter() {
        char zero = convert.negativeNumberConverter(0);
        char one = convert.negativeNumberConverter(1);
        char two = convert.negativeNumberConverter(2);
        char three = convert.negativeNumberConverter(3);
        char four = convert.negativeNumberConverter(4);
        char five = convert.negativeNumberConverter(5);
        char six = convert.negativeNumberConverter(6);
        char seven = convert.negativeNumberConverter(7);
        char eight = convert.negativeNumberConverter(8);
        char nine = convert.negativeNumberConverter(9);
        char def = convert.negativeNumberConverter('a');
        assertEquals(' ', zero);
        assertEquals('!', one);
        assertEquals('"', two);
        assertEquals('#', three);
        assertEquals('$', four);
        assertEquals('%', five);
        assertEquals('&', six);
        assertEquals('\\', seven);
        assertEquals('(', eight);
        assertEquals(')', nine);
        assertEquals('a', def);
    }

    @Test
    void testIEFFileCreated() throws IOException {
        convert.combineInfoToIEF(5);
        File file = new File("./invoice5.txt");
        assertTrue(file.exists());

    }

    @AfterEach
    void tearDown() {
        convert = null;
        assertNull(convert);
    }
}