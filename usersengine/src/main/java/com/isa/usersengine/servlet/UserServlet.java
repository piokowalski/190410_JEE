package com.isa.usersengine.servlet;

import com.isa.usersengine.domain.User;
import com.isa.usersengine.service.UserService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Inject
    UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = new User();

        user.setId(Long.valueOf(req.getParameter("id")));
        user.setName(String.valueOf(req.getParameter("name")));
        user.setLogin(String.valueOf(req.getParameter("login")));
        user.setPassword(String.valueOf(req.getParameter("password")));
        user.setAge(Integer.valueOf(req.getParameter("age")));

        userService.saveUser(user);

        resp.getWriter().println("User added with success!");
    }
}
