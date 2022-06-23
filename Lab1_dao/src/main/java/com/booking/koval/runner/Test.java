package com.booking.koval.runner;

import com.booking.koval.connection.ConnectionPool;
import com.booking.koval.dao.DAOFactory;
import com.booking.koval.dao.OrderDAO;
import com.booking.koval.dao.RoleDAO;
import com.booking.koval.dao.interfaces.IOrderDAO;
import com.booking.koval.dao.interfaces.IRoleDAO;
import com.booking.koval.dao.interfaces.IRoomDAO;
import com.booking.koval.dao.interfaces.IUserDAO;
import com.booking.koval.entities.*;
import com.booking.koval.entities.Class;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Test {
    private static void createOrder(IOrderDAO orderDAO, long capacity, Class class_, String start_date, String end_date) {
        try {
            Date start_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(start_date);
            Date end_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(end_date);
            Order order1 = new Order(capacity, class_, start_date1, end_date1, Status.pending);
            long order_id = orderDAO.create(order1);
            System.out.println("Created order #"+order_id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void findAllOrders(IOrderDAO orderDAO) {
        List<Order> orders = orderDAO.findAll();
        System.out.println("\nALL ORDERS IN DATABASE");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Order order : orders) {
            System.out.println("order_id = "+order.getOrder_id()+"; capacity = "+order.getCapacity()+
                    "; class = "+order.getClass_()+"; start_date = "+format.format(order.getStart_date())+
                    "; end_date = "+format.format(order.getEnd_date())+"; status = "+order.getStatus()+"; room_id = "+order.getRoom_id());
        }
    }

    private static void findOrderById(IOrderDAO orderDAO, long id) {
        Order order = orderDAO.findById(id);
        System.out.println("\nSHOWING ORDER #"+id+" IN DATABASE");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("order_id = "+order.getOrder_id()+"; capacity = "+order.getCapacity()+
                    "; class = "+order.getClass_()+"; start_date = "+format.format(order.getStart_date())+
                    "; end_date = "+format.format(order.getEnd_date())+"; status = "+order.getStatus()+"; room_id = "+order.getRoom_id());
    }

    private static void createRole(IRoleDAO roleDAO, String role_name) {
        Role role = new Role(role_name);
        long role_id = roleDAO.create(role);
        System.out.println("Created role #"+role_id+": "+role_name);
    }

    private static void findAllRoles(IRoleDAO roleDAO) {
        List<Role> roles = roleDAO.findAll();
        System.out.println("\nALL ROLES IN DATABASE");
        for (Role role : roles) {
            System.out.println("role_id = "+role.getRole_id()+"; role_name = "+role.getRole_name());
        }
    }

    private static void findRoleById(IRoleDAO roleDAO, long id) {
        Role role = roleDAO.findById(id);
        System.out.println("\nSHOWING ROLE #"+id+" IN DATABASE");
            System.out.println("role_id = "+role.getRole_id()+"; role_name = "+role.getRole_name());
    }

    private static void createRoom(IRoomDAO roomDAO, long price_per_night, long capacity, Class class_) {
        Room room1 = new Room(price_per_night, capacity, class_);
        long room_id = roomDAO.create(room1);
        System.out.println("Created room #"+room_id);
    }

    private static void findAllRooms(IRoomDAO roomDAO) {
        List<Room> rooms = roomDAO.findAll();
        System.out.println("\nALL ROOMS IN DATABASE");
        for (Room room : rooms) {
            System.out.println("role_id = "+room.getRoom_id()+"; price_per_night = "+room.getPrice_per_night()+"; capacity = "+ room.getCapacity()+"; class = "+room.getClass_());
        }
    }

    private static void findRoomById(IRoomDAO roomDAO, long id) {
        Room room = roomDAO.findById(id);
        System.out.println("\nSHOWING ROOM #"+id+" IN DATABASE");
        System.out.println("role_id = "+room.getRoom_id()+"; price_per_night = "+room.getPrice_per_night()+"; capacity = "+ room.getCapacity()+"; class = "+room.getClass_());
    }

    private static void createUser(IUserDAO userDAO, String username, String password, long role_id) {
        User user1 = new User(username, password, role_id);
        long user_id = userDAO.create(user1);
        System.out.println("Created user #"+user_id);
    }

    private static void findAllUsers(IUserDAO userDAO) {
        List<User> users = userDAO.findAll();
        System.out.println("\nALL USERS IN DATABASE");
        for (User user : users) {
            System.out.println("user_id = "+user.getUser_id()+"; username = "+user.getUsername()+"; password = "+ user.getPassword()+"; role_id = "+user.getRole_id());
        }
    }

    private static void findUserById(IUserDAO userDAO, long id) {
        User user = userDAO.findById(id);
        System.out.println("\nSHOWING USER #"+id+" IN DATABASE");
        System.out.println("user_id = "+user.getUser_id()+"; username = "+user.getUsername()+"; password = "+ user.getPassword()+"; role_id = "+user.getRole_id());
    }

    private static void OrderDAO_Test() throws SQLException {
        System.out.println("\n\n###___TESTING_ORDER_DAO___###\n");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connect = connectionPool.getConnection();

        IOrderDAO orderDAO = DAOFactory.getOrderDAO(connect);

        createOrder(orderDAO, 3, Class.business, "10-05-2022", "12-05-2022");
        createOrder(orderDAO, 2, Class.economy, "20-06-2022", "25-06-2022");
        findAllOrders(orderDAO);
        findOrderById(orderDAO, 2);
        Order order1 = new Order(2);
        long id1 = 1;
        orderDAO.update(id1, order1);
        System.out.println("\nORDER #"+id1+" UPDATED!");
        findOrderById(orderDAO, id1);
        long id2 = 1;
        orderDAO.delete(id2);
        System.out.println("\nORDER #"+id2+" DELETED!");
        findAllOrders(orderDAO);
    }

    private static void RoleDAO_Test() throws SQLException {
        System.out.println("\n\n###___TESTING_ROLE_DAO___###\n");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connect = connectionPool.getConnection();

        IRoleDAO roleDAO = DAOFactory.getRoleDAO(connect);
        createRole(roleDAO, "guest");
        createRole(roleDAO, "manager");
        findAllRoles(roleDAO);
        findRoleById(roleDAO, 2);
        long id1 = 2;
        Role role1 = new Role("hotel_manager");
        roleDAO.update(id1, role1);
        System.out.println("\nROLE #"+id1+" UPDATED!");
        findRoleById(roleDAO, id1);
        long id2 = 1;
        roleDAO.delete(id2);
        System.out.println("\nROLE #"+id2+" DELETED!");
        findAllRoles(roleDAO);
    }

    private static void RoomDAO_Test() throws SQLException {
        System.out.println("\n\n###___TESTING_ROOM_DAO___###\n");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connect = connectionPool.getConnection();

        IRoomDAO roomDAO = DAOFactory.getRoomDAO(connect);

        createRoom(roomDAO, 800, 3, Class.business);
        createRoom(roomDAO, 500, 2, Class.economy);
        createRoom(roomDAO, 1400, 3, Class.first);
        findAllRooms(roomDAO);
        findRoomById(roomDAO, 3);
        long id1 = 2;
        Room room1 = new Room(600);
        roomDAO.update(id1, room1);
        System.out.println("\nROLE #"+id1+" UPDATED!");
        findRoomById(roomDAO, id1);
        long id2 = 3;
        roomDAO.delete(id2);
        System.out.println("\nROLE #"+id2+" DELETED!");
        findAllRooms(roomDAO);
    }

    private static void UserDAO_Test() throws SQLException {
        System.out.println("\n\n###___TESTING_USER_DAO___###\n");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connect = connectionPool.getConnection();

        IUserDAO userDAO = DAOFactory.getUserDAO(connect);

        createUser(userDAO, "guest1", "1234", 1);
        createUser(userDAO, "guest2", "1221", 1);
        createUser(userDAO, "manager", "admin", 2);
        findAllUsers(userDAO);
        findUserById(userDAO, 2);
        long id1 = 2;
        User user1 = new User("0000");
        userDAO.update(id1, user1);
        System.out.println("\nROLE #"+id1+" UPDATED!");
        findUserById(userDAO, id1);
        long id2 = 1;
        userDAO.delete(id2);
        System.out.println("\nROLE #"+id2+" DELETED!");
        findAllUsers(userDAO);
    }

    public static void main(String args[]) throws SQLException {
        OrderDAO_Test();
        RoleDAO_Test();
        RoomDAO_Test();
        UserDAO_Test();
    }
}
