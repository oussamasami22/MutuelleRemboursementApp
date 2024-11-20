package ma.ensa.system_metuelle.models;
import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor  @AllArgsConstructor @Data
public class Traitement {
    private  String codeBarre;
    private Boolean existe;
    private String nomMedicament;
    private String typeMedicament;
    private double prixMedicament;

    private MedicamentReferentiel medicamentReferentiel;
    private double montantRemboursement;


}
