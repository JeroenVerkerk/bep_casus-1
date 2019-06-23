package sql.dao;

import sql.connector.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    private DBConnector connector = DBConnector.getInstance();

    public Connection getConnection() {
        return connector.getConnection();
    }
}
