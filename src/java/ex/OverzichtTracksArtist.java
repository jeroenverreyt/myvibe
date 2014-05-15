/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(value = "/OverzichtTracksArtist", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/overzichttracks.jsp"),})
public class OverzichtTracksArtist extends HttpServlet {

    private TracksperuserDao daoTracksPerUser;
    private TrackDao dao;
    private ArtistDao daoArtist;
    private String page = "";

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

            daoArtist = new ArtistDao();
            daoArtist.setDriver(driver);
            daoArtist.setUser(user);
            daoArtist.setPassword(password);
            daoArtist.setUrl(url);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Unable to load driver", ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            ArtistBean currentArtist = (ArtistBean) session.getAttribute("currentSessionArtist");
            Map<String, String> howtosort = new HashMap<String, String>();
            String sort;
            String sortdirection;
            sort = "TrackName";
            sortdirection = "desc";
            String btnSort = request.getParameter("btnSort");

            int iSort = 1;
            int iSortDirection = 10;
            if (btnSort != null) {
                iSort = Integer.parseInt(request.getParameter("sorting"));
                iSortDirection = Integer.parseInt(request.getParameter("sortdirection"));
                System.out.println("iSort: " + iSort);
            }

            switch (iSort) {
                case 1:
                    sort = "TrackName";
                    howtosort.put("sort", "1");
                    break;
                case 2:
                    sort = "TrackReleaseDate";
                    howtosort.put("sort", "2");
                    break;
                case 3:
                    sort = "TrackCounter";
                    howtosort.put("sort", "3");
                    break;
            }
            switch (iSortDirection) {
                case 10:
                    sortdirection = "desc";
                    howtosort.put("sortdirection", "10");
                    break;
                case 20:
                    sortdirection = "";
                    howtosort.put("sortdirection", "20");
                    break;

            }

            int currentId = currentArtist.getId();
            List<TrackBean> tracksperartist = dao.getTracksPerArtist(currentId, sort, sortdirection);
            ArrayList tracks = new ArrayList();
            request.setAttribute("sort", howtosort);
            request.setAttribute("tracks", tracksperartist);
            RequestDispatcher disp = request.getRequestDispatcher(page);
            if (disp != null) {
                disp.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
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
