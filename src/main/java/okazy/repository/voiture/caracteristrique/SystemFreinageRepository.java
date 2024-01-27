package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.SystemFreinage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemFreinageRepository extends JpaRepository<SystemFreinage, Integer> {
}
