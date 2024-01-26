package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.OptionSecurite;
import okazy.repository.voiture.caracteristrique.OptionSecuriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OptionSecuriteService {

    private final OptionSecuriteRepository optionSecuriteRepository;

    @Autowired
    public OptionSecuriteService(OptionSecuriteRepository optionSecuriteRepository) {
        this.optionSecuriteRepository = optionSecuriteRepository;
    }

    public List<OptionSecurite> findAll() {
        return optionSecuriteRepository.findAll();
    }

    public Optional<OptionSecurite> findById(int id) {
        return optionSecuriteRepository.findById(id);
    }

    public OptionSecurite save(OptionSecurite optionSecurite) {
        return optionSecuriteRepository.save(optionSecurite);
    }

    public OptionSecurite update(int id, OptionSecurite optionSecurite) {
        optionSecurite.setId(id);
        return optionSecuriteRepository.save(optionSecurite);
    }

    public void delete(int id){
        optionSecuriteRepository.deleteById(id);
    }
}
