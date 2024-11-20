package ma.ensa.system_metuelle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class RembAssure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomAssure;
    private String numeroAffiliation;
    private String immatriculation;
    private double totalRembouresement;
}
