import ConnectionManager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task16.Task1;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class junitTests {
    private static final Logger LOGGER = LogManager.getLogger(Task1.class);
    private static ConnectionManager connectionManager;
    private static Connection connection;
    private static Task1 app;

    @BeforeAll
    static void setUp() {
        LOGGER.trace("Task1 tests started");
        app = new Task1();
        connectionManager = ConnectionManager.getInstance();
        connection = connectionManager.getConnection();
    }

    @AfterAll
    static void tearsDown() throws SQLException {
        LOGGER.trace("Task1 tests finished");
        connectionManager = null;
        if (connection!=null) connection.close();
    }

    @Test
    void checkSelect() {
        assertDoesNotThrow(() -> app.selectUser(connectionManager, 0l, "Neo"));
    }

    @Test
    void checkInsert() throws SQLException {
        assertDoesNotThrow(() -> app.insertRecord(connection));
    }
}
