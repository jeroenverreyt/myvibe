/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import ex.UserBean;
import ex.UserDao;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeroen
 */
@WebServlet(value = "/LogIn", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "home.jsp"),
    @WebInitParam(name = "errorpage", value = "index.jsp"),})
public class LogIn extends HttpServlet {

    private UserDao dao;
    private String page;
    private String errorpage;
    RequestDispatcher dispatcher;

    
    public void init() throws ServletException {
        try {
            String driver = getInitParameter("driver");
            String url = getInitParameter("url");
            String user = getInitParameter("user");
            String password = getInitParameter("password");
            page = getInitParameter("page");
            errorpage = getInitParameter("errorpage");
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

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         Map<String, String> feedback = new HashMap<String, String>();
        try {

            UserBean user = new UserBean();
            ArtistBean artist = new ArtistBean();
            
            user.setEmail(request.getParameter("email"));
            user.setPass(request.getParameter("password"));
            
            artist.setEmail(request.getParameter("email"));
            artist.setPass(request.getParameter("password"));

            user = UserDao.login(user);
            artist = ArtistDao.login(artist);

            if (user.isValid()) {

                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                session.removeAttribute("currentSessionArtist");
             
                session.setAttribute("loggedIn", true);
                response.sendRedirect("HomeServlet");
                System.out.println("Logged in as user!");
                
               // RequestDispatcher disp = request.getRequestDispatcher(page);
                //disp.forward(request, response);
            } else if(artist.isValid()){
                
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionArtist", artist);
                session.removeAttribute("currentSessionUser");
                session.setAttribute("loggedIn", true);
                response.sendRedirect("OverzichtTracksArtist");
                
                System.out.println("Logged in as artist!");
            } else {
                feedback.put("login", "Het wachtwoord of emailadres is fout!");
                request.setAttribute("feedback", feedback);
                RequestDispatcher disp = request.getRequestDispatcher(errorpage);
                disp.forward(request, response);
          
            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);


        } catch (SQLException ex) {
            Logger.getLogger(LogIn.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);


        } catch (SQLException ex) {
            Logger.getLogger(LogIn.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
