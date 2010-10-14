package com.oreilly.hh.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
	
@Entity
@Table(name="ALBUM_TRACKS")
public class AlbumTrack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Album album;
	
	@ManyToOne
	private Track track;
	
	private Short disc;
	private Short positionOnDisc;
	
	public AlbumTrack() {}

	public AlbumTrack(Album album, Track track, Short disc, Short positionOnDisc) {
		this.track = track;
		this.album = album;
		this.disc = disc;
        this.positionOnDisc = positionOnDisc;
    }

    public Short getDisc() {
		return disc;
	}


	public void setDisc(Short disc) {
		this.disc = disc;
	}


	public Short getPositionOnDisc() {
		return positionOnDisc;
	}


	public void setPositionOnDisc(Short positionOnDisc) {
		this.positionOnDisc = positionOnDisc;
	}


	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

}
