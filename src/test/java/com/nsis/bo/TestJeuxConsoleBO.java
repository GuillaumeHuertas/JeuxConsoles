package com.nsis.bo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
//import org.springframework.test.annotation.DirtiesContext;

//@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "/persistance.xml" })

public class TestJeuxConsoleBO {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@PersistenceContext
	private EntityManager em;

	@Test
	//@DirtiesContext
	public void testAddPerson() {
		
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		jeuxBO.setName("Final Fantasy");

		em.merge(jeuxBO); 
		
		
	}
	
	
}
