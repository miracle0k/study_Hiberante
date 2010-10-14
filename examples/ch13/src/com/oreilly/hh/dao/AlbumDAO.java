package com.oreilly.hh.dao;

import com.oreilly.hh.data.Album;

public interface AlbumDAO {
	public Album persist( Album album );
    public void delete(Album album);
}
