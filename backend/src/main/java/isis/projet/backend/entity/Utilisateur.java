package isis.projet.backend.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jakarta.persistence.*;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/

import jakarta.validation.constraints.Size;

@Entity
@Getter  
@Setter
@NoArgsConstructor 
@ToString
public class Utilisateur {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 40)
    private String nom;

    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 40)
    private String prenom;

    @Basic(optional = false)
    @Size(min = 1, max = 40)
    @Column(nullable = false, length = 40)
    private String username;

    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 40)
    private String password;

    @Basic(optional = false)
    @Size(min = 1, max = 40)
    @Column(nullable = false, length = 40)
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "utilisateur")
    @ToString.Exclude
    private List<Annee> annee = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
    @ToString.Exclude
    private List<Matiere> matiere = new LinkedList<>();

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(nom +" "+ prenom);
    return sb.toString();
    }


}
