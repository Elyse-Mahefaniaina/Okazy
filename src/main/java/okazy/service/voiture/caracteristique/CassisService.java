package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Cassis;
import okazy.repository.voiture.caracteristrique.CassisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CassisService {

    private final CassisRepository cassisRepository;

    @Autowired
    public CassisService(CassisRepository boiteVitesseRepository) {
        this.cassisRepository = boiteVitesseRepository;
    }

    public List<Cassis> findAll() {
        return cassisRepository.findAll();
    }

    public Optional<Cassis> findById(int id) {
        return cassisRepository.findById(id);
    }

    public Cassis save(Cassis cassis) {
        return cassisRepository.save(cassis);
    }

    public Cassis update(int id, Cassis cassis) {
        cassis.setId(id);
        return cassisRepository.save(cassis);
    }

    public void delete(int id){
        cassisRepository.deleteById(id);
    }
}
