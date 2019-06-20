package load.sqldatabase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyDAOTest {
    private CompanyDAO companyDao;

    @BeforeEach
    public void BeforeEach() {
        companyDao = new CompanyDAO();
    }

    @Test
    public void TestCompanyName() {
        assertEquals("Helma", companyDao.selectCompanyInfomation(1).get(0).getCompanyName());
    }
}
