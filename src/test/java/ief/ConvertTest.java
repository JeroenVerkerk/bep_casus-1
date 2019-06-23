package ief;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {
    private Convert convert;

    @BeforeEach
    void setUp() {
        convert = new Convert();
        assertNotNull(convert);
    }
    @Disabled
    @Test
    void getCompanyInfo() {
        assertEquals("B", convert.getCompanyInfo());
    }

    @Test
    void getCustomerInfo() {
        assertEquals("K", convert.getCustomerInfo());
    }

    @Disabled
    @Test
    void getInvoiceInfo() {
        assertEquals("F", convert.getInvoiceInfo(5));
    }

    @Test
    void getInvoiceLines() {
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

    @AfterEach
    void tearDown() {
        convert = null;
        assertNull(convert);
    }

}