package com.oreilly.hh.data;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class AlbumTrack {

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="TRACK_ID")
    private Track track;
    
    private Integer disc;
    private Integer positionOnDisc;
    
    public AlbumTrack() {}

    public AlbumTrack(Track track, Integer disc, Integer positionOnDisc) {
        this.track = track;
        this.disc = disc;
        this.positionOnDisc = positionOnDisc;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Integer getDisc() {
        return disc;
    }

    public void setDisc(Integer disc) {
        this.disc = disc;
    }

    public Integer getPositionOnDisc() {
        return positionOnDisc;
    }

    public void setPositionOnDisc(Integer positionOnDisc) {
        this.positionOnDisc = positionOnDisc;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@");
        builder.append(Integer.toHexString(hashCode())).append(" [");
        builder.append("track").append("='").append(getTrack()).append("' ");
        builder.append("]");
        return builder.toString();
    }
}
