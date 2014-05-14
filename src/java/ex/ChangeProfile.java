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

        String buycredits = request.getParameter("buycredits");
        String changeprofile = request.getParameter("changeprofile");
        if (changeprofile!= null){
       
        Map<String, String> feedback = new HashMap<String, String>();

        String newPasswordToHash = request.getParameter("newPassword");
        SecurePassword s = new SecurePassword();
        
        String newPassword = s.md5password(newPasswordToHash);
        String newPhone = request.getParameter("newPhone");
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
      
        int currentId = currentUser.getId();
        
  
        try {
            if (!newPassword.equals("")) {
                
                dao.changePassword(newPassword, currentId);
                feedback.put("wijziging", "Uw wijzigingen zijn opgeslagen!");
            }
            if (!newPhone.equals("")) {
                dao.changePhone(newPhone, currentId);
                feedback.put("wijziging", "Uw wijzigingen zijn opgeslagen!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("feedback", feedback);
        RequestDispatcher disp = request.getRequestDispatcher(page);
        disp.forward(request, response);
        }else{
            RequestDispatcher disp = request.getRequestDispatcher("/credits.jsp");
        disp.forward(request, response);
        }
        
   
    }
}
