package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.Cassis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassisRepository extends JpaRepository<Cassis, Integer> {
}
