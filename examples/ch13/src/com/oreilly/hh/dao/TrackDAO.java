package com.oreilly.hh.dao;

import java.sql.Time;
import java.util.List;

import com.oreilly.hh.data.Track;

public interface TrackDAO {
    public Track persist(Track track);
    public void delete(Track track);
    /**
     * Retrieve any tracks that fit in the specified amount of time.
     *
     * @param length the maximum playing time for tracks to be returned.
     * @param session the Hibernate session that can retrieve data.
     * @return a list of {@link Track}s meeting the length restriction.
     */
    public List<Track> tracksNoLongerThan(Time length);
}
