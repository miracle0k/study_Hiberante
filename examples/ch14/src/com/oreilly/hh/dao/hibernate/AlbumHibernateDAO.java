package com.oreilly.hh.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oreilly.hh.dao.AlbumDAO;
import com.oreilly.hh.data.Album;

public class AlbumHibernateDAO extends HibernateDaoSupport implements AlbumDAO {

    public Album persist(Album album) {
        album = (Album) getHibernateTemplate().merge(album);
        getSession().flush();
        return album;
    }
    
    public void delete(Album album) {
        getHibernateTemplate().delete(album);
    }
    @SuppressWarnings("unchecked")
    public List<Album> list() {
        return getHibernateTemplate().loadAll(Album.class);
    }
   
    public Album get(Integer id) {
        return (Album)getHibernateTemplate().load(Album.class, id);
    }
}
