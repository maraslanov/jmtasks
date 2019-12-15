import ConnectionManager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import task16.Task1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class testWithMocks {
    private static final Logger LOGGER = LogManager.getLogger(Task1.class);

    private static ConnectionManager connectionManager;
    private static Task1 app;
    private static Connection connection;
    @Mock
    PreparedStatement preparedStatement;

    @BeforeAll
    static void tearsUp() {
        LOGGER.trace("Task1 tests started");
        app = new Task1();
        connectionManager = spy(ConnectionManager.getInstance());
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        connection = mock(Connection.class);
    }

    @AfterAll
    static void tearsDown() throws SQLException {
        LOGGER.trace("Task1 tests finished");
        connectionManager = null;
        if (connection != null) connection.close();
    }

    @Test
    void checkSelect() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        //подменяем вызов connection.prepareStatement
        doReturn(preparedStatement).when(connection).prepareStatement(any());
        assertDoesNotThrow(() -> app.selectUser(connectionManager, 1l, "agent1"));
        verify(connection, times(1)).prepareStatement(any());
        verify(preparedStatement, times(1)).executeQuery();
        verify(preparedStatement, times(0)).execute();
    }
}
