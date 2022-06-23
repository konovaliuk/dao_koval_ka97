package com.booking.koval.commands;

import com.booking.koval.entities.Order;
import com.booking.koval.entities.User;
import com.booking.koval.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandAllProcessedOrders implements ICommand{
    private final static String USER_ORDERS_PAGE = "/allProcessedOrders.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = new OrderService().getAllProcessedOrders();
        request.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}
