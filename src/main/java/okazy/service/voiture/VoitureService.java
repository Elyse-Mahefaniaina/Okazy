package okazy.service.voiture;

import okazy.model.voiture.Voiture;
import okazy.repository.voiture.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    @Autowired
    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    public List<Voiture> findAll() {
        return voitureRepository.findAll();
    }

    public Optional<Voiture> findById(int id) {
        return voitureRepository.findById(id);
    }

    public Voiture insert(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public Voiture update(int id, Voiture voiture) {
        voiture.setId(id);
        return voitureRepository.save(voiture);
    }

    public void delete(int id)  {
        voitureRepository.deleteById(id);
    }
}
