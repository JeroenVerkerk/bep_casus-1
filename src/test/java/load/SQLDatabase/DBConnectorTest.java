package load.SQLDatabase;

import org.junit.jupiter.api.Test;
import sql.connector.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBConnectorTest {
    private DBConnector dbConnector = DBConnector.getInstance();

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
