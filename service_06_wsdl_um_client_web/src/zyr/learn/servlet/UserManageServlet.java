package zyr.learn.servlet;


import zyr.learn.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URL;

/**
 * Created by zhouweitao on 2016/12/1.
 */
@javax.servlet.annotation.WebServlet(name = "UserManageServlet", urlPatterns = "/user")
public class UserManageServlet extends HttpServlet {

    private UserService userService;
    private IUserService userServicePort;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //URL url = new URL("http://localhost:7788/um/services?wsdl");
        //QName qName = new QName("http://service.learn.zyr", "UserService");
        //this.userService = new UserService(url,qName);
        this.userService = new UserService();
        this.userServicePort = userService.getUserServicePort();
        String method = request.getParameter("m");
        switch (method) {
            case "login":
                login(request, response);
                break;
            case "addInput":
                addInput(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "del":
                delete(request, response);
                break;
            case "list":
                list(request, response);
                break;
            default:
                list(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User user = userServicePort.login(username, password);
            request.getSession().setAttribute("loginUser", user);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
        list(request, response);
    }

    private void addInput(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("WEB-INF/view/add.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) {
        LicenceHeandler.addLicenceHeader(request,userServicePort);
        User u = new User();
        u.setUsername(request.getParameter("username"));
        u.setPassword(request.getParameter("password"));
        u.setNickname(request.getParameter("nickname"));
        try {
            userServicePort.add(u);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
        list(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        LicenceHeandler.addLicenceHeader(request,userServicePort);
        try {
            userServicePort.delete(request.getParameter("username"));
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
        list(request, response);
    }

    public void list(HttpServletRequest request, HttpServletResponse response) {
        try {
            java.util.List<User> users = userServicePort.list();
            request.setAttribute("users", users);
            request.getRequestDispatcher("WEB-INF/view/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
