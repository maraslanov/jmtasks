package jee.task22.dao;

import ConnectionManager.ConnectionManager;
import jee.task22.pojo.UserPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@EJB
public class UserDaoJDBC implements IUserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoJDBC.class);
    public static final String INSERT_INTO_USER = "INSERT INTO USER (login, password) values (?, ?)";
    public static final String SELECT_FROM_USER = "SELECT * FROM public.\"USER\" WHERE login=? and password=?";
    public static final String SELECT_ALL_FROM_USER = "SELECT * FROM USER";
    public static final String UPDATE_USER = "UPDATE public.\"USER\" SET password=? WHERE id=?";

    private ConnectionManager connectionManager;

    @Inject
    public UserDaoJDBC(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public boolean addUser(UserPojo mobile) {
        try {
            return UserFacade.addUser(mobile, connectionManager);
        } catch (Exception e) {
            LOGGER.error("Some thing wrong in addUser method", e);
            return false;
        }
    }

    @Override
    public UserPojo getUserByParam(String login, String password) {
        try {
            return UserFacade.getUserByParam(login, password, connectionManager);
        } catch (Exception e) {
            LOGGER.error("Some thing wrong in getUserById method", e);
        }
        return null;
    }

    @Override
    public boolean updateUserById(UserPojo mobile) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, mobile.getPassword());
            preparedStatement.setInt(2, mobile.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in updateUserById method", e);
        }
        return false;
    }

    @Override
    public Collection<UserPojo> getAllUsers() {
        try {
            return UserFacade.getAllUsers(connectionManager);
        } catch (Exception e) {
            LOGGER.error("Some thing wrong in getAllUsers method", e);
        }
        return new ArrayList<>();
    }
}
