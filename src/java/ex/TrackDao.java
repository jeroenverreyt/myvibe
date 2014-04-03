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
    private static final String GET_QUERY = "select TrackID, TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID from track";
    private static final String GETBYID_QUERY = "select TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID from track where TrackID = ?";
    private static final String ADD_QUERY = "INSERT INTO track (TrackName, TrackPrice, TrackAudioFile,Artist_ArtistID) values (?, ?, ?, 2)";
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
                TrackBean track = new TrackBean(rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                tracks.add(track);
            }
            return tracks;
        }
    }

    public TrackBean getTrackByID(String tracknr) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            PreparedStatement statement = con.prepareStatement(GETBYID_QUERY);
            statement.setString(1, tracknr);
            ResultSet rs = statement.executeQuery();
            rs.next();
            TrackBean track = new TrackBean();
            String TrackName = rs.getString(1);
            String TrackReleaseDate = rs.getString(2);
            int TrackPrice = rs.getInt(3);
            int Artist_ArtistID = rs.getInt(4);
            track.setTrackname(TrackName);
            track.setTrackreleasedate(TrackReleaseDate);
            track.setTrackprice(TrackPrice);
            track.setArtist_artistid(Artist_ArtistID);
            return track;
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
