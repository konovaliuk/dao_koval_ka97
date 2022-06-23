package com.booking.koval.dao;

import com.booking.koval.dao.interfaces.IOrderDAO;
import com.booking.koval.dao.interfaces.IRoleDAO;
import com.booking.koval.dao.interfaces.IRoomDAO;
import com.booking.koval.dao.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {

    public static IOrderDAO getOrderDAO(Connection connect) throws SQLException {
        return new OrderDAO(connect);
    }
    public static IRoleDAO getRoleDAO(Connection connect) throws SQLException {
        return new RoleDAO(connect);
    }
    public static IRoomDAO getRoomDAO(Connection connect) throws SQLException {
        return new RoomDAO(connect);
    }
    public static IUserDAO getUserDAO(Connection connect) throws SQLException {
        return new UserDAO(connect);
    }
}