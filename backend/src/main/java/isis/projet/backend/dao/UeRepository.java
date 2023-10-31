package isis.projet.backend.dao;

import isis.projet.backend.entity.Ue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UeRepository extends JpaRepository<Ue, Integer> {
    /**
	 * Recherche une Ue par son intitule (unique)
	 * @param intitule l'Ue recherché
	 * @return Une Ue avec ce libellé
	 */
	Ue findByIntitule(String intitule);	

	/**
	 * Recherche les Ue dont le libellé contient une sous-chaine
	 * @param substring la sous-chaine à rechercher dans le libellé
	 * @return la liste des Ue dont le libellé contient substring
	 */
	List<Ue> findByIntituleContaining(String substring);

	// JPQL : formulée sur le modèle conceptuel de données
	//calcul des matieres par ue 
	// @Query("SELECT COUNT(ue.matiere) FROM Ue ue WHERE e.matiere.id = :idMatiere")
	// Integer countMatiereByUe(Integer idMatiere);

}