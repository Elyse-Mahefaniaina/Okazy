package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Suspension;
import okazy.repository.voiture.caracteristrique.SuspensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SuspensionService {

    private final SuspensionRepository suspensionRepository;

    @Autowired
    public SuspensionService(SuspensionRepository suspensionRepository) {
        this.suspensionRepository = suspensionRepository;
    }

    public List<Suspension> findAll() {
        return suspensionRepository.findAll();
    }

    public Optional<Suspension> findById(int id) {
        return suspensionRepository.findById(id);
    }

    public Suspension save(Suspension suspension) {
        return suspensionRepository.save(suspension);
    }

    public Suspension update(int id, Suspension suspension) {
        suspension.setId(id);
        return suspensionRepository.save(suspension);
    }

    public void delete(int id){
        suspensionRepository.deleteById(id);
    }
}
