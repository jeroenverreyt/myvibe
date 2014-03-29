/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.LoopTagStatus;

/**
 *
 * @author Jeroen
 */
@WebServlet(value = "/trackDownload", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/download.jsp"),})
public class TrackDownloadServlet extends HttpServlet {

    private TrackDao dao;
    private String page;
    LoopTagStatus status;
    private TrackBean track  = (TrackBean) status.getCurrent();

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
            dao = new TrackDao();
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
        String trackName = track.getTrackname();
        System.out.println(trackName);
        RequestDispatcher disp = request.getRequestDispatcher(page);
        disp.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @param status
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
