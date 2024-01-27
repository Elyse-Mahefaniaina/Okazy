package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SystemFreinage;
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

    public List<SystemFreinage> findAll() {
        return systemFreinageRepository.findAll();
    }

    public Optional<SystemFreinage> findById(int id) {
        return systemFreinageRepository.findById(id);
    }

    public SystemFreinage save(SystemFreinage systemeFreinage) {
        return systemFreinageRepository.save(systemeFreinage);
    }

    public SystemFreinage update(int id, SystemFreinage systemeFreinage) {
        systemeFreinage.setId(id);
        return systemFreinageRepository.save(systemeFreinage);
    }

    public void delete(int id){
        systemFreinageRepository.deleteById(id);
    }
}
