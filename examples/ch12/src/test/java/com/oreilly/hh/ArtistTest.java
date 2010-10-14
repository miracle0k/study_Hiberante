package com.oreilly.hh;

import org.hibernate.Transaction;

import com.oreilly.hh.data.Artist;

import junit.framework.TestCase;

public class ArtistTest extends BaseTestCase {

    public ArtistTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSave() throws Exception {
        Transaction tx = null;
        try {
            // Create some data and persist it
            tx = session.beginTransaction();
            Artist a = new Artist();
            a.setName("Jim Elliott's Funk Band");
            assertNull(a.getId());
            a = (Artist) session.merge(a);
            assertNotNull(a.getId());
            // We're done; make our changes permanent
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                // Something went wrong; discard all partial changes
                tx.rollback();
            }
            throw new Exception("Transaction failed", e);
        }
    }
}
