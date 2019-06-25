package persistence.people.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.people.dao.CompanyDAO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDAOTest {
    private CompanyDAO companyDao;

    @BeforeEach
    public void BeforeEach() {
        companyDao = CompanyDAO.getInstance();
    }

    @Test
    public void TestCompanyName() throws IOException {
        assertEquals("Helma", companyDao.selectCompanyInfomation(1).getCompanyName());
    }

    @Test
    public void TestCompanyBTWNumber() throws IOException {
        assertEquals("NL001234567B01", companyDao.selectCompanyInfomation(1).getVatNumber());
    }

    @Test
    public void TestCompanyStreet() throws IOException {
        assertEquals("Steenweg                                                    ", companyDao.selectCompanyInfomation(1).getAdress().getStreet());
    }

    @Test
    public void TestCompanyHouseNumber() throws IOException {
        assertEquals("59        ", companyDao.selectCompanyInfomation(1).getAdress().getHouseNumber());
    }

    @Test
    public void TestCompanyCity() throws IOException {
        assertEquals("Utrecht             ", companyDao.selectCompanyInfomation(1).getAdress().getCity());
    }

    @Test
    public void TestCompanyPostalcode() throws IOException {
        assertEquals("3511JN", companyDao.selectCompanyInfomation(1).getAdress().getPostalcode());
    }

    @Test
    public void TestCompanyIBAN() throws IOException {
        assertEquals("NL91ABNA0417164300", companyDao.selectCompanyInfomation(1).getBank().getIban());
    }

    @Test
    public void TestCompanyBIC() throws IOException {
        assertEquals("DABAIE2D", companyDao.selectCompanyInfomation(1).getBank().getBic());
    }

    @Test
    public void TestCompanyWithGiro() throws IOException {
        assertEquals("giro882", companyDao.selectCompanyInfomation(5).getBank().getIban());
    }

    @Test
    public void TestCustomerOldAdressMOATA() throws IOException {
        assertEquals("Rotterdam", companyDao.selectCompanyInfomation(2).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressKDLRA() throws IOException {
        assertEquals("Zevenhuizen", companyDao.selectCompanyInfomation(3).getAdress().getCity());
    }

    @Test
    public void TestCustomerOldAdressNIPJK() throws IOException {
        assertEquals("Den Haag", companyDao.selectCompanyInfomation(5).getAdress().getCity());
    }
}
