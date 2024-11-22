package ma.ensa.system_metuelle.services.interfaces;

import ma.ensa.system_metuelle.exceptions.ResourceAlreadyExistException;
import ma.ensa.system_metuelle.exceptions.ResourceNotFoundException;
import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import java.util.List;
public interface IMedRefService {
     MedicamentReferentiel addTraitemnt(MedicamentReferentiel traitement) throws ResourceAlreadyExistException;
     MedicamentReferentiel getTraitement(String codeBarre) throws ResourceNotFoundException;
     MedicamentReferentiel updateTraitemnt(MedicamentReferentiel traitement ,String codeBarre) throws ResourceNotFoundException;
     void addMultipleMedicaments(List<MedicamentReferentiel> medicaments) throws ResourceAlreadyExistException;
    List<MedicamentReferentiel> getAllMedicaments();
    void deleteTraitement(String id);
}
