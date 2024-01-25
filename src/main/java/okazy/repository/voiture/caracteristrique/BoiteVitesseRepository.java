package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.BoiteVitesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoiteVitesseRepository extends JpaRepository<BoiteVitesse, Integer> {
}
