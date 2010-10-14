package com.oreilly.hh;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import com.oreilly.hh.data.*;

import java.sql.Time;
import java.util.*;

/**
 * Retrieve data as objects
 */
public class QueryTest {

    /**
     * Retrieve any tracks that fit in the specified amount of time.
     *
     * @param length the maximum playing time for tracks to be returned.
     * @param session the Hibernate session that can retrieve data.
     * @return a list of {@link Track}s meeting the length restriction.
     */
    public static List tracksNoLongerThan(Time length, Session session) {
        Query query = session.getNamedQuery(
                          "com.oreilly.hh.tracksNoLongerThan");
        query.setTime("length", length);
        return query.list();
    }   

    /**
     * Build a parenthetical, comma-separated list of artist names.
     * @param artists the artists whose names are to be displayed.
     * @return the formatted list, or an empty string if the set was empty.
     */
    public static String listArtistNames(Set<Artist> artists) {
        StringBuilder result = new StringBuilder();
        for (Artist artist : artists) {
            result.append((result.length() == 0) ? "(" : ", ");
            result.append(artist.getName());
        }
        if (result.length() > 0) {
            result.append(") ");
        }
        return result.toString();
    }

    /**
     * Print summary information about tracks associated with an artist.
     *
     * @param artist the artist in whose tracks we're interested.
     * @param session the Hibernate session that can retrieve data.
     **/
    public static void printTrackSummary(Artist artist, Session session) {
        Query query = session.getNamedQuery("com.oreilly.hh.trackSummary");
        query.setParameter("artist", artist);
        Object[] results = (Object[])query.uniqueResult();
        System.out.println("Summary of tracks by " + artist.getName() + ':');
        System.out.println("       Total tracks: " + results[0]);
        System.out.println("     Shortest track: " + results[1]);
        System.out.println("      Longest track: " + results[2]);
    }

    /**
     * Print tracks that end some number of seconds into their final minute.
     *
     * @param seconds, the number of seconds at which we want tracks to end.
     * @param session the Hibernate session that can retrieve data.
     **/
    public static void printTracksEndingAt(int seconds, Session session) {
        Query query = session.getNamedQuery("com.oreilly.hh.tracksEndingAt");
        query.setInteger("seconds", seconds);
        List results = query.list();
        for (ListIterator iter = results.listIterator() ; iter.hasNext() ; ) {
            Track track = (Track)iter.next();
            System.out.println("Track: " + track.getTitle() +
                               ", length=" + track.getPlayTime());
        }
    }

    /**
     * Look up and print some tracks when invoked from the command line.
     */
    public static void main(String args[]) throws Exception {
        // Create a configuration based on the XML file we've put
        // in the standard place.
        Configuration config = new Configuration();
        config.configure();

        // Get the session factory we can use for persistence
        SessionFactory sessionFactory = config.buildSessionFactory();

        // Ask for a session using the JDBC information we've configured
        Session session = sessionFactory.openSession();
        try {
            // Print IDs and titles of tracks that will fit in five minutes
            List titles = tracksNoLongerThan(Time.valueOf("00:05:00"),
                                             session);
            for (ListIterator iter = titles.listIterator() ;
                 iter.hasNext() ; ) {
                Object[] row = (Object[])iter.next();
                Integer id = (Integer)row[0];
                String title = (String)row[1];
                System.out.println("Track: " + title + " [ID=" + id + ']');
            }
            printTrackSummary(CreateTest.getArtist("Samuel Barber",
                                                   false, session), session);
            System.out.println("Tracks ending halfway through final minute:");
            printTracksEndingAt(30, session);
        } finally {
            // No matter what, close the session
            session.close();
        }

        // Clean up after ourselves
        sessionFactory.close();
    }
}
