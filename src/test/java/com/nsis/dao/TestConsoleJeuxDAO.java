package com.nsis.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsis.bo.ConsoleBO;
import com.nsis.bo.JeuxBO;
import com.nsis.exception.JeuxDAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test.xml" })

public class TestConsoleJeuxDAO {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private InterfaceJeuxDAO jeuxDAO;

	@PersistenceContext
	private EntityManager em;

	@Test
	@DirtiesContext // Ce test vise à vérifier que l'on arrive bien à insérer un objet JeuxBO avec
					// ses paramètres
	public void testAddJeux() {

		// On crée un objet jeuxBO et on set ses différents paramètres (à l'exception
		// d'image)
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		jeuxBO.setName("Final Fantasy");
		jeuxBO.setDev("SquareSoft");
		jeuxBO.setFinished(true);
		jeuxBO.setNote(19);
		jeuxBO.setImage("Test");

		try {
			// On utilise la méthode insertJeux de JeuxDAO
			jeuxDAO.insertJeux(jeuxBO);
		} catch (JeuxDAOException e) {
			e.printStackTrace();
			// En cas d'erreur d'insertion on soulève un fail
			fail(e.getMessage());
		}

		// On envoi une requête pour vérifier que le jeux est bien entré en base de
		// donnée
		Query query = (Query) em.createQuery("select jeux from JeuxBO as jeux where jeux.name = :nom")
				.setParameter("nom", jeuxBO.getName());

		// Le résultat de la requête est placé dans une List
		List<JeuxBO> list = query.getResultList();
		// On crée un objet JeuxBO qui va stocker les objets de la list
		JeuxBO jeuxLoad = (JeuxBO) applicationContext.getBean(JeuxBO.class);

		// Nous allons parcourir la list à l'aide d'un itérateur
		for (Iterator<JeuxBO> it = list.iterator(); it.hasNext();) {
			// Et insérer l'objet de la liste dans jeuxLoad
			jeuxLoad = it.next();
		}

		// Nous testons chacun des attributs entré dans jeuxBO
		assertEquals("Final Fantasy", jeuxLoad.getName());
		assertEquals("SquareSoft", jeuxLoad.getDev());
		assertEquals(19, jeuxLoad.getNote());
		assertEquals("Test", jeuxLoad.getImage());
		assertTrue(jeuxLoad.isFinished());

		System.out.println("Le nom du jeux est : " + jeuxLoad.getName());

	}// Fin testAddJeux

	@Test
	@DirtiesContext // ce test vise à vérifgier que l'on entre bien une console quand elle est placé
					// en attribut de jeuxBO
	public void testAddJeuxWithConsole() {

		// On crée une console que l'on va mettre en attribut de jeuxBO
		ConsoleBO consoleBO =(ConsoleBO) applicationContext.getBean(ConsoleBO.class); 
		// On set les attributs de consoleBO
		consoleBO.setName("NES");
		consoleBO.setDev("Nintendo");
		// On crée une HasSet de ConsoleBO (l'attribut de jeuxBO étant un Set de ConoleBO
		Set<ConsoleBO> setConsoleBO = new HashSet<ConsoleBO>(); 
		// On ajoute la console au Set
		setConsoleBO.add(consoleBO); 
		
		// On crée un objet jeuxBO et on set ses différents paramètres (à l'exception
		// d'image)
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		jeuxBO.setName("Final Fantasy");
		jeuxBO.setDev("SquareSoft");
		jeuxBO.setFinished(true);
		jeuxBO.setNote(19);
		jeuxBO.setImage("Test");
		jeuxBO.setConsole(setConsoleBO);

		try {
			// On utilise la méthode insertJeux de JeuxDAO
			jeuxDAO.insertJeux(jeuxBO);
		} catch (JeuxDAOException e) {
			e.printStackTrace();
			// En cas d'erreur d'insertion on soulève un fail
			fail(e.getMessage());
		}

		// On envoi une requête pour vérifier que le jeux est bien entré en base de
		// donnée
		Query query = (Query) em.createQuery("select jeux from JeuxBO as jeux where jeux.name = :nom")
				.setParameter("nom", jeuxBO.getName());

		// Le résultat de la requête est placé dans une List
		List<JeuxBO> list = query.getResultList();
		// On crée un objet JeuxBO qui va stocker les objets de la list
		JeuxBO jeuxLoad = (JeuxBO) applicationContext.getBean(JeuxBO.class);

		// Nous allons parcourir la list à l'aide d'un itérateur
		for (Iterator<JeuxBO> it = list.iterator(); it.hasNext();) {
			// Et insérer l'objet de la liste dans jeuxLoad
			jeuxLoad = it.next();
		}

		// Nous testons chacun des attributs entré dans jeuxBO
		assertEquals("Final Fantasy", jeuxLoad.getName());
		assertEquals("SquareSoft", jeuxLoad.getDev());
		assertEquals(19, jeuxLoad.getNote());
		assertEquals("Test", jeuxLoad.getImage());
		assertTrue(jeuxLoad.isFinished());
		
		// On crée un Set pour mettre le Set de console de jeuxLoad
		Set<ConsoleBO> SetConsoleLoad = jeuxLoad.getConsole(); 
		
		// On crée un consoleBO pour stocker la console
		ConsoleBO consoleLoad = (ConsoleBO) applicationContext.getBean(ConsoleBO.class); 
		
		// Nou allons parcourir ce Set pour récupérer la console voulu
		Iterator<ConsoleBO> it = SetConsoleLoad.iterator();
	      while(it.hasNext())
	    	// Et insérer l'objet de la liste dans jeuxLoad
				consoleLoad = it.next();
		
		
		
//		for (Iterator<ConsoleBO> it = SetConsoleLoad.iterator(); it.hasNext();) {
//			// Et insérer l'objet de la liste dans jeuxLoad
//			consoleLoad = it.next();
//		}
		
		// Nous testons les attributs de la console chargé
		assertEquals("NES", consoleLoad.getName());
		assertEquals("Nintendo", consoleLoad.getDev());
		
		System.out.println("Le nom du jeux est : " + jeuxLoad.getName() + " sur la console : " + consoleLoad.getName());

	}// Fin testAddJeux
}
