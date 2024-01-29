package okazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavorisRepository extends JpaRepository<okazy.model.Favoris, Integer> {

    @Query(value = "DELETE FROM Favoris f WHERE f.idannonce = ?1")
    void deleteByIdannonce(int idannonce);
}
