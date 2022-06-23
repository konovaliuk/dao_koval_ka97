package com.booking.koval.commands;

import com.booking.koval.entities.Order;
import com.booking.koval.entities.User;
import com.booking.koval.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandProcessedOrders implements ICommand {
    private final static String USER_ORDERS_PAGE = "/processedOrders.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().getProcessedOrdersByUserId(user.getUser_id());
        request.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}
