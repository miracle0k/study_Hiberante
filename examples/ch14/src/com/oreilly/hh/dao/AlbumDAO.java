package com.oreilly.hh.dao;

import java.util.List;

import com.oreilly.hh.data.Album;

public interface AlbumDAO {
    public Album persist( Album album );
    public void delete(Album album);
    public List<Album> list();
    public Album get(Integer id);
}
