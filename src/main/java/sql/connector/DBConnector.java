package sql.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    private Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);
    private static DBConnector sinlgeInstance;

    public static DBConnector getInstance() {
        if (sinlgeInstance == null)
        {
            sinlgeInstance = new DBConnector();
        }
        return sinlgeInstance;
    }

    public Connection getConnection() throws IOException {
        String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/jxcnbknw";
        String dbConfigPath = "app.properties";
        Properties props = new Properties();
        props.load(new FileInputStream(System.getProperty("user.dir") + "/app.properties"));
        try {
           conn  = DriverManager.getConnection(url, props);
        }catch (SQLException ex) {
            logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE CONNECTING TO DATABASe XXXXXXXXXXXXXXXXXXXXXXXXXX");
            logger.info(ex.getMessage(), ex);
        }

        return conn;

    }
}
