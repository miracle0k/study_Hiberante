package com.oreilly.hh;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import junit.framework.TestCase;

public abstract class BaseTestCase extends TestCase {

	protected Session session;
	protected SessionFactory sessionFactory;

	public BaseTestCase(String name) {
		super(name);
	}

	@Override
	protected void setUp() throws Exception {
		// Create a configuration based on the XML file we've put
		// in the standard place.
		Configuration config = new AnnotationConfiguration();
		config.configure();

		// Configure Log4J
		PropertyConfigurator.configure( Thread.currentThread().getContextClassLoader().getResource("log4j.properties"));
		
		// Get the session factory we can use for persistence
		sessionFactory = config.buildSessionFactory();

		// Ask for a session using the JDBC information we've configured
		session = sessionFactory.openSession();
	}

	@Override
	protected void tearDown() throws Exception {
		session.close();

		// Clean up after ourselves
		sessionFactory.close();
	}

}
