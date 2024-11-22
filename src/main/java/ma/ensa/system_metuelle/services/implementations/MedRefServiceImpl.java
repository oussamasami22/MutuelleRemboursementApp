package ma.ensa.system_metuelle.services.implementations;

import lombok.RequiredArgsConstructor;
import ma.ensa.system_metuelle.exceptions.ResourceAlreadyExistException;
import ma.ensa.system_metuelle.exceptions.ResourceNotFoundException;
import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import ma.ensa.system_metuelle.repositories.MedRefRepository;
import ma.ensa.system_metuelle.services.interfaces.IMedRefService;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedRefServiceImpl implements IMedRefService {
    private final MedRefRepository medRefRepository;
    @Override
    public MedicamentReferentiel addTraitemnt(MedicamentReferentiel traitement) throws ResourceAlreadyExistException {
  if(medRefRepository.existsByCodeBarre(traitement.getCodeBarre())){
      throw new ResourceAlreadyExistException("Medicament Already exist !");
  }
  return medRefRepository.save(traitement);

    }

    @Override
    public MedicamentReferentiel getTraitement(String codeBarre) throws ResourceNotFoundException {

        return Optional.ofNullable(medRefRepository.findByCodeBarre(codeBarre)).orElse(null);
    }
    @Override
    public MedicamentReferentiel updateTraitemnt(MedicamentReferentiel traitement, String id) throws ResourceNotFoundException {
       MedicamentReferentiel traitement1 = getTraitement(id);
        traitement1.setCodeBarre(traitement.getCodeBarre());
        traitement1.setNomMedicament((traitement.getNomMedicament()));
        return medRefRepository.save(traitement1);
    }

    @Override
    public void addMultipleMedicaments(List<MedicamentReferentiel> medicaments) throws ResourceAlreadyExistException {
       for(MedicamentReferentiel med :medicaments) {
           addTraitemnt(med);
       }
    }

    @Override
    public List<MedicamentReferentiel> getAllMedicaments() {

        return medRefRepository.findAll();
    }

    @Override
    public void deleteTraitement(String id) {
        medRefRepository.findById(id).ifPresentOrElse(medRefRepository::delete, () ->
                new ResourceClosedException("le medicament avec id"+id+"n'as pas été trouvé")
        );

    }
}
