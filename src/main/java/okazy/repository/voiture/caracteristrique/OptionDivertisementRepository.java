package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.OptionDivertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionDivertisementRepository extends JpaRepository<OptionDivertisement, Integer> {
}
