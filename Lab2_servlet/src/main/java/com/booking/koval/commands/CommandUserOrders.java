package com.booking.koval.commands;

import com.booking.koval.dao.OrderDAO;
import com.booking.koval.entities.Order;
import com.booking.koval.entities.User;
import com.booking.koval.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandUserOrders implements ICommand{
    private final static String USER_ORDERS_PAGE = "/userOrders.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().getOrdersByUserId(user.getUser_id());
//        System.out.println(orders);
//        OrderDAO orderDAO = new OrderDAO();
//        List<Order> orders1 = orderDAO.findAll();
//        orderDAO.closeConnection();
//        System.out.println(orders1);
        request.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}
