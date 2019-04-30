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

public class TestJeuxDAO {

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

		// On crée un objet jeuxBO et on set ses différents paramètres
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
	@DirtiesContext // Ce test vise à vérifier que l'on arrive bien à insérer un objet JeuxBO avec
					// ses paramètres
	// On pourra se reservir de ce test pour tester les futur méthodes classé par
	// notes, apr jeux non fini etc.
	public void testAddManyJeux() {

		// On crée plusieurs objets jeuxBO et on set leurs différents paramètres
		JeuxBO jeuxBO = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		JeuxBO jeuxBO2 = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		JeuxBO jeuxBO3 = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		JeuxBO jeuxBO4 = (JeuxBO) applicationContext.getBean(JeuxBO.class);
		JeuxBO jeuxBO5 = (JeuxBO) applicationContext.getBean(JeuxBO.class);

		// Set de jeuxBO
		jeuxBO.setName("Final Fantasy");
		jeuxBO.setDev("SquareSoft");
		jeuxBO.setFinished(true);
		jeuxBO.setNote(20);
		jeuxBO.setImage("Test");

		// Set dejeuxBO2
		jeuxBO2.setName("Final Fantasy II");
		jeuxBO2.setDev("SquareSoft");
		jeuxBO2.setFinished(true);
		jeuxBO2.setNote(18);
		jeuxBO2.setImage("Test2");

		// Set dejeuxBO3
		jeuxBO3.setName("Super Mario");
		jeuxBO3.setDev("Nintendo");
		jeuxBO3.setFinished(false);
		jeuxBO3.setNote(16);
		jeuxBO3.setImage("Test3");

		// Set dejeuxBO4
		jeuxBO4.setName("Castlevania");
		jeuxBO4.setDev("Konami");
		jeuxBO4.setFinished(false);
		jeuxBO4.setNote(15);
		jeuxBO4.setImage("Test4");

		// Set dejeuxBO5
		jeuxBO5.setName("The Legend of Zelda");
		jeuxBO5.setDev("Nintendo");
		jeuxBO5.setFinished(true);
		jeuxBO5.setNote(19);
		jeuxBO5.setImage("Test5");

		try {
			// On utilise la méthode insertJeux pour insérer les 5 jeux
			jeuxDAO.insertJeux(jeuxBO);
			jeuxDAO.insertJeux(jeuxBO2);
			jeuxDAO.insertJeux(jeuxBO3);
			jeuxDAO.insertJeux(jeuxBO4);
			jeuxDAO.insertJeux(jeuxBO5);
		} catch (JeuxDAOException e) {
			e.printStackTrace();
			// En cas d'erreur d'insertion on soulève un fail
			fail(e.getMessage());
		}

		// On envoi une requête pour vérifier que les jeux sont biens entrés en base de
		// donnée
		Query query = (Query) em.createQuery("select jeux from JeuxBO as jeux");

		// Le résultat de la requête est placé dans une List
		List<JeuxBO> list = query.getResultList();
		// Nous vérifions que la list possède bien 5 entrés
		assertTrue(list.size()==5);
		// On crée un objet JeuxBO qui va stocker les objets de la list
		JeuxBO jeuxLoad = (JeuxBO) applicationContext.getBean(JeuxBO.class);

		// Nous allons parcourir la list à l'aide d'un itérateur
		for (Iterator<JeuxBO> it = list.iterator(); it.hasNext();) {
			// Et insérer l'objet de la liste dans jeuxLoad
			jeuxLoad = it.next();

			// Nous allons vérifier que les 3 consoles ont bin été enregistrés dans la base
			// de donnée
			if (jeuxLoad.getName().equals("Final Fantasy")) {
				assertEquals("Final Fantasy", jeuxLoad.getName());
			} else if (jeuxLoad.getName().equals("Final Fantasy II")) {
				assertEquals("Final Fantasy II", jeuxLoad.getName());
			} else if (jeuxLoad.getName().equals("Super Mario")) {
				assertEquals("Super Mario", jeuxLoad.getName());
			} else if (jeuxLoad.getName().equals("Castlevania")) {
				assertEquals("Castlevania", jeuxLoad.getName());
			} else if (jeuxLoad.getName().equals("The Legend of Zelda")) {
				assertEquals("The Legend of Zelda", jeuxLoad.getName());
			} else {
				// Si on n'optient un autre nom on retournera un fail
				fail("Vous n'êtes pas censé arriver ici ! ");
			}
		}

	}// Fin testAddManyJeux

	@Test
	@DirtiesContext // ce test vise à vérifgier que l'on entre bien une console quand elle est placé
					// en attribut de jeuxBO
	public void testAddJeuxWithConsole() {

		// On crée une console que l'on va mettre en attribut de jeuxBO
		ConsoleBO consoleBO = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);
		// On set les attributs de consoleBO
		consoleBO.setName("NES");
		consoleBO.setDev("Nintendo");
		// On crée une HasSet de ConsoleBO (l'attribut de jeuxBO étant un Set de
		// ConoleBO
		Set<ConsoleBO> setConsoleBO = new HashSet<ConsoleBO>();
		// On ajoute la console au Set
		setConsoleBO.add(consoleBO);

		// On crée un objet jeuxBO et on set ses différents paramètres
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
		Set<ConsoleBO> setConsoleLoad = jeuxLoad.getConsole();

		// On crée un consoleBO pour stocker la console
		ConsoleBO consoleLoad = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);

		// Nou allons parcourir ce Set pour récupérer la console voulu
		Iterator<ConsoleBO> it = setConsoleLoad.iterator();
		while (it.hasNext())
			// Et insérer l'objet de la liste dans jeuxLoad
			consoleLoad = it.next();

		// Nous testons les attributs de la console chargé
		assertEquals("NES", consoleLoad.getName());
		assertEquals("Nintendo", consoleLoad.getDev());

		System.out.println("Le nom du jeux est : " + jeuxLoad.getName() + " sur la console : " + consoleLoad.getName());

	}// Fin testAddJeuxWithConsole

	@Test
	@DirtiesContext // Ajout d'un jeux possédant plusieurs consoles
	public void testAddJeuxWithManyConsoles() {

		// On crée Trois consoles que l'on va mettre en attribut de jeuxBO
		ConsoleBO consoleBO = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);
		ConsoleBO consoleBO2 = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);
		ConsoleBO consoleBO3 = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);

		// On set les attributs des consoleBO
		consoleBO.setName("NES");
		consoleBO.setDev("Nintendo");

		consoleBO2.setName("SNES");
		consoleBO2.setDev("Nintendo");

		consoleBO3.setName("Playstation");
		consoleBO3.setDev("Sony");

		// On crée une HasSet de ConsoleBO (l'attribut de jeuxBO étant un Set de
		// ConoleBO
		Set<ConsoleBO> setConsoleBO = new HashSet<ConsoleBO>();
		// On ajoute les consoles au Set
		setConsoleBO.add(consoleBO);
		setConsoleBO.add(consoleBO2);
		setConsoleBO.add(consoleBO3);

		// On crée un objet jeuxBO et on set ses différents paramètres
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
		Set<ConsoleBO> setConsoleLoad = jeuxLoad.getConsole();
		// On vérifie qu'il y a bien trois console dans le Set
		assertEquals(3, setConsoleLoad.size());

		// On crée un consoleBO pour stocker la console
		ConsoleBO consoleLoad = (ConsoleBO) applicationContext.getBean(ConsoleBO.class);

		// Nous allons parcourir ce Set pour récupérer la console voulu
		for (Iterator<ConsoleBO> it = setConsoleLoad.iterator(); it.hasNext();) {
			// Et insérer l'objet de la liste dans console
			consoleLoad = it.next();

			// Nous allons vérifier que les 3 consoles ont bin été enregistrés dans la base
			// de donnée
			if (consoleLoad.getName().equals("NES")) {
				assertEquals("NES", consoleLoad.getName());
			} else if (consoleLoad.getName().equals("SNES")) {
				assertEquals("SNES", consoleLoad.getName());
			} else if (consoleLoad.getName().equals("Playstation")) {
				assertEquals("Playstation", consoleLoad.getName());
			} else {
				// Si on n'optient un autre nom on retournera un fail
				fail("Vous n'êtes pas censé arriver ici ! ");
			}
		}
	}// Fin testAddJeuxWithManyConsoles
}
