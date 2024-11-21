package ma.ensa.system_metuelle.processors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor @NoArgsConstructor
public class CompositeItemProcessor implements ItemProcessor<Dossier, RembAssure> {

    @Autowired
    private ValidationProcessor validationProcessor;
    @Autowired
    private CalculProcessor calculProcessor;

    @Override
    public RembAssure process(Dossier item) throws Exception {
        Dossier dossier = validationProcessor.process(item);
        RembAssure rembAssure = calculProcessor.process(dossier);
        return rembAssure;
    }
}