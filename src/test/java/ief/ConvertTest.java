package ief;

import enums.NegativeTokens;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import sql.models.Adress;
import sql.models.Bank;

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
    void getCompanyInfo() throws IOException {
        assertEquals("BEricson                                                     Ajax                                                        5         1901CDRotterdam           BEricson                                                     Ajax                                                        5         1901CDRotterdam           NL00123123120                                                                DABAIE2D  \n", convert.getCompanyInfo(2));
    }

    @Disabled
    @Test
    void getCustomerInfo() throws IOException {
        assertEquals("F", convert.getCustomerInfo(1));
    }

    @Disabled
    @Test
    void getInvoiceInfo() throws IOException {
        assertEquals("F", convert.getInvoiceInfo(4));
    }

    @Disabled
    @Test
    void getInvoiceLines() {
        //Mock list of invoicelines?
//        assertEquals("R", convert.getInvoiceLinesFromInvoice());
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
        assertEquals(NegativeTokens.WHITESPACE.value, zero);
        assertEquals(NegativeTokens.EXCLAMATIONMARK.value, one);
        assertEquals(NegativeTokens.DOUBLEQUOTE.value, two);
        assertEquals(NegativeTokens.NUMBERSIGN.value, three);
        assertEquals(NegativeTokens.DOLLAR.value, four);
        assertEquals(NegativeTokens.PERCENT.value, five);
        assertEquals(NegativeTokens.AMPERSAND.value, six);
        assertEquals(NegativeTokens.DOUBLEBACKSLASH.value, seven);
        assertEquals(NegativeTokens.LEFTPARENTHESIS.value, eight);
        assertEquals(NegativeTokens.RIGHTPARENTHESIS.value, nine);
        assertEquals('a', def);
    }

    @Test
    void testIEFFileCreated() throws IOException {
        convert.combineInfoToIEF(4);
        File file = new File("./invoice4.txt");
        assertTrue(file.exists());

    }
    @Test
    void testGetAdress(){
        StringBuilder stringBuilder = new StringBuilder();
        Adress adress = new Adress("a", "a", "a", "a");
        String test = convert.getAdress(stringBuilder,adress);
        assertEquals("a                                                           a         a     a                   ", test);

    }

    @Test
    void testGetBankInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        Bank bank = new Bank("a", "a");
        String test = convert.getBankInfo(stringBuilder, "1234", bank);
        assertEquals("1234a                                                               a         \n", test);
    }
    @Test
    void doubleConverter() {
        String test = convert.doubleConverter(5,10.04);

        assertEquals("0001004", test);
    }

    @AfterEach
    void tearDown() {
        convert = null;
        assertNull(convert);
    }
}