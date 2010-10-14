package com.oreilly.hh.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Index;

/**
 * Represents an artist who is associated with a track or album.
 *
 * @author Timothy O'Brien
 * @author Jim Elliott
 */
@Entity
@Table(name="ARTIST")
@NamedQueries({
    @NamedQuery(name="com.oreilly.hh.artistByName", 
        query="from Artist as artist where upper(artist.name) = upper(:name)")
})
public class Artist {

    /**
     * The synthetic database key associated with this artist once it is
     * persisted to a database.
     */
    @Id
    @Column(name="ARTIST_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    /**
     * The name by which this artist is known.
     */
    @Column(name="NAME",nullable=false,unique=true)
    @Index(name="ARTIST_NAME",columnNames={"NAME"})
    private String name;
    
    /**
     * All tracks by this artist which are found in the database.
     */
    @ManyToMany
    @JoinTable(name="TRACK_ARTISTS", 
               joinColumns={@JoinColumn(name="TRACK_ID")},
               inverseJoinColumns={@JoinColumn(name="ARTIST_ID")})
    private Set<Track> tracks;
    
    /**
     * If not {@code null}, this record is an "alias", an alternate name by
     * which the artist is knows, and the value is the ID of the canonical
     * Artist record which should be used instead.
     */
    @ManyToOne
    @JoinColumn(name="actualArtist")
    private Artist actualArtist;
               
    /**
     * Empty constructor required for this class to be a valid JavaBean.
     */
    public Artist() {}

    /**
     * Create a new Artist.
     *
     * @param name the name by which the artist is known.
     * @param tracks the set of tracks by this artist.
     * @param actualArtist if not {@code null}, this object represents an
     *                     alternate name by which the artist is known, and
     *                     {@code actualArtist} is the canonical Artist object
     *                     that should always be used instead.
     */
    public Artist(String name, Set<Track> tracks, Artist actualArtist) {
        this.name = name;
        this.tracks = tracks;
        this.actualArtist = actualArtist;
    }

    /**
     * Get the database key.
     *
     * @return the synthetic database key of the row in which this artist
     *         is persisted.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the database key.
     *
     * @param id the synthetic database key of the row in which this artist
     *           is persisted.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the name by which this artist is known.
     *
     * @return the name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name by which this artist is known.
     *
     * @param name the name of the artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the canonical Artist object for which this instance is a mere
     * alternate name.
     *
     * @return {@code null} if this is not an alias for another artist
     *         instance, or the actual artist object if it is.
     */
    public Artist getActualArtist() {
        return actualArtist;
    }

    /**
     * Set the canonical Artist object for which this instance is a mere
     * alternate name.
     *
     * @param actualArtist {@code null} if this is not an alias for another
     *         artist instance, or the actual artist object if it is.
     */
    public void setActualArtist(Artist actualArtist) {
        this.actualArtist = actualArtist;
    }

    /**
     * Get the tracks associated with this artist.
     *
     * @return the set of tracks in the database by this artist.
     */
    public Set<Track> getTracks() {
        return tracks;
    }

    /**
     * Set the tracks associated with this artist.
     *
     * @param tracks the set of tracks in the database by this artist.
     */
    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * Produce a human-readable representation of the artist.
     *
     * @return a textual description of the artist.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getClass().getName()).append("@");
        builder.append(Integer.toHexString(hashCode())).append(" [");
        builder.append("name").append("='").append(getName()).append("' ");
        builder.append("actualArtist").append("='").append(getActualArtist());
        builder.append("' ").append("]");
      
        return builder.toString();
    }

}
