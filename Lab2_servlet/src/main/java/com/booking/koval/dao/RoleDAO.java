package com.booking.koval.dao;

import com.booking.koval.connection.ConnectionPool;
import com.booking.koval.dao.interfaces.IRoleDAO;
import com.booking.koval.entities.Role;
import com.booking.koval.entities.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleDAO implements IRoleDAO {
    private final String COLUMN_ROLE_ID = "role_id";
    private final String COLUMN_ROLE_NAME = "role_name";
    private Connection connection;
    private Statement statement;

    public RoleDAO() {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Role getRole(ResultSet result) throws SQLException {
        long role_id = result.getLong(COLUMN_ROLE_ID);
        String role_name = result.getString(COLUMN_ROLE_NAME);
        return new Role(role_id, role_name);
    }

    public RoleDAO(final Connection connect) throws SQLException {
        this.connection = connect;
        this.statement = connect.createStatement();
    }

    @Override
    public List<Role> findAll() {
        String query = "SELECT * FROM roles";
        List<Role> roleList = new ArrayList<Role>();

        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                roleList.add(getRole(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public Role findById(long role_id) {
        String query = "SELECT * FROM roles WHERE role_id = "+role_id;
        Role role = new Role();

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            role = getRole(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public long create(Role role) {
        String query = "INSERT INTO roles (role_name) VALUES ('"+role.getRole_name()+"')";
        long role_id = 0;
        try {
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                role_id = result.getLong(1);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role_id;
    }

    @Override
    public void update(long role_id, Role role) {
        String query = "UPDATE roles SET role_name = '"+role.getRole_name()+"' WHERE role_id = "+role_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void delete(long role_id) {
        String query = "DELETE FROM roles WHERE role_id = "+role_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
