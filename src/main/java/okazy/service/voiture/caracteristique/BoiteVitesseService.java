package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.BoiteVitesse;
import okazy.repository.voiture.caracteristrique.BoiteVitesseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BoiteVitesseService {

    private final BoiteVitesseRepository boiteVitesseRepository;

    @Autowired
    public BoiteVitesseService(BoiteVitesseRepository boiteVitesseRepository) {
        this.boiteVitesseRepository = boiteVitesseRepository;
    }

    public List<BoiteVitesse> findAll() {
        return boiteVitesseRepository.findAll();
    }

    public Optional<BoiteVitesse> findById(int id) {
        return boiteVitesseRepository.findById(id);
    }

    public BoiteVitesse save(BoiteVitesse boiteVitesse) {
        return boiteVitesseRepository.save(boiteVitesse);
    }

    public BoiteVitesse update(int id, BoiteVitesse boiteVitesse) {
        boiteVitesse.setId(id);
        return boiteVitesseRepository.save(boiteVitesse);
    }

    public void delete(int id){
        boiteVitesseRepository.deleteById(id);
    }
}
