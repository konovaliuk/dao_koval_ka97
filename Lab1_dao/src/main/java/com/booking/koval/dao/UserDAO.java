package com.booking.koval.dao;

import com.booking.koval.dao.interfaces.IUserDAO;
import com.booking.koval.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private final String COLUMN_USER_ID = "user_id";
    private final String COLUMN_USERNAME = "username";
    private final String COLUMN_PASSWORD = "password";
    private final String COLUMN_ROLE_ID = "role_id";
    private final Connection connection;
    private final Statement statement;

    private User getUser(ResultSet result) throws SQLException {
        long user_id = result.getLong(COLUMN_USER_ID);
        String username = result.getString(COLUMN_USERNAME);
        String password = result.getString(COLUMN_PASSWORD);
        long role_id = result.getLong(COLUMN_ROLE_ID);
        return new User(user_id, username, password, role_id);
    }

    public UserDAO(final Connection connect) throws SQLException {
        this.connection = connect;
        this.statement = connect.createStatement();
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> userList = new ArrayList<User>();

        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                userList.add(getUser(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(long user_id) {
        String query = "SELECT * FROM users WHERE user_id = "+user_id;
        User user = new User();

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            user = getUser(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public long create(User user) {
        String query = "INSERT INTO users (username, password, role_id) VALUES ('"+user.getUsername()+"', '"+BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())+"', '"+user.getRole_id()+"')";
        long user_id = 0;
        try {
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user_id = result.getLong(1);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    @Override
    public void update(long user_id, User user) {
        String query = "UPDATE users SET password = '"+BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())+"' WHERE user_id = "+user_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void delete(long user_id) {
        String query = "DELETE FROM users WHERE user_id = "+user_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
