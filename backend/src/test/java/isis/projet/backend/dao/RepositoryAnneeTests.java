package isis.projet.backend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isis.projet.backend.entity.Annee;
import isis.projet.backend.entity.Utilisateur;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class RepositoryAnneeTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private AnneeRepository anneeDAO;

	@Autowired
	private UtilisateurRepository userDAO;
	
	@Test
	void compterLesAnnee() {
		log.info("Compter les entités");

		long nombre = anneeDAO.count(); // 'count' donne le nombre d'enregistrements

		assertEquals(3, nombre, "Le jeu de test contient 2 Annee");
	}

	@Test
	void listerAnnee()  {
		log.info("Lister les Annees");
		List<Annee> liste = anneeDAO.findAll(); // Renvoie la liste des ue dans la table
		assertFalse(liste.isEmpty(), "Il y a des Annee dans le jeu de test");
		log.info("Liste des Annees: {} ==>", liste);
	}
	
	// @Test
	// void creerAnnee()  {
	// 	log.info("Créer une Annee");
	// 	Annee annee = new Annee();
	// 	annee.setAnnee("2024");
	// 	assertNull(annee.getId(), "L'entité n'a pas encore d'id ");
	// 	annee.setUtilisateur();
	// 	anneeDAO.save(annee); // 'save' enregistre l'entite dans la base
	// 	Integer nouvellecle = annee.getId(); // La clé a été auto-générée lors de l'enregistrement
	// 	assertNotNull(nouvellecle,"Cle auto-generer");
	// 	log.info("L'annee à ete ajouter ",liste);
	// }


}
