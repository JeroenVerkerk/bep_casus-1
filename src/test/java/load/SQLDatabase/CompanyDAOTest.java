package load.SQLDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import sql.DAO.CompanyDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDAOTest {
    private CompanyDAO companyDao;

    @BeforeEach
    public void BeforeEach() {
        companyDao = new CompanyDAO();
    }

    @Test
    public void TestCompanyName() {
        assertEquals("Helma", companyDao.selectCompanyInfomation(1, "F").get(0).getCompanyName());
    }

    @Test
    public void TestCompanyBTWNumber() {
        assertEquals("NL001234567B01", companyDao.selectCompanyInfomation(1, "F").get(0).getBtwNumber());
    }

    @Test
    public void TestCompanyStreet() {
        assertEquals("Steenweg                                                    ", companyDao.selectCompanyInfomation(1, "F").get(0).getAdress().getStreet());
    }

    @Test
    public void TestCompanyHouseNumber() {
        assertEquals("59        ", companyDao.selectCompanyInfomation(1, "F").get(0).getAdress().getHouseNumber());
    }

    @Test
    public void TestCompanyCity() {
        assertEquals("Utrecht             ", companyDao.selectCompanyInfomation(1,"F").get(0).getAdress().getCity());
    }

    @Test
    public void TestCompanyPostalcode() {
        assertEquals("3511JN", companyDao.selectCompanyInfomation(1, "F").get(0).getAdress().getPostalcode());
    }

    @Test
    public void TestCompanyIBAN() {
        assertEquals("NL91ABNA0417164300", companyDao.selectCompanyInfomation(1, "F").get(0).getBank().getIBAN());
    }

    @Test
    public void TestCompanyBIC() {
        assertEquals("DABAIE2D", companyDao.selectCompanyInfomation(1, "F").get(0).getBank().getBIC());
    }

    @Test
    public void TestCompanyWithGiro() {
        assertEquals("giro882", companyDao.selectCompanyInfomation(5, "F").get(0).getBank().getIBAN());
    }

    @Test
    public void TestCustomerOldAdressMOATA() {
        assertEquals("Rotterdam", companyDao.selectCompanyInfomation(2, "F").get(0).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressKDLRA() {
        assertEquals("Zevenhuizen", companyDao.selectCompanyInfomation(3, "F").get(0).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressNIPJK() {
        assertEquals("Den Haag", companyDao.selectCompanyInfomation(2, "A").get(0).getAdress().getCity());
    }
}
