package ma.ensa.system_metuelle.processors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class CalculProcessor implements ItemProcessor<Dossier, RembAssure> {
    @Autowired
    private ConsultationProcessor consultationProcessor;
    @Autowired
    private TraitementMappingProcessor traitementMappingProcessor;
    @Autowired
    private TraitementRembouresementProcessor traitementRembouresementProcessor;
    @Autowired
    private TotalRembouresmentProcessor totalRembouresmentProcessor;

    @Override
    public RembAssure process(Dossier item) throws Exception {
        // Étape 1 : Valider les consultations
        Dossier dossierAfterConsultation = consultationProcessor.process(item);

        // Étape 2 : Mapper les traitements
        Dossier dossierAfterMapping = traitementMappingProcessor.process(dossierAfterConsultation);

        // Étape 3 : Calculer les remboursements par traitement
        Dossier dossierAfterRemboursement = traitementRembouresementProcessor.process(dossierAfterMapping);

        // Étape 4 : Calculer le total des remboursements
        RembAssure rembAssure = totalRembouresmentProcessor.process(dossierAfterRemboursement);

        // Si des champs supplémentaires sont à définir, les traiter ici
        rembAssure.setNomAssure(dossierAfterRemboursement.getNomAssure());
        rembAssure.setNumeroAffiliation(dossierAfterRemboursement.getNumeroAffiliation());
        rembAssure.setImmatriculation(dossierAfterRemboursement.getImmatriculation());

        return rembAssure;
    }
}
