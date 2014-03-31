/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.Serializable;

/**
 *
 * @author Jeroen
 */
public class TracksperuserBean implements Serializable {

    private int trackid;
    private int userid;

    public TracksperuserBean() {
    }

    public TracksperuserBean(int trackid, int userid) {
        this.trackid = trackid;
        this.userid = userid;
    }

    public int getTrackid() {
        return trackid;
    }

    public void setTrackid(int trackid) {
        this.trackid = trackid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
