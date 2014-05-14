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

        Map<String, String> messages = new HashMap<String, String>();
        Map<String, String> fields = new HashMap<String, String>();
        try{
            
        
        boolean userExists;
        String isartist = request.getParameter("CheckBoxStageName");
         String artistname = "";
        String login = request.getParameter("inputUsername");
        fields.put("login", login);
        String pass = request.getParameter("inputPassword");
        String name = request.getParameter("inputName");
        fields.put("name", name);
        String firstname = request.getParameter("inputFirstName");
        fields.put("firstname", firstname);
        String email = request.getParameter("inputEmail");
        fields.put("email", email);
        String confEmail = request.getParameter("inputConfEmail");
        fields.put("confEmail", confEmail);
        int phone = Integer.parseInt(request.getParameter("inputPhonenumber"));
        fields.put("phone",request.getParameter("inputPhonenumber" ));
        String day = request.getParameter("day");
        fields.put("day", day);
        String month = request.getParameter("month");
        fields.put("month", month);
        String year = request.getParameter("year");
        fields.put("year", year);
        String birthDate = year + "-" + month + "-" + day;
        userExists = dao.userExists(email);
        SecurePassword s = new SecurePassword();
        String generatedPassword = s.md5password(pass);
        System.out.println(isartist + " test ");
        System.out.println(confEmail + "  " + email);

        if (isartist == null) {
            isartist = "user";
        } else if (isartist.equals("on")) {
             isartist = "artist";
            artistname = request.getParameter("inputStagename");
             fields.put("artistname", artistname);
            fields.put("artist", isartist);
        }

        if (!email.equals(confEmail)) {
            System.out.println("Email addresses are not equal");
            messages.put("email", "Beide email adressen moeten gelijk zijn!");
            fields.remove("email");
            fields.remove("confEmail");
        } else {
            switch (isartist) {
                case "user":
                    //it's a user!
                    if (!userExists) {

                        UserBean user = new UserBean(login, generatedPassword, name, firstname, birthDate, email, phone, 0);
                        dao.addUser(user);

                        messages.put("register", "U bent met succes geregistreerd!");
                    } else {
                        System.out.println("user allready exists");
                        messages.put("user", "Er bestaat al een user met dit email adres");
                        fields.remove("email");
                        fields.remove("confEmail");
                    }

                    break;

                case "artist":
                    //it's an artist!
                    boolean artistExists = artistdao.artistExists(email);
                   
                    if (!artistExists) {
                        
                            ArtistBean artist = new ArtistBean(login, generatedPassword, name, firstname, birthDate, email, phone, artistname);
                            artistdao.addArtist(artist);

                            messages.put("register", "U bent met succes geregistreerd!");
                        
                    } else {
                        System.out.println("artist allready exists");
                        messages.put("user", "Er bestaat al een artist met dit email adres");
                        fields.remove("email");
                        fields.remove("confEmail");
                    }

                    break;
            }
        }

                }catch (SQLException ex) {
                    ex.printStackTrace();
                }

        request.setAttribute("messages", messages);
        request.setAttribute("fields", fields);
        RequestDispatcher disp = request.getRequestDispatcher(page);
        if (disp != null) {
            disp.forward(request, response);
        }
        doGet(request, response);
    }

}
