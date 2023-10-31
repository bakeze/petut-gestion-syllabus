package isis.projet.backend.service;
import isis.projet.backend.dao.MatiereRepository;
import isis.projet.backend.dao.UeRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.MatiereDTOupdate;
import isis.projet.backend.entity.Matiere;
import isis.projet.backend.entity.Ue;
import isis.projet.backend.entity.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MatiereService {
    private final MatiereRepository matiereDao;
    private final UtilisateurRepository userDAO;
    private final UeRepository ueDAO;

    public MatiereService(MatiereRepository matiereDao, UtilisateurRepository userDAO, UeRepository ueDAO) {
        this.matiereDao = matiereDao;
        this.userDAO = userDAO;
        this.ueDAO = ueDAO;
    }

    @Transactional
    public Matiere creerMatiere(MatiereDTOupdate matupdate){
        var user = userDAO.findByNom(matupdate.getUtilisateur());
        var ue = ueDAO.findByIntitule(matupdate.getUe());
        Matiere nouvelleMatiere = new Matiere();
        nouvelleMatiere.setIntitule(matupdate.getIntitule());
        nouvelleMatiere.setCodematiere(matupdate.getCodematiere());
        nouvelleMatiere.setHeurecm(matupdate.getHeurecm());
        nouvelleMatiere.setHeuretd(matupdate.getHeuretd());
        nouvelleMatiere.setFoad(matupdate.getFoad());
        nouvelleMatiere.setDescripenseignement(matupdate.getDescripenseignement());
        nouvelleMatiere.setObjectifpedagogique(matupdate.getObjectifpedagogique());
        nouvelleMatiere.setUe(ue);
        nouvelleMatiere.setUtilisateur(user);
        matiereDao.save(nouvelleMatiere);
        return  nouvelleMatiere;
    }

    @Transactional
    public Matiere modifierMatiere( MatiereDTOupdate matupdate){
        var user = userDAO.findByNom(matupdate.getUtilisateur());
        var ue = ueDAO.findByIntitule(matupdate.getUe());
        Matiere nouvelleMatiere = matiereDao.findById(matupdate.getId()).orElseThrow();
        nouvelleMatiere.setIntitule(matupdate.getIntitule());
        nouvelleMatiere.setCodematiere(matupdate.getCodematiere());
        nouvelleMatiere.setHeurecm(matupdate.getHeurecm());
        nouvelleMatiere.setHeuretd(matupdate.getHeuretd());
        nouvelleMatiere.setFoad(matupdate.getFoad());
        nouvelleMatiere.setDescripenseignement(matupdate.getDescripenseignement());
        nouvelleMatiere.setObjectifpedagogique(matupdate.getObjectifpedagogique());
        nouvelleMatiere.setUe(ue);
        nouvelleMatiere.setUtilisateur(user);
        matiereDao.save(nouvelleMatiere);
        return  nouvelleMatiere;
    }


    @Transactional
    public String supprimerMatiere(String intitule){
        Matiere matiere = matiereDao.findByIntitule(intitule);
        matiereDao.delete(matiere);
        return  "Matiere supprimer";
    }


    // @Transactional
    // public String supprimerMatiere(MatiereDTO matieredto){
    //     Matiere matiere = matiereDao.findById(intitule);
    //     matiereDao.delete(matiere);
    //     return  "Matiere supprimer";
    // }

    // @Transactional
    // public String modifierIntituleMatiere(String oldintitule, String newintitule){
    //     Matiere matiere = matiereDao.findByIntitule(oldintitule);
    //     matiere.setIntitule(newintitule);
    //     matiereDao.save(matiere);
    //     return  "Intitule changer ";
    // }

    // @Transactional
    // public String modifierCodeMatiere(String intitule, String newcodematiere){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setCodematiere(newcodematiere);
    //     matiereDao.save(matiere);
    //     return  "Code changer ";
    // }

    // @Transactional
    // public String modifierheurecm(String intitule, int heurecm){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setHeurecm(heurecm);
    //     matiereDao.save(matiere);
    //     return  "heurecm changer ";
    // }

    // @Transactional
    // public String modifierheuretd(String intitule, int heuretd){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setHeuretd(heuretd);
    //     matiereDao.save(matiere);
    //     return  "heuretd changer ";
    // }


    // @Transactional
    // public String modifierfoad(String intitule, int foad){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setFoad(foad);
    //     matiereDao.save(matiere);
    //     return  "foad changer ";
    // }

    // @Transactional
    // public String modifierdescripenseignement(String intitule, String descripenseignement){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setDescripenseignement(descripenseignement);
    //     matiereDao.save(matiere);
    //     return  "descrip changer changer ";
    // }

    // @Transactional
    // public String modifierobjetcifpedagogique(String intitule, String objetcifpedagogique){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     matiere.setObjetcifpedagogique(objetcifpedagogique);
    //     matiereDao.save(matiere);
    //     return  "objectif  pedago ";
    // }

    // @Transactional
    // public String modifierUser(String intitule, String user){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     Utilisateur utilisateur = userDAO.findByNom(user);
    //     matiere.setUtilisateur(utilisateur);
    //     matiereDao.save(matiere);
    //     return  "  User changer ";
    // }
    
    // @Transactional
    // public String modifierUe(String intitule, String uetitle){
    //     Matiere matiere = matiereDao.findByIntitule(intitule);
    //     Ue ue = ueDAO.findByIntitule(uetitle);
    //     matiere.setUe(ue);
    //     matiereDao.save(matiere);
    //     return  " Ue Changer  ";
    // }
}
