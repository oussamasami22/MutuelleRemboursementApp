package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.Traitement;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TraitementRembouresementProcessor implements ItemProcessor<Dossier, Dossier> {
    @Override
    public Dossier process(Dossier item) throws Exception {
        for(Traitement traitement:item.getTraitements()){
            if(traitement.getExiste()){
                 double prixRef =traitement.getMedicamentReferentiel().getPrixMedicament();
                 double tauxRef = traitement.getMedicamentReferentiel().getTauxRemboursement();
                 traitement.setMontantRemboursement(prixRef * tauxRef);

            }
        }

        return item;
    }
}
