package isis.projet.backend.dao;

import isis.projet.backend.entity.Annee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Integer> {

    /**
	 * Recherche une Annee par son id (unique)
	 * @param annee  recherché
	 * @return Une Ue avec ce libellé
	 */
	Annee findById(int id);

	 /**
	 * Recherche une Annee par son id (unique)
	 * @param annee  recherché
	 * @return Une Ue avec ce libellé
	 */
	Annee findByAnnee(Integer annee);
}