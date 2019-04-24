package com.isa.usersengine.servlet;

import com.isa.usersengine.cdi.FileUploadProcessorBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.exceptions.UserImageNotFound;
import com.isa.usersengine.freemarker.TemplateProvider;
import com.isa.usersengine.service.UserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet(urlPatterns = {"/user", "/user/add", "/user/edit"})
public class UserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserServlet.class.getName());

    @Inject
    UserService userService;

    @Inject
    TemplateProvider templateProvider;

    @Inject
    FileUploadProcessorBean fileUploadProcessorBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Map<String, Object> model = new HashMap<>();
        User user = new User();

        user.setId(Long.valueOf(req.getParameter("id")));
        user.setName(String.valueOf(req.getParameter("name")));
        user.setLogin(String.valueOf(req.getParameter("login")));
        user.setPassword(String.valueOf(req.getParameter("password")));
        user.setAge(Integer.valueOf(req.getParameter("age")));

        try {
            File file = fileUploadProcessorBean.uploadImageFile(req.getPart("image"));
            user.setImageURL("/images/" + file.getName());
        } catch (UserImageNotFound userImageNotFound) {
            userImageNotFound.printStackTrace();
        }

        userService.saveUser(user);

        Template template = templateProvider.getTemplate(getServletContext(), "add-edit-user.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Map<String, Object> model = new HashMap<>();
        User user = userService.findUserById(Long.valueOf(req.getParameter("id")));

        user.setId(Long.valueOf(req.getParameter("id")));
        user.setName(String.valueOf(req.getParameter("name")));
        user.setLogin(String.valueOf(req.getParameter("login")));

        if (!String.valueOf(req.getParameter("password")).isEmpty()) {
            user.setPassword(String.valueOf(req.getParameter("password")));
        }
        user.setAge(Integer.valueOf(req.getParameter("age")));

        try {
            File file = fileUploadProcessorBean.uploadImageFile(req.getPart("image"));
            user.setImageURL("/images/" + file.getName());
        } catch (UserImageNotFound userImageNotFound) {
            userImageNotFound.printStackTrace();
        }

        Template template = templateProvider.getTemplate(getServletContext(), "add-edit-user.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> model = new HashMap<>();
        String templateName = "users-list.ftlh";
        String[] servletMapping = req.getHttpServletMapping().getMatchValue().split("/");
        String action = servletMapping[servletMapping.length - 1];

        if (action.equals("user")) {
            model.put("users", userService.findAllUsers());
        } else if (action.equals("add")) {
            templateName = "add-edit-user.ftlh";
        } else if (action.equals("edit")) {
            Long id = Long.valueOf(req.getParameter("id"));
            templateName = "add-edit-user.ftlh";
            model.put("user", userService.findUserById(id));
            model.put("action", "edit");
        }

        Template template = templateProvider.getTemplate(getServletContext(), templateName);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteUserById(Long.parseLong(req.getParameter("id")));
    }
}
