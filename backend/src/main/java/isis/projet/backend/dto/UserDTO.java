package isis.projet.backend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String role;

}
