package com.isa.usersengine.servlet;

import com.isa.usersengine.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/number-range")
public class RangeNumbersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RangeNumbersServlet.class.getName());

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberParam = req.getParameter("number");

        Map<String, Object> model = new HashMap<>();
        Template template = templateProvider.getTemplate(getServletContext(), "range-numbers.ftlh");

        if (numberParam == null || numberParam.isEmpty()) {
            model.put("errorMessage", "Parameter number is obligatory!");
        } else {
            Integer number = Integer.parseInt(numberParam);
            model.put("maxNumber", number);
        }

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }
}
