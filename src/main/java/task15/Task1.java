package task15;

import java.sql.*;

import static task15.Consts.*;

public class Task1 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(driver);
        String conn = urlConnection + host + ":" + port + "/" + database;
        try (Connection connection = DriverManager.getConnection(conn, user, password)) {
            //Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
            //Используя параметризированный запрос:
            String INSERT_USER = "INSERT INTO \"USER\" " +
                    "(name, birthday, login_id, city, email) VALUES (?,?,?,?,?)";
            connection.setAutoCommit(false);
            insertRecord(connection, INSERT_USER);
            //PSQLSavepoint savepoint = new PSQLSavepoint("A");
            Savepoint savepoint = connection.setSavepoint("A");
            //Используя batch процесс
            try {
                insertBatch(connection, INSERT_USER);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                connection.rollback(savepoint);
                System.out.println("произведен откат к точке: " + savepoint.getSavepointName());
            }
            //cделать параметризированную выборку по login_ID и name одновременно
            connection.commit();
            connection.setAutoCommit(true);
            String SELECT_USER = "SELECT * from \"USER\" " +
                    "WHERE login_id = ? and name = ?";
            selectUser(connection, SELECT_USER);
            //connection.commit(); //autocommit enabled
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectUser(Connection connection, String SELECT_USER) throws SQLException {
        try (PreparedStatement selectStmt = connection.prepareStatement(SELECT_USER)) {
            selectStmt.setLong(1, 0l);
            selectStmt.setString(2, "Neo");
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                User agent = new User();
                agent.setName(resultSet.getString("name"));
                agent.setBirthday(resultSet.getDate("birthday"));
                agent.setLoginId(resultSet.getLong("login_id"));
                agent.setCity(resultSet.getString("city"));
                agent.setEmail(resultSet.getString("email"));
                agent.setEmail(resultSet.getString("description"));
                System.out.println(agent.toString());
            }
        }
    }

    private static void insertBatch(Connection connection, String INSERT_USER) throws SQLException {
        try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER)) {
            int batchSize = 10;
            for (int i = 1; i < batchSize; i++) {
                insertStmt.setString(1, "agent" + i);
                insertStmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                insertStmt.setLong(3, new Long(i));
                insertStmt.setString(4, "Matrix");
                insertStmt.setString(5, null);
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        }
    }

    private static void insertRecord(Connection connection, String INSERT_USER) throws SQLException {
        Savepoint save = connection.setSavepoint("try");
        try (PreparedStatement insertStmt = connection.prepareStatement(INSERT_USER)) {
            insertStmt.setString(1, "Neo");
            insertStmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            insertStmt.setLong(3, 0l);
            insertStmt.setString(4, "Xeon");
            insertStmt.setString(5, "110110110011101010001");
            insertStmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            connection.rollback(save);
        }
    }
}
