package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.SystemDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemDirectionRepository extends JpaRepository<SystemDirection, Integer> {
}
