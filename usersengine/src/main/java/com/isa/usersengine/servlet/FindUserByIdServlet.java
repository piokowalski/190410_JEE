package com.isa.usersengine.servlet;

import com.isa.usersengine.cdi.MaxPulseBean;
import com.isa.usersengine.domain.Gender;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.TemplateProvider;
import com.isa.usersengine.service.UserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FindUserByIdServlet.class.getName());

    @Inject
    UserService userService;

    @Inject
    MaxPulseBean maxPulseBean;

    @Inject
    TemplateProvider templateProvider;

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

        Template template = templateProvider.getTemplate(getServletContext(), "find-user-by-id.ftlh");
        Map<String, Object> model = new HashMap<>();

        if (userById != null) {

            Double pulse = 0.0;
            if (Gender.WOMAN.equals(userById.getGender())) {
                pulse = maxPulseBean.getWomanPulse(userById.getAge());
            } else if (Gender.MAN.equals(userById.getGender())) {
                pulse = maxPulseBean.getManMaxPulse(userById.getAge());
            }

            model.put("user", userById);
            model.put("pulse", pulse);

        } else {
            model.put("errorMessage", "User has not been found.");
        }

        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }
}
