package ma.ensa.system_metuelle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class Dossier {
    @Id 
    private String nomAssure;
    private String numeroAffiliation;
    private String immatriculation;
    private String lienParente;
    private double montantTotalFrais;
    private double prixConsultation;
    private int nombrePiecesJointes;
    private String nomBeneficiaire;
    private Date dateDepotDossier;

    private List<Traitement> traitements;

    private double RobouresementConsultation;


}
