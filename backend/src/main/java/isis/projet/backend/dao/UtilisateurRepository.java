package isis.projet.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isis.projet.backend.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    /**
	 * Recherche une Ue par son intitule (unique)
	 * @param   id
	 * @return Une user avec ce libellé
	 */
	Utilisateur findByNom(String nom);	
}