package jee.task22.dao;

import ConnectionManager.ConnectionManager;
import jee.task22.pojo.UserPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jee.task22.dao.UserDaoJDBC.*;

public class UserFacade {

    public static UserPojo getUserByParam(String login, String password, ConnectionManager connectionManager) throws Exception {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserPojo(
                            resultSet.getInt(1),
                            resultSet.getString(9),
                            resultSet.getString(8)
                    );
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return null;
    }

    public static Collection<UserPojo> getAllUsers(ConnectionManager connectionManager) throws Exception {
        List<UserPojo> lstmb = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                lstmb.add(new UserPojo(
                        resultSet.getInt(1),
                        resultSet.getString(9),
                        resultSet.getString(8)));
            }
            return lstmb;
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public static boolean addUser(UserPojo mobile, ConnectionManager connectionManager) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USER)) {
            preparedStatement.setString(1, mobile.getLogin());
            preparedStatement.setString(2, mobile.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
