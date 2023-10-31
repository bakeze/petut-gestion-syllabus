package isis.projet.backend.controller;

import isis.projet.backend.dto.UeDTO;
import isis.projet.backend.dto.UserDTO;
import isis.projet.backend.service.UeService;
import isis.projet.backend.service.UtilisateurService;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;

@RestController
@RequestMapping("/rest")
@Slf4j
public class UtilisateurController {

    private final UtilisateurService userService;
    private final ModelMapper mapper;


    
    public UtilisateurController(UtilisateurService userService, ModelMapper mapper ) {
        this.userService = userService;
        this.mapper = mapper;
    }

    // @PutMapping("/combienDue")
    // public Map<String, Long> combienDue() {
    //     log.info("Service combienDue");
    //     return Map.of("combien", ueService.combienDue());
    // }

    @PostMapping(path = "UserAddControler")
	public UserDTO UeAddController(@RequestBody UserDTO userdto) {
        log.info("voici inti "+userdto.getNom());
        var user = userService.creerUser(userdto);
		return mapper.map(user, UserDTO.class); 
	}

    @PutMapping(path = "SupprimerUser")
	public String SupprimerUserController(@RequestBody String intitule) {
        userService.supprimerUtilisateur(intitule);
		return "User supprimer ! "; 
	}

    @PutMapping(path = "ModiferUser")
	public UserDTO ModifierUeController(@RequestBody UserDTO userdto) {
        var user = userService.ModifierUser(userdto);
		return  mapper.map(user, UserDTO.class); 
	}


    // @PutMapping("/combienDeMatiereUe/{id}")
    // public Map<String, Long> combienDeMatiereUe(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res) {
    //     log.info("Service combienDeMatiereUe");
    //     return Map.of("combien", ueService.combienDeMatiereUe(id));
    // }

}


