package isis.projet.backend.controller;
import isis.projet.backend.dto.MatiereDTOupdate;

import isis.projet.backend.service.MatiereService;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;


import org.modelmapper.ModelMapper;


@RestController
@RequestMapping("/rest")
@Slf4j
public class MatiereController {
    private final MatiereService matiereService;
    private final ModelMapper mapper;

    
    public MatiereController( MatiereService matiereService, ModelMapper mapper ) {
        this.matiereService = matiereService;
        this.mapper = mapper;
    }

    @PostMapping(path = "MatiereAddControler")
	public MatiereDTOupdate MatiereAddController(@RequestBody MatiereDTOupdate matupdate) {
        System.out.println("voici inti "+matupdate.getIntitule());
        var matiere = matiereService.creerMatiere(matupdate);
		return mapper.map(matiere, MatiereDTOupdate.class); 
	}

    @PutMapping(path = "ModifierMatiere")
	public MatiereDTOupdate ModifierMatiereController(@RequestBody MatiereDTOupdate matupdate) {
        var matiere = matiereService.modifierMatiere(matupdate);
		return  mapper.map(matiere, MatiereDTOupdate.class); 
	}

    @PutMapping(path = "SupprimerMatiere")
	public String SupprimerMatiereController(@RequestBody String intitule) {
        matiereService.supprimerMatiere(intitule);
		return "Matiere supprimer ! "; 
	}

}


