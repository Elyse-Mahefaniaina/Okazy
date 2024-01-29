package okazy.repository.stat;

import okazy.model.stat.VenteAVG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteAVGRepository extends JpaRepository<VenteAVG, Integer> {
}
