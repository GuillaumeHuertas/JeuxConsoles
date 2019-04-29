package com.nsis.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsis.bo.JeuxBO;

@RunWith(SpringJUnit4ClassRunner.class)

public class TestJeuxDAO {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private InterfaceJeuxDAO jeuxDAO; 
	
	@PersistenceContext
	private EntityManager em;
	
	@Test
	public void testAddJeux() {
		
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class); 
		jeuxBO.setName("Final Fantasy");
		
		jeuxDAO.insertJeux(jeuxBO);
		
	}

}
