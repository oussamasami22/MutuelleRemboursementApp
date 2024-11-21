package ma.ensa.system_metuelle.processors;

import ma.ensa.system_metuelle.exceptions.ValidationException;
import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationProcessor  implements ItemProcessor<Dossier, Dossier> {
   @Override
    public Dossier process(Dossier item) throws Exception{
       if(item.getNomAssure() == null || item.getNomAssure().isEmpty()){
           throw  new ValidationException("Nom de l'assuré est manquant !");
       }
       if(item.getNumeroAffiliation() ==null || item.getNumeroAffiliation().isEmpty()){
           throw new ValidationException(" Numero d'Affiliation est manquant !");
       }
       if(item.getPrixConsultation() <= 0){
           throw  new ValidationException(" le prix doit etre sup a 0 !");
       }
       if(item.getMontantTotalFrais() <= 0){
         throw new ValidationException("MontantTotalFrais doit etre sup a 0 !");
       }
       if(item.getTraitements() ==null || item.getTraitements().isEmpty()){
           throw new ValidationException("List de Traitemnts manquant !");
       }
   return item;
   }
}
