package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
}
