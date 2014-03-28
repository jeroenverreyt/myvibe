package ex;

import ex.UserBean;
import ex.UserDao;
import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(value = "/ChangeProfile", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/profile.jsp"),})
public class ChangeProfile extends HttpServlet {

    private UserDao dao;
    private String page;

    public void init() throws ServletException {
        try {
            String driver = getInitParameter("driver");
            String url = getInitParameter("url");
            String user = getInitParameter("user");
            String password = getInitParameter("password");
            page = getInitParameter("page");
            if (driver == null || url == null || user == null || password == null
                    || page == null) {
                throw new ServletException("Init parameter missing");
            }
            dao = new UserDao();
            dao.setDriver(driver);
            dao.setUser(user);
            dao.setPassword(password);
            dao.setUrl(url);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Unable to load driver", ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
      
    

        String newPassword = request.getParameter("newPassword");
        String newPhone = request.getParameter("newPhone");
         HttpSession session = request.getSession();
         String email =(String) session.getAttribute("emailCurrentuser");
           System.out.println(email);
        try {
            if(!newPassword.equals("")){
                dao.changePassword(newPassword, email);
            }
            if(!newPhone.equals("")){
                dao.changePhone(newPhone, email); 
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

       RequestDispatcher disp = request.getRequestDispatcher(page);
                disp.forward(request, response);
    }
   
}

