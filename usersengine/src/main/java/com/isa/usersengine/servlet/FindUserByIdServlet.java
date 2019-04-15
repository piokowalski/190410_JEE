package com.isa.usersengine.servlet;

import com.isa.usersengine.cdi.MaxPulseBean;
import com.isa.usersengine.domain.Gender;
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

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    @Inject
    UserService userService;

    @Inject
    MaxPulseBean maxPulseBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idParam = req.getParameter("id");
        PrintWriter writer = resp.getWriter();

        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long id = Long.parseLong(idParam);

        User userById = userService.findUserById(id);

        if (userById != null) {
            writer.println("Name: " + userById.getName());
            writer.println("Login: " + userById.getLogin());
            writer.println("Age: " + userById.getAge());
            Double pulse = 0.0;
            if (Gender.WOMAN.equals(userById.getGender())) {
                pulse = maxPulseBean.getWomanPulse(userById.getAge());
            } else if (Gender.MAN.equals(userById.getGender())) {
                pulse = maxPulseBean.getManMaxPulse(userById.getAge());
            }
            writer.println("Pulse: " + pulse);

        } else {
            writer.println("User has not been found.");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }
}
