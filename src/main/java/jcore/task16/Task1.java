package jcore.task16;

import ConnectionManager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static jcore.task16.Consts.INSERT_USER;
import static jcore.task16.Consts.SELECT_USER;

public class Task1 {
    private static final Logger LOGGER = LogManager.getLogger(Task1.class);

    public static void main(String[] args) throws ClassNotFoundException {
        ConnectionManager manager = ConnectionManager.getInstance();
        try (Connection connection = manager.getConnection()) {
            //Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
            //Используя параметризированный запрос:

            insertRecord(connection);

            Savepoint savepoint = connection.setSavepoint("A");

            //Используя batch процесс
            try {
                LOGGER.info("выполняется batch для insert в таблицу USERS");
                insertBatch(connection);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                connection.rollback(savepoint);
                LOGGER.error("произведен откат к точке: " + savepoint.getSavepointName());
            }

            //cделать параметризированную выборку по login_ID и name одновременно
            connection.commit();
            connection.setAutoCommit(true);

            selectUser(manager, 0l, "Neo");
            //connection.commit(); //autocommit enabled
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void selectUser(ConnectionManager manager, Long id, String name) throws SQLException {
        LOGGER.info("производится чтение из таблицы USER:");
        try (Connection connection = manager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(SELECT_USER)) {
            selectStmt.setLong(1, id);
            selectStmt.setString(2, name);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet != null)
                while (resultSet.next()) {
                    User agent = new User();
                    agent.setName(resultSet.getString("name"));
                    agent.setBirthday(resultSet.getDate("birthday"));
                    agent.setLoginId(resultSet.getLong("login_id"));
                    agent.setCity(resultSet.getString("city"));
                    agent.setEmail(resultSet.getString("email"));
                    agent.setEmail(resultSet.getString("description"));
                    LOGGER.info(agent.toString());
                }
        }
    }

    private static void insertBatch(Connection connection) throws SQLException {
        try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER)) {
            int batchSize = 10;
            for (int i = 1; i < batchSize; i++) {
                insertStmt.setString(1, "agent" + i);
                insertStmt.setDate(2, Date.valueOf(java.time.LocalDate.now()));
                insertStmt.setLong(3, new Long(i));
                insertStmt.setString(4, "Matrix");
                insertStmt.setString(5, null);
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        }
    }

    public static void insertRecord(Connection connection) throws SQLException {
        LOGGER.info("производится запись в таблицу USER");
        connection.setAutoCommit(false);
        Savepoint save = connection.setSavepoint("try");
        try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER)) {
            insertStmt.setString(1, "Neo");
            insertStmt.setDate(2, Date.valueOf(java.time.LocalDate.now()));
            insertStmt.setLong(3, 0l);
            insertStmt.setString(4, "Xeon");
            insertStmt.setString(5, "110110110011101010001");
            insertStmt.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            connection.rollback(save);
        }
    }
}
