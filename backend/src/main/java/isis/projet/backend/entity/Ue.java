package isis.projet.backend.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.UniqueElements;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor  @ToString
public class Ue {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Basic(optional = false)
    @Size(max = 50, min = 3)
    @NotEmpty   
    @Column(nullable = false)
    private String intitule;

    @Basic(optional = false)
    @NonNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 40)
    private String codeue;

    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private int creditects;

    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private int foad;

    @Basic(optional = false)
    @NonNull
    @Column(nullable = false)
    private String prerequis;

    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private int numsemestre;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ue")
    @ToString.Exclude
	private List<Matiere> matiere = new ArrayList<>();

    @ManyToOne(optional = false)
    @NotNull
    private Annee annee ;

    @Basic(optional = false)
    @NonNull
    @Column(nullable = false)
    private String formation;


    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(intitule);
    return sb.toString();
}


}