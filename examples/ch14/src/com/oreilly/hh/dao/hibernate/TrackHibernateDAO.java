package com.oreilly.hh.dao.hibernate;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oreilly.hh.dao.TrackDAO;
import com.oreilly.hh.data.Track;

public class TrackHibernateDAO extends HibernateDaoSupport implements TrackDAO {

    public Track persist(Track track) {
        track = (Track) getHibernateTemplate().merge(track);
        getSession().flush();
        return track;
    }
    
    public void delete(Track track) {
        getHibernateTemplate().delete(track);
    }

    /**
     * Retrieve any tracks that fit in the specified amount of time.
     *
     * @param length the maximum playing time for tracks to be returned.
     * @param session the Hibernate session that can retrieve data.
     * @return a list of {@link Track}s meeting the length restriction.
     */
    public List<Track> tracksNoLongerThan(Time length) {
        Query query = getSession().getNamedQuery(
                          "com.oreilly.hh.tracksNoLongerThan");
        query.setTime("length", length);
        return new ArrayList<Track>( query.list() );
    }   

}
