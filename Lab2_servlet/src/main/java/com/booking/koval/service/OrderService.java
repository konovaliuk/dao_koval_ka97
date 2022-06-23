package com.booking.koval.service;

import com.booking.koval.dao.OrderDAO;
import com.booking.koval.entities.Class;
import com.booking.koval.entities.Order;
import com.booking.koval.entities.Status;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    public List<Order> getAll(){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll();
    }

    public List<Order> getOrdersByUserId(long user_id) {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.findAll();
        orderDAO.closeConnection();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getUser_id() == user_id && order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getAllUnprocessedOrders() {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.findAll();
        orderDAO.closeConnection();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getProcessedOrdersByUserId(long user_id) {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.findAll();
        orderDAO.closeConnection();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getUser_id() == user_id && !order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getAllProcessedOrders() {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.findAll();
        orderDAO.closeConnection();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(!order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public Order findOrderById(long order_id) {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.findById(order_id);
        orderDAO.closeConnection();
        return order;
    }

    public void add(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.create(order);
        orderDAO.closeConnection();
    }

    public void edit(long order_id, Order order){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.update(order_id, order);
        orderDAO.closeConnection();
    }

    public void accept(long order_id, long room_id){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.accept(order_id, room_id);
        orderDAO.closeConnection();
    }

    public void deny(long order_id){
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.deny(order_id);
        orderDAO.closeConnection();
    }

    public void delete(long order_id) {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(order_id);
        orderDAO.closeConnection();
    }
}
