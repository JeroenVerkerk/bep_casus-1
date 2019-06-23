package sql.dao;

import sql.connector.DBConnector;

import java.sql.Connection;

public class BaseDao {
    private DBConnector connector = DBConnector.getInstance();

    public Connection getConnection() {
        return connector.getConnection();
    }
}
