package com.nsis.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nsis.bo.JeuxBO;
import com.nsis.exception.JeuxDAOException;

@Repository
public class JeuxDAO implements InterfaceJeuxDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ApplicationContext applicationContext;
	
	public JeuxDAO() {
		
	}

	@Transactional
	public void insertJeux(JeuxBO jeux) {
		
		em.merge(jeux);
		
	}

}
