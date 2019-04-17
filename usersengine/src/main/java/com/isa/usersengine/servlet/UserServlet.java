package com.isa.usersengine.servlet;

import com.isa.usersengine.domain.User;
import com.isa.usersengine.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html><html><body>");
        writer.println("<script src=\"https://code.jquery.com/jquery-3.4.0.min.js\"></script>");

        userService.findAllUsers().forEach(u -> {
            writer.println("ID: " + u.getId() + "<br>");
            writer.println("Login: " + u.getLogin() + "<br>");
            writer.println("Name: " + u.getName() + "<br>");
            writer.println("<a href=\"#\" class=\"delete-user-link\" data-id=\"" + u.getId() + "\">delete user</a>");
            writer.println("<br><br>================<br><br>");
        });

        writer.println("<script src=\"/js/user.js\"></script>");
        writer.println("</body></html>\n");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUserById(Long.parseLong(req.getParameter("id")));
    }
}
