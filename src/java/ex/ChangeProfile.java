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
            dao = new UserDao();
            daoArtist = new ArtistDao();
            dao.setDriver(driver);
            daoArtist.setDriver(driver);
            dao.setUser(user);
            daoArtist.setUser(user);
            dao.setPassword(password);
            daoArtist.setPassword(password);
            dao.setUrl(url);
            daoArtist.setUrl(url);
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
        ArtistBean currentArtist = null;
        UserBean currentUser = null;
        if (changeprofile != null) {

            Map<String, String> feedback = new HashMap<String, String>();

            String newPasswordToHash = request.getParameter("newPassword");
            SecurePassword s = new SecurePassword();

            String newPassword = s.md5password(newPasswordToHash);
            String newPhone = request.getParameter("newPhone");
            HttpSession session = request.getSession();
            int currentId = 0;
            
            boolean isArtist = false;
            try {
                 currentUser = (UserBean) session.getAttribute("currentSessionUser");
                currentId = currentUser.getId();

            } catch (NullPointerException e) {
                System.out.println("Momenteel artist");
                isArtist = true;
            }

            if (isArtist) {
               currentArtist = (ArtistBean) session.getAttribute("currentSessionArtist");
                currentId = currentArtist.getId();
                
            }

            System.out.println("currentArtistId: " + currentId);

            try {
                if (!newPasswordToHash.equals("")) {
                    if (isArtist) {
                        System.out.println("new password: " + newPassword);
                        daoArtist.changePassword(newPassword, currentId);
                        currentArtist.setPass(newPassword);
                    } else {
                        dao.changePassword(newPassword, currentId);
                        currentUser.setPass(newPassword);
                    }

                    feedback.put("wijziging", "Uw wijzigingen zijn opgeslagen!");
                }
                if (!newPhone.equals("")) {
                    if (isArtist) {
                        daoArtist.changePhone(newPhone, currentId);
                        currentArtist.setPhone(Integer.parseInt(newPhone));
                    } else {
                        dao.changePhone(newPhone, currentId);
                        currentUser.setPhone(Integer.parseInt(newPhone));
                    }

                    feedback.put("wijziging", "Uw wijzigingen zijn opgeslagen!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ChangeProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            if(isArtist){
                session.setAttribute("sessionCurrentArtist", currentArtist);
            }else{
                session.setAttribute("sessionCurrentUser", currentUser);
            }
            
            
            request.setAttribute("feedback", feedback);
            RequestDispatcher disp = request.getRequestDispatcher(page);
            disp.forward(request, response);
        } else {
            RequestDispatcher disp = request.getRequestDispatcher("/credits.jsp");
            disp.forward(request, response);
        }

    }
}
