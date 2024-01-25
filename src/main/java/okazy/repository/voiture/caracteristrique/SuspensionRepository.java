package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.Suspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspensionRepository extends JpaRepository<Suspension, Integer> {
}
