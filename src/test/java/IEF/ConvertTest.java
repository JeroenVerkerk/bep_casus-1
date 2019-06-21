package IEF;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void getCustomerInfo() {
    }

    @Test
    void getInvoiceInfo() {
    }

    @Test
    void getInvoiceLines() {
    }


    @Test
    void testPadd() {
        String paddedString = convert.paddOrSnip(6, "vier");
        assertEquals("vier  ", paddedString);
    }

    @Test
    void testSnip(){
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
    }

    @AfterEach
    void tearDown() {
        convert = null;
        assertNull(convert);
    }


}