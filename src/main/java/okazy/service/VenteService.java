package okazy.service;

import okazy.model.Vente;
import okazy.repository.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteService {

    private final VenteRepository venteRepository;

    @Autowired
    public VenteService(VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
    }

    public void save(Vente vente) {
        this.venteRepository.save(vente);
    }

    public List<Vente> findAll() {
        return this.venteRepository.findAll();
    }
}
