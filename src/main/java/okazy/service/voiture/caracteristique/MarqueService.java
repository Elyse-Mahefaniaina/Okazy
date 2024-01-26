package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Marque;
import okazy.repository.voiture.caracteristrique.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MarqueService {

    private final MarqueRepository marqueRepository;

    @Autowired
    public MarqueService(MarqueRepository boiteVitesseRepository) {
        this.marqueRepository = boiteVitesseRepository;
    }

    public List<Marque> findAll() {
        return marqueRepository.findAll();
    }

    public Optional<Marque> findById(int id) {
        return marqueRepository.findById(id);
    }

    public Marque save(Marque marque) {
        return marqueRepository.save(marque);
    }

    public Marque update(int id, Marque marque) {
        marque.setId(id);
        return marqueRepository.save(marque);
    }

    public void delete(int id){
        marqueRepository.deleteById(id);
    }
}
