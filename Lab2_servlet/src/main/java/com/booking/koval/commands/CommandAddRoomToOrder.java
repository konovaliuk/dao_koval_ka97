package com.booking.koval.commands;

import com.booking.koval.entities.Order;
import com.booking.koval.entities.Room;
import com.booking.koval.service.OrderService;
import com.booking.koval.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandAddRoomToOrder implements ICommand{
    private static final String ADD_ROOM_PAGE = "/addRoomToOrder.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.parseLong(request.getParameter("order_id"));
        Order order = orderService.findOrderById(order_id);
        request.setAttribute("order", order);

        if ("GET".equals(request.getMethod())) {
            List<Room> rooms = new RoomService().getAll();
            request.setAttribute("rooms", rooms);
            return ADD_ROOM_PAGE;
        }
        else {
            long room_id = Long.valueOf(request.getParameter("room_id"));
            orderService.accept(order_id, room_id);
            return "redirect:/allOrders";
        }
    }
}
