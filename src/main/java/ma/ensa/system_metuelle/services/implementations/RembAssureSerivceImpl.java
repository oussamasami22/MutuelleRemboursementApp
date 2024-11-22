package ma.ensa.system_metuelle.services.implementations;

import lombok.RequiredArgsConstructor;
import ma.ensa.system_metuelle.models.RembAssure;
import ma.ensa.system_metuelle.repositories.RembAssureRepository;
import ma.ensa.system_metuelle.services.interfaces.IRembAssureService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RembAssureSerivceImpl implements IRembAssureService {
    private final RembAssureRepository rembAssureRepository;
    @Override
    public RembAssure addRembAssure(RembAssure rembAssure) {
        return rembAssureRepository.save(rembAssure);
    }
}
