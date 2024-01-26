package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.OptionDivertissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionDivertisementRepository extends JpaRepository<OptionDivertissement, Integer> {
}
