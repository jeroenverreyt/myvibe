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
          
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
