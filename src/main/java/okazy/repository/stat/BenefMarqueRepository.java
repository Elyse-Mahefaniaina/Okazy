package okazy.repository.stat;

import okazy.model.stat.BenefMarque;
import okazy.model.voiture.caracteristique.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefMarqueRepository extends JpaRepository<BenefMarque, Marque> {

}
