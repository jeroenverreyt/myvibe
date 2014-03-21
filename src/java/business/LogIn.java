/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import data.UserBean;
import data.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
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
      @WebInitParam(name = "page", value = "/WEB-INF/pages/home.jsp"), })
public class LogIn extends HttpServlet {

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
            throws ServletException, IOException {
        //get parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //create user object
        UserBean user = new UserBean();
        user.setEmail(email);
        user.setPass(password);
        
        String message= "";
        String url = "";
        
        if(UserDao.userExists(user.getEmail(),user.getPass())){
            url="/home.jsp";
        }
        else{
            url="users.jsp";
            message="Deze gebruiker bestaat niet.";
        }
        
        //store user and message in session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.setAttribute("message", message);
        
        //forward the request and response to the view
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);        
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
        processRequest(request, response);
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
