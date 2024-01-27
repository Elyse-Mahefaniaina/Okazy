package okazy.repository;

import okazy.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

    @Query(value = "SELECT * FROM annonce WHERE state = ?1", nativeQuery = true)
    List<Annonce> findByState(int state);

    @Query(value = "SELECT * FROM annonce WHERE idutilisateur = ?1", nativeQuery = true)
    List<Annonce> findByIdutilisateur(int idutilisateur);

}
