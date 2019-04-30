package com.nsis.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nsis.bo.JeuxBO;
import com.nsis.exception.JeuxDAOException;

@Repository
public class JeuxDAO implements InterfaceJeuxDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ApplicationContext applicationContext;

	// Constructeur par défaut
	public JeuxDAO() {

	}

	// Méthode permetant d'insérer un jeu
	@Transactional
	public void insertJeux(JeuxBO jeux) throws JeuxDAOException {

		// Vérifie que le jeu n'est pas null 
		if (jeux == null) {
			throw new JeuxDAOException("Le jeux ne peut être nul");
		}
		
		// Vérifie que le jeu n'existe pas déjà
		
		em.merge(jeux);
		// Retourner un message 
		
	} // Fin méthode insertJeux

	public List<JeuxBO> allJeux() throws JeuxDAOException {

		// Requête permettant d'aller chercher tous les jeux en base de donnée
		Query query = (Query) em.createQuery("select jeux from JeuxBO as jeux");

		// Le résultat de la requête est placé dans une List
		List<JeuxBO> listJeux = query.getResultList();

		return listJeux;
	} // Fin méthode allJeux
	
	// Méthode permettant de rechercher par nom 
	
	// Méthode permettant de mettre à jour les données d'un jeux
	
	// Méthode permtant de supprimer une reférence 
	
	
	
	

}
