package jee.task22.dao;

import jee.task22.pojo.Mobile;

import java.util.Collection;

public interface IMobileDao {
    boolean addMobile(Mobile mobile);

    Mobile getMobileById(Integer id);

    boolean updateMobileById(Mobile mobile);

    boolean deleteMobileById(Integer id);

    void createTable();

    Collection<Mobile> getAllMobile();
}
