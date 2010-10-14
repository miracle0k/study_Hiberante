package com.oreilly.hh;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import com.oreilly.hh.data.*;

import java.util.*;

/**
 * Retrieve all persistent objects
 */
public class QueryTest3 {

    /**
     * Look up and print all entities when invoked from the command line.
     */
    public static void main(String args[]) throws Exception {
        // Create a configuration based on the XML file we've put
        // in the standard place.
        Configuration config = new Configuration();
        config.configure();

        // Get the session factory we can use for persistence
        SessionFactory sessionFactory = config.buildSessionFactory();

        // Ask for a session using the JDBC information we've configured
        Session session = sessionFactory.openSession();
        try {
            // Print every mapped object in the database
            List all = session.createQuery("from java.lang.Object").list();
            for (Object obj : all) {
                System.out.println(obj);
            }
        } finally {
            // No matter what, close the session
            session.close();
        }

        // Clean up after ourselves
        sessionFactory.close();
    }
}
