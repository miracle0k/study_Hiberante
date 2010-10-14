package com.oreilly.hh.web;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.*;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.*;

import com.oreilly.hh.dao.AlbumDAO;
import com.oreilly.hh.data.Album;

/**
 * Class that implements the web based front end of our Jukebox.
 *
 */
public class AlbumActionBean implements ActionBean {
    /**
     * Logger
     */
    private static Logger log = Logger.getLogger(AlbumActionBean.class);
    /**
     * The ActionBeanContext provided to this class by Stripes DispatcherServlet.
     */
    private ActionBeanContext context;
    /**
     * The list of Album objects we will display on the Album list page.
     */
    private List<Album> albums;
    /**
     * The Album we are providing a form for on the edit page.
     */
    private Album album;
    /**
     * The Data Access Object for our Albums.
     */
    private AlbumDAO albumDAO;
    /**
     * Comment to add to the album
     */
    private String comment;
    
    
    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext aContext) {
        context = aContext;
    }

    /**
     * The default event handler that displays a list of Albums.
     * @return a forward to the Album list jsp.
     */
    @DefaultHandler
    public Resolution list() {
        albums = albumDAO.list();
        return new ForwardResolution("/albums/list.jsp");
    }
    
    /**
     * The event handler for handling edits to an Album
     * @return a forward to the Album edit jsp.
     */
    @LoadBean("album")
    public Resolution edit() {
        log.debug("Editing album: " + album);
        return new ForwardResolution("/albums/edit.jsp");
    }

    /**
     * The event handler for saving an Album.
     * @return a redirect to the Album list jsp.
     */
    @LoadBean("album")
    public Resolution save() {
        albumDAO.persist(album);
        log.debug("Redirecting to list!");
        return new RedirectResolution("/albums/list.jsp");
    }
    
    /**
     * A getter for the view to retrieve the list of Albums.
     * @return a list of Albums
     */
    public List<Album> getAlbums() {
        return albums;
    }
    
    
    /**
     * A setter for the DispatcherServlet to call that provides the album to save.
     * @param anAlbum
     */
    @ValidateNestedProperties({
        @Validate(field = "title", required= true, on = {"save"} ),
        @Validate(field = "numDiscs", required= true, on = {"save"})
    })
    public void setAlbum(Album anAlbum) {
        log.debug("setAlbum");
        album = anAlbum;
    }
    
    /**
     * A getter for the edit view to call.
     * @return an Album
     */
    public Album getAlbum() {
        return album;
    }
    
    /**
     * A method Spring will call that provides this class with an AlbumDAO instance.
     * @param anAlbumDAO The AlbumDAO object
     */
    @SpringBean("albumDAO")
    public void injectAlbumDAO(AlbumDAO anAlbumDAO) {
        this.albumDAO = anAlbumDAO;
    }

    /**
     * Event handler to save a comment to the Album
     * @return redirect to the edit Album page.
     */
    public Resolution saveComment() {
        Album a = albumDAO.get(album.getId());
        a.getComments().add(comment);
        albumDAO.persist(a);
        RedirectResolution r = new RedirectResolution("/albums/edit.jsp");
        r.addParameter("album.id", album.getId());
        return r;
    }
    
    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    
    /**
     * @param aComment the comment to set
     */
    public void setComment(String aComment) {
        comment = aComment;
    }
}
