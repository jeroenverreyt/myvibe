/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeroen
 */
public class ArtistDao {

    private String url;
    private String user;
    private String password;
    private static final String UPDATE_QUERY = "insert into artist (ArtistBirthdate, ArtistEmail, ArtistFirstName, ArtistName, ArtistLogin, ArtistPass, ArtistPhone, ArtistArtistName) values (?,?,?,?,?,?,?,?)";
    private static final String GET_ARTIST = "SELECT * FROM artist where ArtistID = ? ";
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

     public void addArtist(ArtistBean artist) throws SQLException {
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, artist.getBirthDate());
            stmt.setString(2, artist.getEmail());
            stmt.setString(3, artist.getFirstName());
            stmt.setString(4, artist.getName());
            stmt.setString(5, artist.getLogin());
            stmt.setString(6, artist.getPass());
            stmt.setInt(7, artist.getPhone());
            stmt.setString(8, artist.getArtistName());
            stmt.executeUpdate();
        }
    }
     
     public String getArtistName(int artistID) throws SQLException {
       

        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(GET_ARTIST)) {
            stmt.setInt(1, artistID);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("no data");
                return "no artist";
            }else{
                String artistName = rs.getString(9);
               return artistName;  
            }

           
        }
    }
      public void changePassword(String newPassword, int id) throws SQLException {

        String change_phone_query = "UPDATE artist SET ArtistPass= ? WHERE ArtistId= ? ";
        System.out.println(change_phone_query);
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(change_phone_query)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
     public void changePhone(String newPhone, int id) throws SQLException {

        String change_phone_query = "UPDATE artist SET ArtistPhone= ? WHERE ArtistId= ? ;";
        System.out.println(change_phone_query);
        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(change_phone_query)) {
            stmt.setString(1, newPhone);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
    
       public boolean artistExists(String email) throws SQLException {
        String query = "select * from artist where ArtistEmail= ? ;";

        try (Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("no data");
                return false;
            }

            return true;
        }
    }
       public static ArtistBean login(ArtistBean bean) {

        //preparing some objects for connection 
        Statement stmt = null;

        String email = bean.getEmail();
        String passwordToHash = bean.getPass();
        SecurePassword s = new SecurePassword();

        String password = s.md5password(passwordToHash);
        String searchQuery = "select * from artist where ArtistEmail='"
                + email
                + "' AND ArtistPass='"
                + password
                + "'";

        // "System.out.println" prints in the console; Normally used to trace the process
        System.out.println("Your artist name is " + email);
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
                System.out.println("Sorry, you are not a registered artist! Please sign up first");
                bean.setValid(false);
            } //if user exists set the isValid variable to true
            else if (more) {
                int ArtistId = rs.getInt("ArtistId");
                String ArtistLogin = rs.getString("ArtistLogin");
                String ArtistPass = rs.getString("ArtistPass");
                String ArtistName = rs.getString("ArtistName");
                String ArtistFirstName = rs.getString("ArtistFirstName");
                String ArtistBirthdate = rs.getString("ArtistBirthdate");
                String ArtistEmail = rs.getString("ArtistEmail");
                String ArtistStageName = rs.getString("ArtistArtistName");
                int ArtistPhone = rs.getInt("ArtistPhone");
                
                System.out.println("Welcome " + ArtistFirstName);
                bean.setId(ArtistId);
                bean.setLogin(ArtistLogin);
                bean.setPass(ArtistPass);
                bean.setName(ArtistName);
                bean.setFirstName(ArtistFirstName);
                bean.setBirthDate(ArtistBirthdate);
                bean.setEmail(ArtistEmail);
                bean.setPhone(ArtistPhone);
                bean.setArtistName(ArtistStageName);
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
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
