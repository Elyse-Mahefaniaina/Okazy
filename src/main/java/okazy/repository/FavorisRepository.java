package okazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorisRepository extends JpaRepository<okazy.model.Favoris, Integer> {

    @Query(value = "DELETE FROM favoris WHERE idannonce = ?1", nativeQuery = true)
    void deleteByIdannonce(int idannonce);
}
