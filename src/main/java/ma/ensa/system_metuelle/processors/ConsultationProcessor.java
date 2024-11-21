package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import org.springframework.batch.item.ItemProcessor;

public class ConsultationProcessor implements ItemProcessor<Dossier, Dossier> {
  private final double tauxConsultation = 0.7;
    @Override
    public Dossier process(Dossier item) throws Exception {
        double remboursementConsultation = item.getPrixConsultation()*tauxConsultation;
        item.setRobouresementConsultation(remboursementConsultation);
        return item;
    }
}
