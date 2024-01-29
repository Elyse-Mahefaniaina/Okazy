package okazy.service.stat;

import okazy.model.stat.Benef;
import okazy.repository.stat.BenefRepository;
import okazy.service.voiture.caracteristique.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BenefService {

    private final BenefRepository benefRepository;
    private final MarqueService marqueService;

    @Autowired
    public BenefService(BenefRepository benefRepository, MarqueService marqueService) {
        this.benefRepository = benefRepository;
        this.marqueService = marqueService;
    }

    public List<Benef> findAll() {
        List<Benef> benefs =  this.benefRepository.findAll();
        for (Benef b : benefs) {
            b.setMarque(this.marqueService.findById(b.getIdmarque()).get());
        }

        return benefs;
    }
}
