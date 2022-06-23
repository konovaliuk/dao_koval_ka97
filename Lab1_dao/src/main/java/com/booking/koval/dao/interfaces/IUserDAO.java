package com.booking.koval.dao.interfaces;

import com.booking.koval.entities.User;

import java.util.List;

public interface IUserDAO {
    List<User> findAll();
    User findById(long user_id);
    long create(User user);
    void update(long user_id, User user);
    void delete(long user_id);
}
