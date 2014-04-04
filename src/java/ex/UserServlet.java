package ex;

import ex.UserBean;
import ex.UserDao;
import java.io.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(value = "/UserMVC", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "index.jsp"),})
public class UserServlet extends HttpServlet {

    private UserDao dao;
    private ArtistDao artistdao;
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
            artistdao = new ArtistDao();
            artistdao.setDriver(driver);
            artistdao.setUser(user);
            artistdao.setPassword(password);
            artistdao.setUrl(url);
            
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

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            Map<String, String> messages = new HashMap<String, String>();

            boolean userExists;
            String isartist = request.getParameter("CheckBoxStageName");
            String artistname = request.getParameter("inputStagename");
            String login = request.getParameter("inputUsername");
            String pass = request.getParameter("inputPassword");
            String name = request.getParameter("inputName");
            String firstname = request.getParameter("inputFirstName");
            String email = request.getParameter("inputEmail");
            String confEmail = request.getParameter("inputConfEmail");
            int phone = Integer.parseInt(request.getParameter("inputPhonenumber"));
            String day = request.getParameter("day");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String birthDate = year + "-" + month + "-" + day;
            userExists = dao.userExists(email);
            SecurePassword s = new SecurePassword();
            String generatedPassword = s.md5password(pass);
            System.out.println(isartist + " test ");
            System.out.println(confEmail + "  " + email);
            if (!email.equals(confEmail)) {
                System.out.println("Email addresses are not equal");
                messages.put("email", "Beide email adressen moeten gelijk zijn!");
            } else {
                if (isartist == null) {
                    if (!userExists) {

                        UserBean user = new UserBean(login, generatedPassword, name, firstname, birthDate, email, phone, 0);
                        dao.addUser(user);

                        messages.put("register", "U bent met succes geregistreerd!");
                    } else {
                        System.out.println("user allready exists");
                        messages.put("user", "Er bestaat al een user met dit email adres");
                    }
                }else{
 //public ArtistBean(String login, String pass, String name, String firstName, String birthDate, String email, int phone, String artistName) {

                    ArtistBean artist = new ArtistBean(login, generatedPassword, name, firstname, birthDate, email, phone, artistname);
                    artistdao.addArtist(artist);
                }
            }

            request.setAttribute("messages", messages);
            RequestDispatcher disp = request.getRequestDispatcher(page);
            if (disp != null) {
                disp.forward(request, response);
            }
            doGet(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
