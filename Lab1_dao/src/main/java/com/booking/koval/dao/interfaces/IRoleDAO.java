package com.booking.koval.dao.interfaces;

import com.booking.koval.entities.Role;

import java.util.List;

public interface IRoleDAO {
    List<Role> findAll();
    Role findById(long role_id);
    long create(Role role);
    void update(long role_id, Role role);
    void delete(long role_id);
}
