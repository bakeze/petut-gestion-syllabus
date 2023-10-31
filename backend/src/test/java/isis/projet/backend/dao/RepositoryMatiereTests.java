package isis.projet.backend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isis.projet.backend.entity.Annee;
import isis.projet.backend.entity.Matiere;
import isis.projet.backend.entity.Ue;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class RepositoryMatiereTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private UeRepository ueDAO;

	@Autowired
	private MatiereRepository matiereDAO;

	@Autowired
	private AnneeRepository anneeDAO;

	
	@Autowired
	private UtilisateurRepository userDAO;


	
	// @Test
	// void compterLesUes() {
	// 	log.info("Compter les entités");

	// 	long nombre = ueDAO.count(); // 'count' donne le nombre d'enregistrements

	// 	assertEquals(8, nombre, "Le jeu de test contient 2 catégories");
	// }

	@Test
	void listerMatiere()  {
		log.info("Lister les Matieres");
		List<Matiere> liste = matiereDAO.findAll(); // Renvoie la liste des Matieres dans la table
		assertFalse(liste.isEmpty(), "Il y a des Matiere dans le jeu de test");
		log.info("Liste des Matieres: {} ==>", liste);
	}

	// @Test
	// void listerUeAvecSousChaine() {
	// 	log.info("Chercher des entités avec une requête 'custom' Spring");
	// 	String substring = "Sci";
	// 	List<Ue> liste = ueDAO.findByIntituleContaining(substring);
	// 	log.info("Entités ue trouvées: {}", liste);
	// 	assertEquals(2, liste.size(), "Il y a deux catégories dont le libellé contient la sous-chaine");
	// 	substring = "xx";
	// 	assertTrue(ueDAO.findByIntituleContaining(substring).isEmpty(), "Aucun libellé de catégorie ne contient cette sous-chaine"); 	
	// }

	// @Test
	// void UeInconnue()  {
	// 	log.info("Chercher une entité inconnue");
	// 	int idInconnu = 10;
	// 	Optional<Matiere> resultat = matiereDAO.findById(idInconnu);
	// 	assertFalse(resultat.isPresent(), "Cet Matiere  n'existe pas");
	// }
	
	// @Test
	// void creerUneMatiere()  {
	// 	log.info("Créer une Matiere");
	// 	var user = userDAO.findByNom("marc");
    //     var ue = ueDAO.findByIntitule("Sciences fondamentales 1");
    //     Matiere nouvelleMatiere = new Matiere();
    //     nouvelleMatiere.setIntitule("Mathématique test");
    //     nouvelleMatiere.setCodematiere("MAT-22");
    //     nouvelleMatiere.setHeurecm(12);
    //     nouvelleMatiere.setHeuretd(13);
    //     nouvelleMatiere.setFoad(2);
    //     nouvelleMatiere.setDescripenseignement("Test test test");
    //     nouvelleMatiere.setObjectifpedagogique("Test test test");
    //     nouvelleMatiere.setUe(ue);
    //     nouvelleMatiere.setUtilisateur(user);
    //     matiereDAO.save(nouvelleMatiere);
	// 	Integer nouvellecle = nouvelleMatiere.getId(); // La clé a été auto-générée lors de l'enregistrement
	// 	assertNotNull(nouvellecle,"Cle auto-generer");
	// }

}
