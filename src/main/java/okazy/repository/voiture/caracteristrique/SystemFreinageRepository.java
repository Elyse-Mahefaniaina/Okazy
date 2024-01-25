package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.SystemeFreinage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemFreinageRepository extends JpaRepository<SystemeFreinage, Integer> {
}
