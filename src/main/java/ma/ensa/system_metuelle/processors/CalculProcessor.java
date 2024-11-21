package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculProcessor implements ItemProcessor<Dossier, RembAssure> {
    @Autowired
    private ConsultationProcessor consultationProcessor;
    @Autowired
    private TraitementMappingProcessor traitementMappingProcessor;
    @Autowired
    private TraitementRembouresementProcessor traitementRembouresementProcessor;
    @Autowired
    private TotalRembouresmentProcessor totalRembouresmentProcessor;
    public RembAssure process(Dossier item) throws Exception {
        return null;
    }
}
