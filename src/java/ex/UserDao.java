package ex;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDao {

    private String url;
    private String user;
    private String password;
    private static final String GET_QUERY = "select UserLogin, UserPass, UserName, UserFirstName, UserBirthdate, UserEmail, UserPhone, UserCredits from user";
    private static final String UPDATE_QUERY = "insert into user (UserLogin, UserPass, UserName, UserFirstName, UserBirthdate, UserEmail, UserPhone, UserCredits) values (?,?,?,?,?,?,?,?)";
    
    static Connection currentCon = null;
    static ResultSet rs = null;

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

    public boolean userExists(String email) throws SQLException {
        String query = "select * from user where UserEmail='" + email + "';";

        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<UserBean> users = new ArrayList<UserBean>();
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("no data");
                return false;
            }

            return true;
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
    
    public void changePhone(String newPhone, String email) throws SQLException{
        
        String change_phone_query = "UPDATE user SET UserPhone= " + newPhone + " WHERE UserEmail='" + email + "';";
        System.out.println(change_phone_query);
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(change_phone_query)) {
                  
                  stmt.executeUpdate();
        }
    }
    public void changePassword(String newPassword, String email) throws SQLException{
        
        String change_phone_query = "UPDATE user SET UserPass= '" + newPassword + "' WHERE UserEmail='" + email + "';";
        System.out.println(change_phone_query);
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(change_phone_query)) {
                  
                  stmt.executeUpdate();
        }
    }
    
       public void buyCredits(int newCredits, String email) throws SQLException{
        
        String buy_credits_query = "UPDATE user SET UserCredits= '" + newCredits + "' WHERE UserEmail='" + email + "';";
        System.out.println(buy_credits_query);
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(buy_credits_query)) {
                  
                  stmt.executeUpdate();
        }
    }
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static UserBean login(UserBean bean) {

        //preparing some objects for connection 
        Statement stmt = null;

        String email = bean.getEmail();
        String password = bean.getPass();

        String searchQuery =
                "select * from user where UserEmail='"
                + email
                + "' AND UserPass='"
                + password
                + "'";

        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your user name is " + email);
        System.out.println("Your password is " + password);
        System.out.println("Query: " + searchQuery);

        try {
            //connect to DB 
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user does not exist set the isValid variable to false
            if (!more) {
                System.out.println("Sorry, you are not a registered user! Please sign up first");
                bean.setValid(false);
            } //if user exists set the isValid variable to true
            else if (more) {
                String Userlogin = rs.getString("UserLogin");
                String Userpass = rs.getString("UserPass");
                String UserlastName = rs.getString("UserName");
                String UserfirstName = rs.getString("UserFirstName");
                String UserbirthDate = rs.getString("UserBirthDate");
                String Useremail = rs.getString("UserEmail");
                int Userphone = rs.getInt("UserPhone");
                int Usercredits = rs.getInt("UserCredits");

                System.out.println("Welcome " + UserfirstName);
                bean.setLogin(Userlogin);
                bean.setPass(Userpass);
                bean.setName(UserlastName);
                bean.setFirstName(UserfirstName);
                bean.setBirthDate(UserbirthDate);
                bean.setEmail(Useremail);
                bean.setPhone(Userphone);
                bean.setCredits(Usercredits);
                bean.setValid(true);
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        } //some exception handling
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return bean;

    }
}
