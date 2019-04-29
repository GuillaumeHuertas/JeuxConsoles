package com.nsis.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsis.bo.JeuxBO;
import com.nsis.exception.JeuxDAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-test.xml"})

public class TestJeuxDAO {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private InterfaceJeuxDAO jeuxDAO; 
	
//	@PersistenceContext
//	private EntityManager em;
	
	@Test
	@DirtiesContext
	public void testAddJeux() {
		
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class); 
		jeuxBO.setName("Final Fantasy");
		
		try {
			jeuxDAO.insertJeux(jeuxBO);
		} catch (JeuxDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
