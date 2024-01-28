package okazy.service;

import okazy.model.Favoris;
import okazy.repository.FavorisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavorisService {

    private final FavorisRepository favorisRepository;

    @Autowired
    public FavorisService(FavorisRepository favorisRepository) {
        this.favorisRepository = favorisRepository;
    }

    public Favoris save(Favoris favoris) {
        return this.favorisRepository.save(favoris);
    }

    public void deleteByIdannonce(int id) {
        this.favorisRepository.deleteByIdannonce(id);
    }
}
