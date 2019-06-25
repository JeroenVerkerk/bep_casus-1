package load.sqldatabase;

import enums.Salutation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.dao.CustomerDAO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    @BeforeEach
    public void BeforeEach() {
        customerDAO = CustomerDAO.getInstance();
    }

    @Test
    public void TestCustomerName() throws IOException {
        assertEquals("Jan", customerDAO.selectCustomerInformation(1).getName().getFirstName());
    }

    @Test
    public void TestCustomerMiddleName() throws IOException {
        assertEquals(null, customerDAO.selectCustomerInformation(1).getName().getMiddleName());
    }

    @Test
    public void TestCustomerLastName() throws IOException {
        assertEquals("Janssen", customerDAO.selectCustomerInformation(1).getName().getLastName());
    }

    @Test
    public void TestCustomerSalutation() throws IOException {
        assertEquals(Salutation.DHR, customerDAO.selectCustomerInformation(2).getName().getSalutation());
    }

    @Test
    public void TestCustomerIsCompany() throws IOException {
        assertEquals("Helma", customerDAO.selectCustomerInformation(1).getCompany().getCompanyName());
    }

    @Test
    public void TestCustomerIsNotACompany() throws IOException {
        assertEquals(null, customerDAO.selectCustomerInformation(4).getCompany());
    }

    @Test
    public void TestCustomerWithGiro() throws IOException {
        assertEquals("giro882", customerDAO.selectCustomerInformation(5).getBank().getIban());
    }

    @Test
    public void TestCustomerOldAdressMOATA() throws IOException {
        assertEquals("Rotterdam", customerDAO.selectCustomerInformation(2).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressKDLRA() throws IOException {
        assertEquals("Zevenhuizen", customerDAO.selectCustomerInformation(3).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressNIPJK() throws IOException {
        assertEquals("Den Haag", customerDAO.selectCustomerInformation(5).getAdress().getCity());
    }
}
