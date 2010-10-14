package com.oreilly.hh.dao.hibernate;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oreilly.hh.dao.ArtistDAO;
import com.oreilly.hh.data.Artist;
import com.oreilly.hh.data.Track;

public class ArtistHibernateDAO extends HibernateDaoSupport implements
		ArtistDAO {

	private static Logger log = Logger.getLogger(ArtistHibernateDAO.class);

	public Artist persist(Artist artist) {
		return (Artist) getHibernateTemplate().merge(artist);
	}

	public void delete(Artist artist) {
		getHibernateTemplate().delete(artist);
	}

	public Artist uniqueByName(final String name) {
		return (Artist) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = getSession().getNamedQuery(
						"com.oreilly.hh.artistByName");
				query.setString("name", name);
				return (Artist) query.uniqueResult();
			}
		});
	}

	/**
	 * Look up an artist record given a name.
	 * 
	 * @param name
	 *            the name of the artist desired.
	 * @param create
	 *            controls whether a new record should be created if the
	 *            specified artist is not yet in the database.
	 * @param session
	 *            the Hibernate session that can retrieve data
	 * @return the artist with the specified name, or <code>null</code> if
	 *         no such artist exists and <code>create</code> is
	 *         <code>false</code>.
	 */
	public Artist getArtist(String name, boolean create) {
		Artist found = uniqueByName(name);
		if (found == null && create) {
			found = new Artist(name, new HashSet<Track>(), null);
			found = persist(found);
		}
		if (found != null && found.getActualArtist() != null) {
			return found.getActualArtist();
		}
		return found;
	}

}
