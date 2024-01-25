package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.OptionSecurite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionSecuriteRepository extends JpaRepository<OptionSecurite, Integer> {
}
