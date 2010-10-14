package com.oreilly.hh.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name="ARTIST")
@NamedQueries({
	@NamedQuery(name="com.oreilly.hh.artistByName", 
			    query="from Artist as artist where upper(artist.name) = upper(:name)")
})
public class Artist {

	@Id
	@Column(name="ARTIST_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="NAME",nullable=false,unique=true)
	@Index(name="ARTIST_NAME",columnNames={"NAME"})
	private String name;
	
	@ManyToMany
	@JoinTable(name="TRACK_ARTISTS", 
			   joinColumns={@JoinColumn(name="TRACK_ID")},
			   inverseJoinColumns={@JoinColumn(name="ARTIST_ID")})
    private Set<Track> tracks;
	
	@ManyToOne
	private Artist actualArtist;
			   
	public Artist() {}

	public Artist(String name, HashSet<Track> tracks, Artist actualArtist) {
	    this.name = name;
        this.tracks = tracks;
        this.actualArtist = actualArtist;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getActualArtist() {
		return actualArtist;
	}

	public void setActualArtist(Artist actualArtist) {
		this.actualArtist = actualArtist;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}
	
}
