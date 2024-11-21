package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import ma.ensa.system_metuelle.models.Traitement;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class TraitementMappingProcessor implements ItemProcessor<Dossier,Dossier> {
    @Autowired
    private  IMedRefService medRefService;
    @Override
    public Dossier process(Dossier item) throws Exception {

        for(Traitement traitement : item.getTraitements()){
            MedicamentReferentiel ref=medRefService.getTraitement(traitement.getCodeBarre());
            traitement.setMedicamentReferentiel(ref);
            traitement.setExiste(ref != null);
        }
        return item;
}
