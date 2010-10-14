package com.oreilly.hh.data;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;

/**
 * Represents an album in the music database, an organized list of tracks.
 *
 * @author Timothy O'Brien
 * @author Jim Elliott
 */
@Entity
@Table(name="ALBUM")
public class Album {

    /**
     * The synthetic database key associated with this album once it is
     * persisted to a database.
     */
    @Id
    @Column(name="ALBUM_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    /**
     * The title by which the album is known.
     */
    @Column(name="TITLE",nullable=false)
    @Index(name="ALBUM_TITLE",columnNames={"TITLE"})
    private String title;

    /**
     * The number of discs which make up the album.
     */
    private Integer numDiscs;

    /**
     * The artists associated with the album.
     */
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ALBUM_ARTISTS", 
               joinColumns=@JoinColumn(name="ARTIST_ID"),
               inverseJoinColumns=@JoinColumn(name="ALBUM_ID"))
    private Set<Artist> artists;
    
    /**
     * The notes made by the user about the album.
     */
    @CollectionOfElements
    @JoinTable(name="ALBUM_COMMENTS",
               joinColumns = @JoinColumn(name="ALBUM_ID"))
    @Column(name="COMMENT")
    private Set<String> comments;
    
    /**
     * When the album was added to the music database.
     */
    @Temporal(TemporalType.DATE)
    private Date added;
    
    /**
     * The tracks that make up the album.
     */
    @CollectionOfElements
    @IndexColumn(name="LIST_POS")
    @JoinTable(name="ALBUM_TRACKS",
               joinColumns = @JoinColumn(name="ALBUM_ID"))
    private List<AlbumTrack> tracks;
    
    /**
     * Empty constructor required for this class to be a valid JavaBean.
     */
    public Album() {}

    /**
     * Constructor which sets all properties of the album.
     *
     * @param title the title by which the album is known.
     * @param numDiscs the number of discs which make up the album.
     * @param artists the artists associated with the album.
     * @param comments the notes made by the user about the album.
     * @param tracks the tracks which make up the album.
     * @param added when the album was added to the music database.
     */
    public Album(String title, int numDiscs, Set<Artist> artists,
                 Set<String> comments, List<AlbumTrack> tracks,
                 Date added) {
        this.title = title;
        this.numDiscs = numDiscs;
        this.artists = artists;
        this.comments = comments;
        this.tracks = tracks;
        this.added = added;
    }

    /**
     * Get the date at which the album was added to the music database.
     *
     * @return the date the album record was created.
     */
    public Date getAdded() {
        return added;
    }

    /**
     * Set the date at which the album was added to the music database.
     *
     * @param added the date the album record was created.
     */
    public void setAdded(Date added) {
        this.added = added;
    }

    /**
     * Get the database key.
     *
     * @return the synthetic database key of the row in which this album
     *         is persisted.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the database key.
     *
     * @param id the synthetic database key of the row in which this album
     *         is persisted.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the number of discs in the album.
     *
     * @return the number of discs that make up the album.
     */
    public Integer getNumDiscs() {
        return numDiscs;
    }

    /**
     * Set the number of discs in the album.
     *
     * @param numDiscs the number of discs that make up the album.
     */
    public void setNumDiscs(Integer numDiscs) {
        this.numDiscs = numDiscs;
    }

    /**
     * Get the album's title.
     *
     * @return the title by which the album is known.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the album's title.
     *
     * @param title the title by which the album is known.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the album's tracks.
     *
     * @return the tracks that make up the album.
     */
    public List<AlbumTrack> getTracks() {
        return tracks;
    }

    /**
     * Set the album's tracks.
     *
     * @param tracks the tracks that make up the album.
     */
    public void setTracks(List<AlbumTrack> tracks) {
        this.tracks = tracks;
    }

    /**
     * Get the album's artists.
     *
     * @return the artists associated with the album itself, as opposed to the
     *         individual tracks (for example a DJ who produced a continuous
     *         mix).
     */
    public Set<Artist> getArtists() {
        return artists;
    }

    /**
     * Set the album's artists.
     *
     * @param artists the artists associated with the album itself, as opposed
     *         to the individual tracks (for example a DJ who produced a
     *         continuous mix).
     */
    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    /**
     * Get the comments associated with the album.
     *
     * @return the notes the user has made about the album.
     */
    public Set<String> getComments() {
        return comments;
    }

    /**
     * Set the comments associated with the album.
     *
     * @param comments the notes the user has made about the album.
     */
    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    /**
     * Produce a human-readable representation of the album.
     *
     * @return a textual description of the album.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@");
        builder.append(Integer.toHexString(hashCode())).append(" [");
        builder.append("title").append("='").append(getTitle()).append("' ");
        builder.append("tracks").append("='").append(getTracks()).append("' ");
        builder.append("]");
        return builder.toString();
    }
}
