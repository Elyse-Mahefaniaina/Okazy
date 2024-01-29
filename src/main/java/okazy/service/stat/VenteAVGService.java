package okazy.service.stat;

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
        return this.venteAVGRepository.findAll();
    }
}
