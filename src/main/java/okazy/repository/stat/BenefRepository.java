package okazy.repository.stat;

import okazy.model.stat.Benef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenefRepository extends JpaRepository<Benef, Integer> {

}
