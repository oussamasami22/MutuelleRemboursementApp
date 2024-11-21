package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import org.springframework.batch.item.ItemProcessor;

public class TraitementMappingProcessor implements ItemProcessor<Dossier,Dossier> {
    @Override
    public Dossier process(Dossier item) throws Exception {
        return null;
    }
}
