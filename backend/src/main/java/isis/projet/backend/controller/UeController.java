package isis.projet.backend.controller;

import isis.projet.backend.dto.UeDTO;
import isis.projet.backend.dto.UeDtoUpdate;
import isis.projet.backend.service.UeService;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;

@RestController
@RequestMapping("/rest")
@Slf4j
public class UeController {

    private final UeService ueService;
    private final ModelMapper mapper;

    
    public UeController(UeService ueService, ModelMapper mapper ) {
        this.ueService = ueService;
        this.mapper = mapper;
    }

    @PutMapping("/combienDue")
    public Map<String, Long> combienDue() {
        log.info("Service combienDue");
        return Map.of("combien", ueService.combienDue());
    }

    @PostMapping(path = "UeAddControler")
	public UeDTO UeAddController(@RequestBody UeDTO uedto) {
        log.info("voici inti "+uedto.getIntitule());
        var ue = ueService.creerUe(uedto);
		return mapper.map(ue, UeDTO.class); 
	}

    @PutMapping(path = "SupprimerUe")
	public String SupprimerUeController(@RequestBody String intitule) {
        ueService.supprimerUe(intitule);
		return "Ue supprimer ! "; 
	}

    @PutMapping(path = "ModiferUe")
	public UeDtoUpdate ModifierUeController(@RequestBody UeDtoUpdate uedto) {
        var ue = ueService.ModifierUe(uedto);
		return  mapper.map(ue, UeDtoUpdate.class); 
	}


    // @PutMapping("/combienDeMatiereUe/{id}")
    // public Map<String, Long> combienDeMatiereUe(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res) {
    //     log.info("Service combienDeMatiereUe");
    //     return Map.of("combien", ueService.combienDeMatiereUe(id));
    // }

}


