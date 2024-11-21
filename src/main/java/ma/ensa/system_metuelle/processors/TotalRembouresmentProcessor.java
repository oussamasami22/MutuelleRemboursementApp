package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import ma.ensa.system_metuelle.models.Traitement;
import org.springframework.batch.item.ItemProcessor;

public class TotalRembouresmentProcessor implements ItemProcessor<Dossier, RembAssure> {

    @Override
    public RembAssure process(Dossier item) throws Exception {
        double totalTembouresement = item.getRobouresementConsultation();
          for (Traitement traitement: item.getTraitements()){
              if(traitement.getExiste()){
                  totalTembouresement += traitement.getMontantRemboursement();
              }
          }
          RembAssure rembAssure = new RembAssure();
          rembAssure.setNomAssure(item.getNomAssure());
          rembAssure.setImmatriculation(item.getImmatriculation());
          rembAssure.setNumeroAffiliation(item.getNumeroAffiliation());
          rembAssure.setTotalRembouresement(totalTembouresement);
        return rembAssure;
    }
}
