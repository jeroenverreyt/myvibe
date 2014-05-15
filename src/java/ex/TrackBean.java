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
    private String trackaudiofile;
    private String trackfilename;
    private int trackcounter;
    private String artistname;
    public TrackBean() {
    }

    public TrackBean(String trackname, String trackreleasedate, int trackprice, int artist_artistid, String trackaudiofile) {
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
        this.trackaudiofile = trackaudiofile;
    }

    public TrackBean(int trackid, String trackname, String trackreleasedate, int trackprice, int artist_artistid, String trackaudiofile, String trackfilename, int trackcounter) {
        this.trackid = trackid;
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
        this.trackaudiofile = trackaudiofile;
        this.trackfilename = trackfilename;
        this.trackcounter = trackcounter;
    }

    public String getTrackaudiofile() {
        return trackaudiofile;
    }

    public String getTrackfilename() {
        return trackfilename;
    }

    public void setTrackfilename(String trackfilename) {
        this.trackfilename = trackfilename;
    }

    public void setTrackaudiofile(String trackaudiofile) {
        this.trackaudiofile = trackaudiofile;
    }

    public TrackBean(int trackid, String trackname, String trackreleasedate, int trackprice, int artist_artistid) {
        this.trackid = trackid;
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
    }
     public TrackBean(int trackid, String trackname, String trackreleasedate, int trackprice, int artist_artistid, int trackcounter) {
        this.trackid = trackid;
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
        this.trackcounter = trackcounter;
    }

         public TrackBean(int trackid, String trackname, String trackreleasedate, int trackprice, int artist_artistid, int trackcounter, String artistname) {
        this.trackid = trackid;
        this.trackname = trackname;
        this.trackreleasedate = trackreleasedate;
        this.trackprice = trackprice;
        this.artist_artistid = artist_artistid;
        this.trackcounter = trackcounter;
        this.artistname = artistname;
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

    public int getTrackcounter() {
        return trackcounter;
    }

    public void setTrackcounter(int trackcounter) {
        this.trackcounter = trackcounter;
    }

    public String getArtistname() {
        return artistname;
    }

    public void setArtistname(String artistname) {
        this.artistname = artistname;
    }
    

}
