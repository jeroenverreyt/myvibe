package ex;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(value = "/OverzichtUserMVC", initParams = {
      @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
      @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
      @WebInitParam(name = "user", value = "keris"),
      @WebInitParam(name = "password", value = "kerisve"),
      @WebInitParam(name = "page", value = "/WEB-INF/pages/overzichtUsers.jsp"), })
public class OverzichtUserServlet extends HttpServlet {
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
      try {
         List<UserBean> users = dao.getUsers(); 
         request.setAttribute("users", users);
         RequestDispatcher disp = request.getRequestDispatcher(page);
         if (disp != null) {
            disp.forward(request, response);
         }
      } catch (SQLException e) {
         throw new ServletException(e);
      }
   }

 }