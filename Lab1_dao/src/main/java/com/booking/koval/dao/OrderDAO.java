package com.booking.koval.dao;

import com.booking.koval.dao.interfaces.IOrderDAO;
import com.booking.koval.entities.Class;
import com.booking.koval.entities.Order;
import com.booking.koval.entities.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private final String COLUMN_ORDER_ID = "order_id";
    private final String COLUMN_CAPACITY = "capacity";
    private final String COLUMN_CLASS_ = "class_";
    private final String COLUMN_START_DATE = "start_date";
    private final String COLUMN_END_DATE = "end_date";
    private final String COLUMN_STATUS = "status";
    private final String COLUMN_ROOM_ID = "room_id";
    private final Connection connection;
    private final Statement statement;

    private Order getOrder(ResultSet result) throws SQLException {
        long order_id = result.getLong(COLUMN_ORDER_ID);
        long capacity = result.getLong(COLUMN_CAPACITY);
        Class class_ = Class.valueOf(result.getString(COLUMN_CLASS_));
        Date start_date = result.getDate(COLUMN_START_DATE);
        Date end_date = result.getDate(COLUMN_END_DATE);
        Status status = Status.valueOf(result.getString(COLUMN_STATUS));
        long room_id = result.getLong(COLUMN_ROOM_ID);
        return new Order(order_id, capacity, class_, start_date, end_date, status, room_id);
    }

    public OrderDAO(final Connection connect) throws SQLException {
        this.connection = connect;
        this.statement = connect.createStatement();
    }

    @Override
    public List<Order> findAll() {
        String query = "SELECT * FROM orders";
        List<Order> orderList = new ArrayList<Order>();

        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                orderList.add(getOrder(result));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order findById(long order_id) {
        String query = "SELECT * FROM orders WHERE order_id = "+order_id;
        Order order = new Order();
        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            order = getOrder(result);
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public long create(Order order) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String query = "INSERT INTO orders (capacity, class_, start_date, end_date, status) VALUES ('"+order.getCapacity()+"', '"+order.getClass_()+"', '"+format.format(order.getStart_date())+"', '"+format.format(order.getEnd_date())+"', '"+order.getStatus()+"')";
        long order_id = 0;
        try {
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                order_id = result.getLong(1);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_id;
    }

    @Override
    public void update(long order_id, Order order) {
        String query = "UPDATE orders SET room_id = "+order.getRoom_id()+", status = 'offered' WHERE order_id = "+order_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void delete(long order_id) {
        String query = "DELETE FROM orders WHERE order_id = "+order_id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
