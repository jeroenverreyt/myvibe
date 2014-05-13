/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author Jeroen
 */
public class TrackBean implements Serializable{
    private int trackid;
    private String trackname;
    private String trackreleasedate;
    private int trackprice;
    private int artist_artistid;
    private Blob trackaudiofile;

    public TrackBean() {
    }

    public TrackBean(String trackname, String trackreleasedate, int trackprice, int artist_artistid, Blob trackaudiofile) {
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
        this.trackaudiofile = trackaudiofile;
    }

    public Blob getTrackaudiofile() {
        return trackaudiofile;
    }

    public void setTrackaudiofile(Blob trackaudiofile) {
        this.trackaudiofile = trackaudiofile;
    }

    public TrackBean(int trackid, String trackname, String trackreleasedate, int trackprice, int artist_artistid) {
        this.trackid = trackid;
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
    }

    public int getTrackid() {
        return trackid;
    }

    public void setTrackid(int trackid) {
        this.trackid = trackid;
    }

    public String getTrackname() {
        return trackname;
    }

    public void setTrackname(String trackname) {
        this.trackname = trackname;
    }

    public String getTrackreleasedate() {
        return trackreleasedate;
    }

    public void setTrackreleasedate(String trackreleasedate) {
        this.trackreleasedate = trackreleasedate;
    }

    public int getTrackprice() {
        return trackprice;
    }

    public void setTrackprice(int trackprice) {
        this.trackprice = trackprice;
    }

    public int getArtist_artistid() {
        return artist_artistid;
    }

    public void setArtist_artistid(int artist_artistid) {
        this.artist_artistid = artist_artistid;
    }

}
