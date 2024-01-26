package okazy.service.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Model;
import okazy.repository.voiture.caracteristrique.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository boiteVitesseRepository) {
        this.modelRepository = boiteVitesseRepository;
    }

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Optional<Model> findById(int id) {
        return modelRepository.findById(id);
    }

    public Model save(Model model) {
        return modelRepository.save(model);
    }

    public Model update(int id, Model model) {
        model.setId(id);
        return modelRepository.save(model);
    }

    public void delete(int id){
        modelRepository.deleteById(id);
    }
}
