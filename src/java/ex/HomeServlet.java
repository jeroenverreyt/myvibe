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
@WebServlet(value = "/HomeServlet", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/home.jsp"),})
public class HomeServlet extends HttpServlet {

    private TracksperuserDao daoTracksPerUser;
    private TrackDao dao;
    private ArtistDao daoArtist;
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
            dao = new TrackDao();
            daoArtist = new ArtistDao();
            daoTracksPerUser = new TracksperuserDao();
            dao.setDriver(driver);
            daoArtist.setDriver(driver);
            daoTracksPerUser.setDriver(driver);
            dao.setUser(user);
            daoTracksPerUser.setUser(user);
            daoArtist.setUser(user);
            dao.setPassword(password);
            daoArtist.setPassword(password);
            daoTracksPerUser.setPassword(password);
            dao.setUrl(url);
            daoArtist.setUrl(url);
            daoTracksPerUser.setUrl(url);

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
            UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");

            int currentId = currentUser.getId();
            List<TrackBean> topTrackUploaded = dao.getTopUploaded();
            List<TrackBean> mostRecent = dao.getMostRecent();
            List<TracksperuserBean> tracksperuser = daoTracksPerUser.getTracks(currentId);
            System.out.println("currentId: " + currentId);
            ArrayList topUpload = new ArrayList();
            ArrayList topRecent = new ArrayList();
            ArrayList userTracks = new ArrayList();

            for (TrackBean t : topTrackUploaded) {
                Map<String, String> track = new HashMap<String, String>();
                track.put("name", t.getTrackname());
                track.put("artist", daoArtist.getArtistName(t.getArtist_artistid()));
                topUpload.add(track);
            }
            for (TrackBean t : mostRecent) {
                Map<String, String> track = new HashMap<String, String>();
                track.put("name", t.getTrackname());
                track.put("artist", daoArtist.getArtistName(t.getArtist_artistid()));
                topRecent.add(track);
            }
               for (TracksperuserBean t : tracksperuser) {
                Map<String, String> track = new HashMap<String, String>();
                track.put("name", dao.getTrackName(t.getTrackid()));
                track.put("artist", daoArtist.getArtistName(dao.getTrackArtistId(t.getTrackid())));
                userTracks.add(track);
              
            }
            request.setAttribute("topUpload", topUpload);
            request.setAttribute("topRecent", topRecent);
            request.setAttribute("userTracks", userTracks);
           
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
