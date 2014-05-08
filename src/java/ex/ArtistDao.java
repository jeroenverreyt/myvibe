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
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
