package load.sqldatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBConnectionTest {
    private DBConnector dbConnector;

    @BeforeEach
    public void beforeEach() {dbConnector = new DBConnector();}

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dbConnector.getConnection();
        boolean result = false;

        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM bifi.Klant");

        if (rs != null) {
            result = true;
        }

        assertEquals(true, result);
    }
}
