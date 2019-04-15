package com.isa.usersengine.servlet;

import com.isa.usersengine.cdi.RandomUserCDIApplicationBean;
import com.isa.usersengine.cdi.RandomUserCDIRequestBean;
import com.isa.usersengine.cdi.RandomUserCDISessionBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/random-user")
public class RandomUserServlet extends HttpServlet {

    @Inject
    RandomUserCDIApplicationBean randomUserCDIApplicationBean;

    @Inject
    RandomUserCDISessionBean randomUserCDISessionBean;

    @Inject
    RandomUserCDIRequestBean randomUserCDIRequestBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("Request scoped: " + randomUserCDIRequestBean.getRandomUser());
        writer.println("Session scoped: " + randomUserCDISessionBean.getRandomUser());
        writer.println("Application scoped: " + randomUserCDIApplicationBean.getRandomUser());
    }
}
