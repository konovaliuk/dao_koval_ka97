package com.booking.koval.commands;

import com.booking.koval.entities.Order;
import com.booking.koval.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDenyOrder implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.parseLong(request.getParameter("order_id"));
        orderService.deny(order_id);
        return "redirect:/allOrders";
    }
}
