package load.sqldatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BedrijfsinformatieDaoTest {
    private BedrijfsinformatieDao dao;

    @BeforeEach
    public void beforeEach() { dao = new BedrijfsinformatieDao();}

    @Test
    public void testGetBedrijfsinformatie() {
        assertEquals(1, dao.selectBedfrijfsinformatie(1).size());
    }
}
