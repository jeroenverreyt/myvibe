/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
 * @author Keris
 */
@WebServlet(value = "/CreditServlet", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/credits.jsp"),})
public class CreditServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String> mess = new HashMap<String, String>();
       
        
        int addCredits = Integer.parseInt(request.getParameter("buyCredits"));
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
        
        int currentCredits = currentUser.getCredits();
        int newCredits = currentCredits + addCredits;
        //credits updaten
        currentUser.setCredits(newCredits);
        //nieuwe sessie maken met bijgevoegde credits
        session.setAttribute("currentSessionUser", currentUser);
       
       String email = currentUser.getEmail();
        try {
            dao.buyCredits(newCredits, email);
           mess.put("credits", "Uw aankoop is voltooid");
            
        
        } catch (SQLException ex) {
            Logger.getLogger(CreditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
          
       request.setAttribute("mess", mess);
       RequestDispatcher disp = request.getRequestDispatcher(page);
       disp.forward(request, response);
   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
