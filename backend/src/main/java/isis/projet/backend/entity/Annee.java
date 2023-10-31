package isis.projet.backend.entity;

import java.util.List;
import java.util.LinkedList;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Annee {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Basic(optional = false)
    @NonNull
    @Size(min = 1, max = 4)
    @Column(nullable = false, length = 40)
    private int annee;

    @ManyToOne(optional = false)
    private  Utilisateur utilisateur;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "annee")
    private List<Ue> ue = new LinkedList<>();

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(annee);
    return sb.toString();
    }

}
