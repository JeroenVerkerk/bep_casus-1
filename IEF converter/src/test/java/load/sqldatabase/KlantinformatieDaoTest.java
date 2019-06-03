package load.sqldatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KlantinformatieDaoTest {
    private KlantinformatieDao dao;

    @BeforeEach
    public void beforeEach() { dao = new KlantinformatieDao();}

    @Test
    public void testGetBedrijfsinformatie() {
        assertEquals(1, dao.selectKlantinformatie(1).size());
    }
}
