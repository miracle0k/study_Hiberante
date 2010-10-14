package com.oreilly.hh.data;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Index;

/**
 * Represents a single playable track in the music database.
 *
 * @author Timothy O'Brien
 * @author Jim Elliott
 */
@Entity
@Table(name="TRACK")
@NamedQueries({
    @NamedQuery(name="com.oreilly.hh.tracksNoLongerThan",
                query="from Track as track where track.playTime <= :length")
})
public class Track {

    /**
     * The synthetic database key associated with this track once it is
     * persisted to a database.
     */
    @Id
    @Column(name="TRACK_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    /**
     * The title by which the track is known.
     */
    @Column(name="TITLE",nullable=false)
    @Index(name="TRACK_TITLE",columnNames={"TITLE"})
    private String title;
    
    /**
     * The location of the track's audio data within the filesystem.
     */
    @Column(nullable=false)
    private String filePath;

    /**
     * The playing time of the track.
     */
    @Temporal(TemporalType.TIME)
    private Date playTime;

    /**
     * The set of artists who were involved in performing the track.
     */
    @ManyToMany
    @JoinTable(name="TRACK_ARTISTS", 
               joinColumns={@JoinColumn(name="ARTIST_ID")},
               inverseJoinColumns={@JoinColumn(name="TRACK_ID")})
    private Set<Artist> artists;
               
    /**
     * When the track was added to the music database.
     */
    @Temporal(TemporalType.DATE)
    private Date added;
    
    /**
     * Any notes the user has made about the track.
     */
    @CollectionOfElements
    @JoinTable(name="TRACK_COMMENTS",
               joinColumns = @JoinColumn(name="TRACK_ID"))
    @Column(name="COMMENT")
    private Set<String> comments;

    /**
     * The type of media on which the track was originally obtained.
     */
    @Enumerated(EnumType.STRING)
    private SourceMedia sourceMedia;
    
    /**
     * How loudly the track should be played.
     */
    @Embedded
    @AttributeOverrides({
      @AttributeOverride(name = "left", column = @Column(name = "VOL_LEFT")),
      @AttributeOverride(name = "right", column = @Column(name = "VOL_RIGHT"))
    })
    StereoVolume volume;
    
    /**
     * Empty constructor required for this class to be a valid JavaBean.
     */
    public Track() {}

    /**
     * Minimal constructor that sets the properties which cannot be
     * {@code null} when persisting the track.
     *
     * @param title the title by which the track is known.
     * @param filePath the location of the audio data in the filesystem.
     */
    public Track(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    /**
     * Constructor that sets all properties.
     *
     * @param title the title by which the track is known.
     * @param filePath the location of the audio data in the filesystem.
     * @param playTime the playing time of the track.
     * @param artists the artists who were involved in performing the track.
     * @param added when the track was added to the music database.
     * @param volume how loudly the track should be played.
     * @param sourceMedia the type of media on which the track was obtained.
     * @param comments any notes the user has made about the track.
     */
    public Track(String title, String filePath, Time playTime,
                 Set<Artist> artists, Date added, StereoVolume volume,
                 SourceMedia sourceMedia, Set<String> comments) {
        this.title = title;
        this.filePath = filePath;
        this.playTime = playTime;
        this.artists = artists;
        this.added = added;
        this.volume = volume;
        this.sourceMedia = sourceMedia;
        this.comments = comments;
    }

    /**
     * Get the date at which the track was added to the music database.
     *
     * @return the date the track record was created.
     */
    public Date getAdded() {
        return added;
    }

    /**
     * Set the date at which the track was added to the music database.
     *
     * @param added the date the track record was created.
     */
    public void setAdded(Date added) {
        this.added = added;
    }

    /**
     * Get the location of the track's audio data within the filesystem.
     *
     * @return the path to the audio file.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the location of the track's audio data within the filesystem.
     *
     * @param filePath the path to the audio file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get the database key.
     *
     * @return the synthetic database key of the row in which this track
     *         is persisted.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the database key.
     *
     * @param id the synthetic database key of the row in which this track
     *           is persisted.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the playing time.
     *
     * @return the duration for which the track's audio plays.
     */
    public Date getPlayTime() {
        return playTime;
    }

    /**
     * Set the playing time.
     *
     * @param playTime the duration for which the track's audio plays.
     */
    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    /**
     * Get the title of the track.
     *
     * @return the title by which the track is known.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the track.
     *
     * @param title the title by which the track is known.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the artists associated with the track.
     *
     * @return the set of artists associated with the performance of the track.
     */
    public Set<Artist> getArtists() {
        return artists;
    }

    /**
     * Set the artists associated with the track.
     *
     * @param artists the set of artists associated with the performance of
     *                the track.
     */
    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    /**
     * Get the comments associated with the track.
     *
     * @return the notes the user has made about the track.
     */
    public Set<String> getComments() {
        return comments;
    }

    /**
     * Set the comments associated with the track.
     *
     * @param comments the notes the user has made about the track.
     */
    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    /**
     * Get the source media of the track.
     *
     * @return the type of media on which the track was originally obtained.
     */
    public SourceMedia getSourceMedia() {
        return sourceMedia;
    }

    /**
     * Set the source media of the track.
     *
     * @param sourceMedia the type of media on which the track was originally
     *                    obtained.
     */
    public void setSourceMedia(SourceMedia sourceMedia) {
        this.sourceMedia = sourceMedia;
    }

    /**
     * Get the volume of the track.
     *
     * @return the volume (and stereo pan) at which the track should be played.
     */
    public StereoVolume getVolume() {
        return volume;
    }

    /**
     * Set the volume of the track.
     *
     * @param volume the volume (and stereo pan) at which the track should be
     *               played.
     */
    public void setVolume(StereoVolume volume) {
        this.volume = volume;
    }

    /**
     * Produce a human-readable representation of the track.
     *
     * @return a textual description of the track.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@");
        builder.append(Integer.toHexString(hashCode())).append(" [");
        builder.append("title").append("='").append(getTitle()).append("' ");
        builder.append("volume").append("='").append(getVolume()).append("' ");
        builder.append("sourceMedia").append("='").append(getSourceMedia());
        builder.append("' ").append("]");
        return builder.toString();
     }
}
