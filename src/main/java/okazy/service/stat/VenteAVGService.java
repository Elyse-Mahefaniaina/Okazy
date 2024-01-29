package okazy.service.stat;

import okazy.model.Vente;
import okazy.model.stat.VenteAVG;
import okazy.repository.stat.VenteAVGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenteAVGService {

    private final VenteAVGRepository venteAVGRepository;

    @Autowired
    public VenteAVGService(VenteAVGRepository venteAVGRepository) {
        this.venteAVGRepository = venteAVGRepository;
    }

    public List<VenteAVG> findAll() {
        List<VenteAVG> venteAVGS = this.venteAVGRepository.findAll();

        for (VenteAVG v : venteAVGS) {
            v.setStr_mois(v.getMois(v.getMois()));
        }

        return venteAVGS;
    }
}
