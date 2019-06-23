package load.sqldatabase;

import enums.Enums;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.dao.CustomerDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomerDAOTest {
    private CustomerDAO customerDAO;
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    @BeforeEach
    public void BeforeEach() {
        customerDAO = new CustomerDAO();
    }

    @Test
    public void TestCustomerName() {
        assertEquals("Jan", customerDAO.selectCustomerInformation(1, "F").getName().getFirstName());
    }

    @Test
    public void TestCustomerMiddleName() {
        assertEquals(null, customerDAO.selectCustomerInformation(1, "F").getName().getMiddleName());
    }

    @Test
    public void TestCustomerLastName() {
        assertEquals("Janssen", customerDAO.selectCustomerInformation(1, "F").getName().getLastName());
    }

    @Test
    public void TestCustomerSalutation() {
        assertEquals(Enums.Salutation.DHR, customerDAO.selectCustomerInformation(2, "F").getName().getSalutation());
    }

    @Test
    public void TestCustomerIsCompany() {
        assertEquals("Helma", customerDAO.selectCustomerInformation(1, "F").getCompany().getCompanyName());
    }

    @Test
    public void TestCustomerIsNotACompany() {
        assertEquals(null, customerDAO.selectCustomerInformation(4, "F").getCompany());
    }

    @Test
    public void TestCustomerWithGiro() {
        assertEquals("giro882", customerDAO.selectCustomerInformation(5, "F").getBank().getIban());
    }

    @Test
    public void TestCustomerOldAdressMOATA() {
        assertEquals("Rotterdam", customerDAO.selectCustomerInformation(2, "F").getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressKDLRA() {
        assertEquals("Zevenhuizen", customerDAO.selectCustomerInformation(3, "F").getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressNIPJK() {
        assertEquals("Den Haag", customerDAO.selectCustomerInformation(2, "A").getAdress().getCity());
    }
}
