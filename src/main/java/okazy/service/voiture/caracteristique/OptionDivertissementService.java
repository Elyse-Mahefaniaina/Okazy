package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.OptionDivertissement;
import okazy.repository.voiture.caracteristrique.OptionDivertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OptionDivertissementService {

    private final OptionDivertisementRepository optionDivertisementRepository;

    @Autowired
    public OptionDivertissementService(OptionDivertisementRepository boiteVitesseRepository) {
        this.optionDivertisementRepository = boiteVitesseRepository;
    }

    public List<OptionDivertissement> findAll() {
        return optionDivertisementRepository.findAll();
    }

    public Optional<OptionDivertissement> findById(int id) {
        return optionDivertisementRepository.findById(id);
    }

    public OptionDivertissement save(OptionDivertissement optionDivertisement) {
        return optionDivertisementRepository.save(optionDivertisement);
    }

    public OptionDivertissement update(int id, OptionDivertissement optionDivertisement) {
        optionDivertisement.setId(id);
        return optionDivertisementRepository.save(optionDivertisement);
    }

    public void delete(int id){
        optionDivertisementRepository.deleteById(id);
    }
}
