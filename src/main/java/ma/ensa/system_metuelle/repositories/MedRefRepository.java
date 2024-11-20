package ma.ensa.system_metuelle.repositories;

import ma.ensa.system_metuelle.models.MedicamentReferentiel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedRefRepository extends JpaRepository<MedicamentReferentiel, String> {
    boolean existsByCodeBarre(String codeBarre);
    MedicamentReferentiel findByCodeBarre(String codeBarre);

}
