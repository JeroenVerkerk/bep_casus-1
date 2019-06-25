package persistence.people.connector;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostgresConnectorTest {
    private PostgresConnector postgresConnector = PostgresConnector.getInstance();

    @Test
    public void testConnection() throws SQLException, IOException {
        Connection connection = postgresConnector.getConnection();
        boolean result = false;

        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM bifi.Klant");

        if (rs != null) {
            result = true;
        }

        assertEquals(true, result);
    }
}
