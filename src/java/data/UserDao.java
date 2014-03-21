package data;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.*;

public class UserDao {

    private String url;
    private String user;
    private String password;
    private static final String GET_QUERY = "select UserLogin, UserPass, UserName, UserFirstName, UserBirthdate, UserEmail, UserPhone, UserCredits from user";
    private static final String UPDATE_QUERY = "insert into user (UserLogin, UserPass, UserName, UserFirstName, UserBirthdate, UserEmail, UserPhone, UserCredits) values (?,?,?,?,?,?,?,?)";

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriver(String driver) throws ClassNotFoundException {
        Class.forName(driver);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserBean> getUsers() throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<UserBean> users = new ArrayList<UserBean>();
            ResultSet rs = stmt.executeQuery(GET_QUERY);
            while (rs.next()) {
                UserBean user = new UserBean(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8));
                users.add(user);
            }
            return users;
        }

    }

    public void addUser(UserBean user) throws SQLException {
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getBirthDate());
            stmt.setString(6, user.getEmail());
            stmt.setInt(7, user.getPhone());
            stmt.setInt(8, user.getCredits());
            stmt.executeUpdate();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
