package sql.dao;

import sql.connector.DBConnector;

import java.io.IOException;
import java.sql.Connection;

public class BaseDao {
    private DBConnector connector = DBConnector.getInstance();

    public Connection getConnection() throws IOException {
        return connector.getConnection();
    }
}
