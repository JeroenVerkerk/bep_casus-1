package persistence.people.dao;

import persistence.people.connector.PostgresConnector;

import java.io.IOException;
import java.sql.Connection;

public class BaseDAO {
    private PostgresConnector connector = PostgresConnector.getInstance();

    public Connection getConnection() throws IOException {
        return connector.getConnection();
    }
}