package jee.task22.dao;

import jee.task22.pojo.UserPojo;

import java.util.Collection;

public interface IUserDao {
    boolean addUser(UserPojo mobile);

    UserPojo getUserByParam(String login, String password);

    boolean updateUserById(UserPojo mobile);

    Collection<UserPojo> getAllUsers();
}
