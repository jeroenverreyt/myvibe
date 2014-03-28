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
public class TrackDao {

    private String url;
    private String user;
    private String password;
    private static final String GET_QUERY = "select TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID from track";
    private static final String ADD_QUERY = "INSERT INTO track (TrackName, TrackPrice, TrackAudioFile,Artist_ArtistID) values (?, ?, ?, 1)";
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

    public List<TrackBean> getTracks() throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> tracks = new ArrayList<TrackBean>();
            ResultSet rs = stmt.executeQuery(GET_QUERY);
            while (rs.next()) {
                TrackBean track = new TrackBean(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4));
                tracks.add(track);
            }
            return tracks;
        }
    }

    public Boolean addTrack(String trackName, String trackPrice, InputStream inputStream) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                ) {
            Boolean success;
            try {
                PreparedStatement statement = con.prepareStatement(ADD_QUERY);
                statement.setString(1, trackName);
                statement.setString(2, trackPrice);

                if (inputStream != null) {
                    // fetches input stream of the upload file for the blob column
                    statement.setBlob(3, inputStream);
                }
                statement.executeUpdate();
                success = true;
            } catch (SQLException ex) {
                success = false;
            }
            return success;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
