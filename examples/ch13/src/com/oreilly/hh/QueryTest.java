package com.oreilly.hh;

import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;

import com.oreilly.hh.dao.TrackDAO;
import com.oreilly.hh.data.Track;

/**
 * Retrieve data as objects
 */
public class QueryTest implements Test {

    private static Logger log = Logger.getLogger(QueryTest.class);

    private TrackDAO trackDAO;

    public void run() {
        // Print the tracks that will fit in five minutes
        List<Track> tracks = trackDAO.tracksNoLongerThan(
        		Time.valueOf("00:05:00"));
        for (Track track : tracks) {
            log.info("Track: \"" + track.getTitle() + "\", "
                    + track.getPlayTime());
        }
    }

    public TrackDAO getTrackDAO() {
        return trackDAO;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
