package com.oreilly.hh.data;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionOfElements;

@Entity
@Table(name="TRACK")
@NamedQueries({
	@NamedQuery(name="com.oreilly.hh.tracksNoLongerThan",
				query="from Track as track where track.playTime <= :length")
})
public class Track {

	@Id
	@Column(name="TRACK_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="TITLE",nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String filePath;

	@Temporal(TemporalType.TIME)
	private Date playTime;

    @ManyToMany
	@JoinTable(name="TRACK_ARTISTS", 
		       joinColumns={@JoinColumn(name="ARTIST_ID")},
			   inverseJoinColumns={@JoinColumn(name="TRACK_ID")})
    private Set<Artist> artists;
			   
	@Temporal(TemporalType.TIMESTAMP)
	private Date added;
	
    @CollectionOfElements
    @JoinTable(
            name="TRACK_COMMENTS",
            joinColumns = @JoinColumn(name="TRACK_ID"))
    @Column(name="COMMENT")
	private Set<String> comments;

    @Enumerated(EnumType.STRING)
    private SourceMedia sourceMedia;
	
    @Embedded
    StereoVolume volume;
    
	public Track() {}

    public Track(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
    }

    public Track(String title, String file, Time playTime, HashSet<Artist> artists, Date added, StereoVolume volume, SourceMedia sourceMedia, HashSet<String> comments) {
        this.title = title;
        this.filePath = file;
        this.playTime = playTime;
        this.artists = artists;
        this.added = added;
        this.volume = volume;
        this.sourceMedia = sourceMedia;
        this.comments = comments;
    }

    public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Artist> getArtists() {
		return artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}

    public Set<String> getComments() {
        return comments;
    }

    public void setComments(Set<String> comments) {
        this.comments = comments;
    }

    public SourceMedia getSourceMedia() {
        return sourceMedia;
    }

    public void setSourceMedia(SourceMedia sourceMedia) {
        this.sourceMedia = sourceMedia;
    }

    public StereoVolume getVolume() {
        return volume;
    }

    public void setVolume(StereoVolume volume) {
        this.volume = volume;
    }
}
