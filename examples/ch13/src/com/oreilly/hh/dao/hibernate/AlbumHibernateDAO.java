package com.oreilly.hh.dao.hibernate;

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

}
