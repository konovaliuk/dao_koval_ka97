package com.booking.koval.commands;

import com.booking.koval.dao.UserDAO;
import com.booking.koval.entities.Order;
import com.booking.koval.entities.User;
import com.booking.koval.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CommandIndex implements ICommand{
    private final static String MAIN_PAGE = "/index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("username", user.getUsername());
        return MAIN_PAGE;
    }
}
