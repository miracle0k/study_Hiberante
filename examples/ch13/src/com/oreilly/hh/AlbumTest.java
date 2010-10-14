package com.oreilly.hh;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.oreilly.hh.dao.AlbumDAO;
import com.oreilly.hh.dao.ArtistDAO;
import com.oreilly.hh.dao.TrackDAO;
import com.oreilly.hh.data.Album;
import com.oreilly.hh.data.AlbumTrack;
import com.oreilly.hh.data.Artist;
import com.oreilly.hh.data.SourceMedia;
import com.oreilly.hh.data.StereoVolume;
import com.oreilly.hh.data.Track;


/**
 * Create sample album data, letting Hibernate persist it for us.
 */
public class AlbumTest implements Test {

    private static Logger log = Logger.getLogger( AlbumTest.class );
    
    private AlbumDAO albumDAO;
    private ArtistDAO artistDAO;
    private TrackDAO trackDAO;
    
    public void run() {

            Artist artist = artistDAO.getArtist("Martin L. Gore", true);
            Album album = new Album("Counterfeit e.p.", 1,
                new HashSet<Artist>(), new HashSet<String>(),
                new ArrayList<AlbumTrack>(5), new Date());
            album.getArtists().add(artist);
            album = albumDAO.persist(album);
            log.info( "Persisted Album: " + album.getId() );
            
            addAlbumTrack(album, "Compulsion", "vol1/album83/track01.mp3",
                          Time.valueOf("00:05:29"), artist, 1, 1);
            addAlbumTrack(album, "In a Manner of Speaking",
                          "vol1/album83/track02.mp3", Time.valueOf("00:04:21"),
                          artist, 1, 2);
            addAlbumTrack(album, "Smile in the Crowd",
                          "vol1/album83/track03.mp3", Time.valueOf("00:05:06"),
                          artist, 1, 3);
            addAlbumTrack(album, "Gone", "vol1/album83/track04.mp3",
                          Time.valueOf("00:03:32"), artist, 1, 4);
            addAlbumTrack(album, "Never Turn Your Back on Mother Earth",
                          "vol1/album83/track05.mp3", Time.valueOf("00:03:07"),
                          artist, 1, 5);
            addAlbumTrack(album, "Motherless Child", "vol1/album83/track06.mp3",
                          Time.valueOf("00:03:32"), artist, 1, 6);
            album = albumDAO.persist( album );

            log.info("Saved an album named " + album.getTitle() );
            log.info("With " + album.getTracks().size() + " tracks." );
    }

    /**
     * Quick and dirty helper method to handle repetitive portion of creating
     * album tracks. A real implementation would have much more flexibility.
     */
    private void addAlbumTrack(Album album, String title, String file,
                                      Time length, Artist artist, int disc,
                                      int positionOnDisc)
    {
        Track track = new Track(title, file, length, new HashSet<Artist>(),
                                new Date(), new StereoVolume(), SourceMedia.CD,
                                new HashSet<String>());
        track.getArtists().add(artist);
        track = trackDAO.persist(track);
        
        album.getTracks().add(new AlbumTrack(track, disc, positionOnDisc));

    }

    public AlbumDAO getAlbumDAO() {
        return albumDAO;
    }

    public void setAlbumDAO(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public ArtistDAO getArtistDAO() {
        return artistDAO;
    }

    public void setArtistDAO(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    public TrackDAO getTrackDAO() {
        return trackDAO;
    }

    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
