package isis.projet.backend.dao;
import isis.projet.backend.dao.MatiereRepository;
import isis.projet.backend.entity.Matiere;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    /**
	 * Recherche une Ue par son intitule (unique)
	 * @param intitule l'Ue recherché
	 * @return Une Ue avec ce libellé
	 */
	Matiere findByIntitule(String intitule);	
	
}