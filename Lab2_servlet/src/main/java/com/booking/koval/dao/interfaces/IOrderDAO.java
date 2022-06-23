package com.booking.koval.dao.interfaces;

import com.booking.koval.entities.Order;

import java.util.List;

public interface IOrderDAO {
    List<Order> findAll();
    Order findById(long order_id);
    long create(Order order);
    void update(long order_id, Order order);

    void accept(long order_id, long room_id);

    void deny(long order_id);

    void delete(long order_id);
}
