package okazy.repository.voiture.caracteristrique;

import okazy.model.voiture.caracteristique.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Integer> {
}
