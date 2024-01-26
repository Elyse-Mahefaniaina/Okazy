package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SourceEnergie;
import okazy.repository.voiture.caracteristrique.SourceEnergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SourceEnergieService {

    private final SourceEnergieRepository sourceEnergieRepository;

    @Autowired
    public SourceEnergieService(SourceEnergieRepository sourceEnergieRepository) {
        this.sourceEnergieRepository = sourceEnergieRepository;
    }

    public List<SourceEnergie> findAll() {
        return sourceEnergieRepository.findAll();
    }

    public Optional<SourceEnergie> findById(int id) {
        return sourceEnergieRepository.findById(id);
    }

    public SourceEnergie save(SourceEnergie sourceEnergie) {
        return sourceEnergieRepository.save(sourceEnergie);
    }

    public SourceEnergie update(int id, SourceEnergie sourceEnergie) {
        sourceEnergie.setId(id);
        return sourceEnergieRepository.save(sourceEnergie);
    }

    public void delete(int id){
        sourceEnergieRepository.deleteById(id);
    }
}
