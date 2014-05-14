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
public class TracksperuserDao {

    private static final String GET_USER_TRACKS = "SELECT TrackID, UserID  FROM tracksperuser where UserID = ?;";
    private static final String ADD_TRACK = "INSERT INTO tracksperuser (TrackID, UserID)  VALUES (?,?);";
    private String url;
    private String user;
    private String password;
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

    public List<TracksperuserBean> getTracks(int userId) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                Statement stmt = con.createStatement()) {

            List<TracksperuserBean> tracksperuser = new ArrayList<TracksperuserBean>();
            PreparedStatement statement = con.prepareStatement(GET_USER_TRACKS);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TracksperuserBean track = new TracksperuserBean(rs.getInt(1),
                        rs.getInt(2));
                tracksperuser.add(track);
            }
            return tracksperuser;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void AddTrack(String trackid, int userid) throws SQLException {
        try (Connection con = getConnection(); // Java 7 !!!
                ) {
            try {
                PreparedStatement statement = con.prepareStatement(ADD_TRACK);
                statement.setString(1, trackid);
                statement.setInt(2, userid);
                statement.executeUpdate();
            } catch (SQLException ex) {
            }
        }
    }
}
