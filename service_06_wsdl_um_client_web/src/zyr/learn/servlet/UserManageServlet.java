package zyr.learn.servlet;

import zyr.learn.service.IUserService;
import zyr.learn.service.User;
import zyr.learn.service.UserException_Exception;
import zyr.learn.service.UserService;

import java.io.IOException;

/**
 * Created by zhouweitao on 2016/12/1.
 */
@javax.servlet.annotation.WebServlet(name = "UserManageServlet")
public class UserManageServlet extends javax.servlet.http.HttpServlet {

    private IUserService service;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.service = new UserService().getUserServicePort();
        String method = request.getParameter("method");
        switch (method) {
            case "add": add(); break;
            case "del": delete(); break;
            case "list": list(); break;
            default:list();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }


    public void add(){
        User u = new User();
        try {
            service.add(u);
        } catch (UserException_Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(){

    }

    public void list(){

    }

}
