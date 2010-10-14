package com.oreilly.hh.dao;

import com.oreilly.hh.data.Artist;

/**
 * Provides persistence operations for the Artist object
 */
public interface ArtistDAO {

    /**
     * Persist an Artist instance (create or update)
     * depending on the value of the id
     */
    public Artist persist(Artist artist);

    /**
     * Remove an Artist from the database
     */
    public void delete(Artist artist);

    /**
     * Return an Artist that matches the name argument
     */
    public Artist uniqueByName(String name);

    /**
     * Returns the matching Artist object.   If the 
     * create parameter is true, this method will
     * insert a new Artist and return the newly created
     * Artist object.
     */
    public Artist getArtist(String name, boolean create);
}
