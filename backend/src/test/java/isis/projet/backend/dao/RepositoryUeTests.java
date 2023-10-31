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
class RepositoryUeTests {

	@Test
	void contextLoads() {

	}

	@Autowired
	private UeRepository ueDAO;

	@Autowired
	private MatiereRepository matiereDAO;

	@Autowired
	private AnneeRepository anneeDAO;

	
	@Test
	void compterLesUes() {
		log.info("Compter les entités");

		long nombre = ueDAO.count(); // 'count' donne le nombre d'enregistrements

		assertEquals(8, nombre, "Le jeu de test contient 2 catégories");
	}

	@Test
	void listerUe()  {
		log.info("Lister les Ues");
		List<Ue> liste = ueDAO.findAll(); // Renvoie la liste des ue dans la table
		assertFalse(liste.isEmpty(), "Il y a des Ue dans le jeu de test");
		log.info("Liste des Ue: {} ==>", liste);
	}

	@Test
	void listerUeAvecSousChaine() {
		log.info("Chercher des entités avec une requête 'custom' Spring");
		String substring = "Sci";
		List<Ue> liste = ueDAO.findByIntituleContaining(substring);
		log.info("Entités ue trouvées: {}", liste);
		assertEquals(2, liste.size(), "Il y a deux catégories dont le libellé contient la sous-chaine");
		substring = "xx";
		assertTrue(ueDAO.findByIntituleContaining(substring).isEmpty(), "Aucun libellé de catégorie ne contient cette sous-chaine"); 	
	}

	// @Test
	// void UeInconnue()  {
	// 	log.info("Chercher une entité inconnue");
	// 	int idInconnu = 10;
	// 	Optional<Ue> resultat = ueDAO.findById(idInconnu);
	// 	assertFalse(resultat.isPresent(), "Cet Ue  n'existe pas");
	// }
	
	@Test
	void creerUneUe()  {
		log.info("Créer une Ue");
		Ue Ue1 = new Ue();
		Ue1.setIntitule("Science de l'essaie");
		Ue1.setCodeue("A3-SV6");
		Ue1.setNumsemestre(1);
		Ue1.setPrerequis("Mathematique applique");
		Ue1.setCreditects(6);
		Ue1.setFoad(12);
		Ue1.setFormation("FIA-3");
		// Matiere Biologie = matiereDAO.findByIntitule("Biologie");
		// Ue1.getMatiere().add(Biologie);
		Annee Anne1 = anneeDAO.findByAnnee(2022);
		Ue1.setAnnee(Anne1);
		assertNull(Ue1.getId(), "L'entité n'a pas encore d'id ");
		ueDAO.save(Ue1); // 'save' enregistre l'entite dans la base
		Integer nouvellecle = Ue1.getId(); // La clé a été auto-générée lors de l'enregistrement
		assertNotNull(nouvellecle,"Cle auto-generer");
		log.info("L'ue à ete ajouter ",Ue1);

	}

    @Test
	void supprimerUe(){
        Ue ue = ueDAO.findByIntitule("Langues et sociétés 1");
		int idue = ue.getId();
		ueDAO.delete(ue);
		Optional<Ue> resultat = ueDAO.findById(idue);
		assertFalse(resultat.isPresent(), "Cet Ue  n'existe pas");
    }

	@Test
	void modifierUe(){
		log.info("Modifier une Ue");
        Ue Ue1 = ueDAO.findById(2).orElseThrow();
		Ue1.setIntitule("Science de l'essaie");
		Ue1.setCodeue("A3-SV6");
		Ue1.setNumsemestre(1);
		Ue1.setPrerequis("Mathematique applique");
		Ue1.setCreditects(6);
		Ue1.setFoad(12);
		Ue1.setFormation("FIA-3");
		// Matiere Biologie = matiereDAO.findByIntitule("Biologie");
		// Ue1.getMatiere().add(Biologie);
		Annee Anne1 = anneeDAO.findByAnnee(2022);
		Ue1.setAnnee(Anne1);
		ueDAO.save(Ue1); // 'save' enregistre l'entite dans la base
		assertEquals(Ue1.getIntitule(),"Science de l'essaie");
		log.info("L'ue à ete modifier ",Ue1.getIntitule());
    }
	
}
