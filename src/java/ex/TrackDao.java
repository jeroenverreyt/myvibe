/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
    private static final String GETBYID_QUERY = "select TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID, TrackAudioFile, TrackFileName, ArtistArtistName from track, artist where TrackID = ? and Artist_ArtistID = ArtistID";
    private static final String ADD_QUERY = "INSERT INTO track (TrackName, TrackReleaseDate, TrackType, TrackPrice, TrackAudioFile,TrackFileName, Artist_ArtistID) values (?, ?,?, ?, ?, ?, 2)";
    private static final String GET_TOP10_UPLOADED = "SELECT TrackID, TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID FROM track order by TrackCounter desc LIMIT 10";
    private static final String GET_MOST_RECENT = "SELECT TrackID, TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID FROM track order by TrackReleaseDate desc Limit 10;";
    private static final String UPDATE_COUNTER = "UPDATE track SET TrackCounter = TrackCounter + 1 WHERE TrackID = ?;";
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

    public List<TrackBean> getTracksPerArtist(int artistId, String sort, String sortdirection) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> tracks = new ArrayList<TrackBean>();
           
            String get_query_artist = "select TrackID, TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID, TrackCounter from track where Artist_ArtistID = ? order by " + sort + " " + sortdirection;
            PreparedStatement statement = con.prepareStatement(get_query_artist);
            statement.setInt(1, artistId);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            while (rs.next()) {
                TrackBean track = new TrackBean(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                tracks.add(track);
            }
            return tracks;
        }

    }

    public List<TrackBean> getTracks(String sort, String sortdirection) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> tracks = new ArrayList<TrackBean>();
           String query = "select TrackID, TrackName, TrackReleaseDate, TrackPrice, Artist_ArtistID from track order by " + sort + " " + sortdirection;

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                TrackBean track = new TrackBean(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                tracks.add(track);
            }
            return tracks;
        }

    }

    public String getTrackName(int trackId) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> tracks = new ArrayList<TrackBean>();
            PreparedStatement statement = con.prepareStatement(GETBYID_QUERY);
            statement.setInt(1, trackId);

            ResultSet rs = statement.executeQuery();
            String trackname = "";
            while (rs.next()) {
                trackname = rs.getString(1);

            }
            return trackname;
        }
    }

    public int getTrackArtistId(int trackId) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> tracks = new ArrayList<TrackBean>();
            PreparedStatement statement = con.prepareStatement(GETBYID_QUERY);
            statement.setInt(1, trackId);
            ResultSet rs = statement.executeQuery();
            int artistId = 0;
            while (rs.next()) {
                artistId = rs.getInt(4);

            }
            return artistId;
        }
    }

    public List<TrackBean> getTopUploaded() throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> topTracksUploaded = new ArrayList<TrackBean>();
            ResultSet rs = stmt.executeQuery(GET_TOP10_UPLOADED);
            while (rs.next()) {
                TrackBean track = new TrackBean(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                topTracksUploaded.add(track);
            }
            return topTracksUploaded;
        }
    }

    public List<TrackBean> getMostRecent() throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {
            List<TrackBean> mostRecent = new ArrayList<TrackBean>();
            ResultSet rs = stmt.executeQuery(GET_MOST_RECENT);
            while (rs.next()) {
                TrackBean track = new TrackBean(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                mostRecent.add(track);
            }
            return mostRecent;
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
            String TrackAudioFile = rs.getString(5);
            String TrackFileName = "songs/" + rs.getString(6);
            String ArtistArtistName = rs.getString(7);
            track.setTrackname(TrackName);
            track.setTrackreleasedate(TrackReleaseDate);
            track.setTrackprice(TrackPrice);
            track.setArtist_artistid(Artist_ArtistID);
            track.setTrackaudiofile(TrackAudioFile);
            track.setTrackfilename(TrackFileName);
            track.setArtistname(ArtistArtistName);
            return track;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void UpdateCounter(String trackid) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                ) {
            try {
                PreparedStatement statement = con.prepareStatement(UPDATE_COUNTER);
                statement.setString(1, trackid);
                statement.executeUpdate();
            } catch (SQLException ex) {
            }
        }
    }

    Boolean addTrack(String trackName, String trackPrice, String trackReleaseDate, String trackType, String trackAudioFilePath, String trackFileName) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                ) {
            Boolean success;
            try {
                //TrackName, TrackReleaseDate, TrackType, TrackPrice, TrackAudioFile
                PreparedStatement statement = con.prepareStatement(ADD_QUERY);
                statement.setString(1, trackName);
                statement.setString(2, trackReleaseDate);
                statement.setString(3, trackType);
                statement.setString(4, trackPrice);
                statement.setString(5, trackAudioFilePath);
                statement.setString(6, trackFileName);
                System.out.println(statement);

                statement.executeUpdate();
                success = true;
            } catch (SQLException ex) {
                success = false;
            }
            return success;
        }
    }
}
