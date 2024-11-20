package ma.ensa.system_metuelle.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
@Getter
@Setter
@Entity
@AllArgsConstructor @NoArgsConstructor @Data
public class MedicamentReferentiel {
    @Id
    @Column(unique = true, nullable = false)
    private String codeBarre;
    private String nomMedicament;
    private double prixMedicament;
    @Range(min = 0, max = 1)
    private double tauxRemboursement;
}



