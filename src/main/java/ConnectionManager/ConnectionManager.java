package ConnectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ConnectionManager.ConnectionConsts.*;

public class ConnectionManager implements IConnectionManager {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionManager.class);
    private static ConnectionManager connectionManager;

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(
                    urlConnection + host + ":" + port + "/" + database,
                    user,
                    password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("getConnection method has error:", e);
        }
        return connection;
    }
}
