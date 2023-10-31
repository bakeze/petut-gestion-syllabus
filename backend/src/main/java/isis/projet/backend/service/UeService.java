package isis.projet.backend.service;
import isis.projet.backend.dao.AnneeRepository;
import isis.projet.backend.dao.MatiereRepository;
import isis.projet.backend.dao.UeRepository;
import isis.projet.backend.dto.UeDTO;
import isis.projet.backend.dto.UeDtoUpdate;
import isis.projet.backend.entity.Annee;
import isis.projet.backend.entity.Matiere;
import isis.projet.backend.entity.Ue;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UeService {
    private final UeRepository ueDao;
    private final MatiereRepository matiereDAO;
    private final AnneeRepository anneeDAO;

    public UeService(UeRepository ueDao, MatiereRepository matiereDAO, AnneeRepository anneeDAO ) {
        this.ueDao = ueDao;
        this.matiereDAO = matiereDAO;
        this.anneeDAO = anneeDAO;
    }

    @Transactional
    public long combienDue() {
        return ueDao.count();
    }
    /**
     * Service métier : Ajouter  une ue avec ses matieres
     * Règles métier :
     * - la matiere doit exister
     * - aucun champ ne doit être null
     * - 
     * - Pour chaque produit commandé, décrémente la quantité en stock (Produit.unitesEnStock)
     *   de la quantité commandée
     * @param commandeNum la clé de la commande
     * @return la commande mise à jour
     */

    @Transactional
    public Ue creerUe(UeDTO uedto){
        var anne = anneeDAO.findByAnnee(uedto.getAnnee());
        var nouvelleUe = new Ue();
        nouvelleUe.setIntitule(uedto.getIntitule());
        nouvelleUe.setCodeue(uedto.getCodeue());
        nouvelleUe.setCreditects(uedto.getCreditects());
        nouvelleUe.setPrerequis(uedto.getPrerequis());    
        nouvelleUe.setNumsemestre(uedto.getNumsemestre()); 
        nouvelleUe.setAnnee(anne);
        nouvelleUe.setFormation(uedto.getFormation());
        ueDao.save(nouvelleUe);
        return  nouvelleUe;
    }


    public Ue ModifierUe(UeDtoUpdate uedto){
        Ue nouvelleUe = ueDao.findById(uedto.getId()).orElseThrow();
        nouvelleUe.setIntitule(uedto.getIntitule());
        nouvelleUe.setCodeue(uedto.getCodeue());
        nouvelleUe.setCreditects(uedto.getCreditects());
        nouvelleUe.setPrerequis(uedto.getPrerequis());    
        nouvelleUe.setNumsemestre(uedto.getNumsemestre()); 
        nouvelleUe.setFormation(uedto.getFormation());
        ueDao.save(nouvelleUe);
        return  nouvelleUe;
    }

    @Transactional
    public String supprimerUe(String intitule){
        Ue ue = ueDao.findByIntitule(intitule);
        ueDao.delete(ue);
        return  "Ue supprimer";
    }

    // @Transactional
    // public String modifierIntituleUe(String oldintitule, String newintitule){
    //     Ue ue = ueDao.findByIntitule(oldintitule);
    //     ue.setIntitule(newintitule);
    //     ueDao.save(ue);
    //     return  "Intitule changer ";
    // }

    // @Transactional
    // public String modifierCodeue(String oldintitule, String newcodeue){
    //     Ue ue = ueDao.findByIntitule(oldintitule);
    //     ue.setCodeue(newcodeue);
    //     ueDao.save(ue);
    //     return  "Code changer ";
    // }

    // @Transactional
    // public String modifiercreditectsue(String oldintitule, int newcreditects){
    //     Ue ue = ueDao.findByIntitule(oldintitule);
    //     ue.setCreditects(newcreditects);
    //     ueDao.save(ue);
    //     return  "Credit ects changer  ";
    // }

    
    // @Transactional
    // public String modifierPrerequisue(String oldintitule, String newprerequis){
    //     Ue ue = ueDao.findByIntitule(oldintitule);
    //     ue.setPrerequis(newprerequis);
    //     ueDao.save(ue);
    //     return  "prerequis changer ";
    // }


    // @Transactional
    // public String modifierNumsemestreue(String oldintitule, int numsemestre){
    //     Ue ue = ueDao.findByIntitule(oldintitule);
    //     ue.setNumsemestre(numsemestre);
    //     ueDao.save(ue);
    //     return  "Semestre changer ";
    // }

































    // private Ue ajouterMatiereToUe(Ue ue, String matiere) {
    //     String [] mates = matiere.split(",");
    //     Matiere mat = null;
    //     for(String str : mates ){
    //         mat = matiereDAO.findByIntitule(str);
    //         ue.getMatiere().add(mat);
    //      }
    //     return ue;
    // } 
        

}
