package isis.projet.backend.service;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.UserDTO;
import isis.projet.backend.entity.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurDao;

    public UtilisateurService(UtilisateurRepository utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    @Transactional
    public long combienDePays() {
        return utilisateurDao.count();
    }

    @Transactional
    public Utilisateur creerUser(UserDTO userdto){
        Utilisateur user = new Utilisateur();
        user.setNom(userdto.getNom());
        user.setPrenom(userdto.getPrenom());      
        user.setRole(userdto.getRole()); 
        user.setUsername(userdto.getUsername()); 
        user.setPassword(userdto.getPassword()); 
        utilisateurDao.save(user);
        return  user;
    }

    @Transactional
    public Utilisateur ModifierUser(UserDTO userdto){
        var user = utilisateurDao.findByNom(userdto.getNom());
        user.setNom(userdto.getNom());
        user.setPrenom(userdto.getPrenom());      
        user.setRole(userdto.getRole()); 
        user.setUsername(userdto.getUsername()); 
        user.setPassword(userdto.getPassword()); 
        utilisateurDao.save(user);
        return  user;
    }

    @Transactional
    public String supprimerUtilisateur(String intitule){
        Utilisateur user = utilisateurDao.findByNom(intitule);
        utilisateurDao.delete(user);
        return  "User supprimer";
    }







    // @Transactional
    // public String supprimerUser(String nom){
    //     Utilisateur user = utilisateurDao.findByNom(nom);
    //     utilisateurDao.delete(user);
    //     return  "User supprimer";
    // }

    // @Transactional
    // public String modifierNom(String oldnom,String nom ){
    //     Utilisateur user = utilisateurDao.findByNom(oldnom);
    //     user.setNom(nom);
    //     utilisateurDao.save(user);
    //     return  "Nom modifier";
    // }

    // @Transactional
    // public String modifierPrenom(String oldnom,String prenom ){
    //     Utilisateur user = utilisateurDao.findByNom(oldnom);
    //     user.setPrenom(prenom);
    //     utilisateurDao.save(user);
    //     return  "Prenom modifier";
    // }

    // @Transactional
    // public String modifierUsername(String oldnom,String username ){
    //     Utilisateur user = utilisateurDao.findByNom(oldnom);
    //     user.setUsername(username);
    //     utilisateurDao.save(user);
    //     return  "username modifier";
    // }

    
}
