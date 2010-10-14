package com.oreilly.hh.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="ALBUM")
public class Album {

	@Id
	@Column(name="ALBUM_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="ALBUM_TITLE",nullable=false)
	private String title;

	@Column(nullable=false)
	private Integer numDiscs;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ALBUM_ARTISTS", 
			   joinColumns={@JoinColumn(name="ARTIST_ID")},
			   inverseJoinColumns={@JoinColumn(name="ALBUM_ID")})
	private Set<Artist> artists;
	
    @CollectionOfElements
    @JoinTable(
            name="ALBUM_COMMENTS",
            joinColumns = @JoinColumn(name="ALBUM_ID"))
    @Column(name="COMMENT")
	private Set<String> comments;
	
	private Date added;
	
	@OneToMany(cascade=CascadeType.ALL)
	@IndexColumn(name="list_pos", nullable=false, base=0)
	@JoinColumn(name="track_id", nullable=false)
	private List<AlbumTrack> tracks;
	
	public Album() {}

	public Album(String title, int numDiscs, HashSet<Artist> artists, HashSet<String> comments, ArrayList<AlbumTrack> tracks, Date added) {
	    this.title = title;
        this.numDiscs = numDiscs;
        this.artists = artists;
        this.comments = comments;
        this.tracks = tracks;
        this.added = added;
    }

    public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumDiscs() {
		return numDiscs;
	}

	public void setNumDiscs(Integer numDiscs) {
		this.numDiscs = numDiscs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AlbumTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<AlbumTrack> tracks) {
		this.tracks = tracks;
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

}
