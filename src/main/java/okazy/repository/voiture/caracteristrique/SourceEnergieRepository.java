package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.SourceEnergie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceEnergieRepository extends JpaRepository<SourceEnergie, Integer> {
}
