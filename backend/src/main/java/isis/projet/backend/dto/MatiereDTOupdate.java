package isis.projet.backend.dto;

import lombok.Data;

@Data
public class MatiereDTOupdate {
    private Integer Id;
    private String intitule;
    private String codematiere;
    private Integer heurecm;
    private Integer heuretd;
    private Integer foad;
    private String descripenseignement;
    private String objectifpedagogique;
    private String ue;
    private String utilisateur;
}

