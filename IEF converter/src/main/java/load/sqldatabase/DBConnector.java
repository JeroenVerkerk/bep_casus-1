package load.sqldatabase;

import java.sql.*;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DBConnector {
    private Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);

    public DBConnector() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE TRYING TO FIND CLASS XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }
    }

    public Connection getConnection() {
        String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/jxcnbknw";

        Properties props = new Properties();
        props.setProperty("user", "jxcnbknw");
        props.setProperty("password", "o8Ndi3bK7w3AHQFNc8qOOUlR3lOPuwlX");
        try {
           conn  = DriverManager.getConnection(url, props);
        }catch (SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE CONNECTING TO DATABASe XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return conn;

    }
}
