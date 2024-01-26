package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SystemeFreinage;
import okazy.repository.voiture.caracteristrique.SystemFreinageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SystemFreinageService {

    private final SystemFreinageRepository systemFreinageRepository;

    @Autowired
    public SystemFreinageService(SystemFreinageRepository systemFreinageRepository) {
        this.systemFreinageRepository = systemFreinageRepository;
    }

    public List<SystemeFreinage> findAll() {
        return systemFreinageRepository.findAll();
    }

    public Optional<SystemeFreinage> findById(int id) {
        return systemFreinageRepository.findById(id);
    }

    public SystemeFreinage save(SystemeFreinage systemeFreinage) {
        return systemFreinageRepository.save(systemeFreinage);
    }

    public SystemeFreinage update(int id, SystemeFreinage systemeFreinage) {
        systemeFreinage.setId(id);
        return systemFreinageRepository.save(systemeFreinage);
    }

    public void delete(int id){
        systemFreinageRepository.deleteById(id);
    }
}
