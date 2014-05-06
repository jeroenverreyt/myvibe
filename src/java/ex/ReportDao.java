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


public class ReportDao {

    private String url;
    private String user;
    private String password;
    private static final String GET_FIELDS = "select * from information_schema.columns where table_schema = 'myvibe10' AND column_name NOT LIKE '%Pass' AND column_name NOT LIKE '%ID' order by table_name,ordinal_position";
    private static final String GET_TABLE= "SELECT DISTINCT TABLE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = ? AND TABLE_SCHEMA='myvibe10'";
  
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

    
    public List<String> getFields() throws SQLException {
	try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_FIELDS)) {
	    List<String> fields = new ArrayList<>();
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
		fields.add(rs.getString(4));
	    }
	    return fields;
	}
    }
       public String getTableName(String column) throws SQLException {
	try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(GET_TABLE)) {
	    stmt.setString(1, column);
	    ResultSet rs = stmt.executeQuery();
	    rs.next();
	    return rs.getString(1);
	}
    }
     public List<String> getData(String column, String table) throws SQLException {
        String get_data = "Select " + column + " from  " + table ;
         try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(get_data)) {
	
             
            List<String> data = new ArrayList<>();
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
		data.add(rs.getString(1));
	    }
	    return data;
	}
    }
        private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
