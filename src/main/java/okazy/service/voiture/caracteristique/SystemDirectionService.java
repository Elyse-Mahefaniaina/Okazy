package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SystemDirection;
import okazy.repository.voiture.caracteristrique.SystemDirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SystemDirectionService {

    private final SystemDirectionRepository systemDirectionRepository;

    @Autowired
    public SystemDirectionService(SystemDirectionRepository suspensionRepository) {
        this.systemDirectionRepository = suspensionRepository;
    }

    public List<SystemDirection> findAll() {
        return systemDirectionRepository.findAll();
    }

    public Optional<SystemDirection> findById(int id) {
        return systemDirectionRepository.findById(id);
    }

    public SystemDirection save(SystemDirection systemDirection) {
        return systemDirectionRepository.save(systemDirection);
    }

    public SystemDirection update(int id, SystemDirection systemDirection) {
        systemDirection.setId(id);
        return systemDirectionRepository.save(systemDirection);
    }

    public void delete(int id){
        systemDirectionRepository.deleteById(id);
    }
}
