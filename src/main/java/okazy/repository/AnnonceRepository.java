package okazy.repository;

import okazy.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

    @Query("SELECT DISTINCT a FROM Annonce a " +
            "JOIN FETCH a.voiture v " +
            "JOIN FETCH v.marque m " +
            "JOIN FETCH v.model mo " +
            "JOIN FETCH a.systemDirection sd " +
            "JOIN FETCH a.systemeFreinage sf " +
            "JOIN FETCH a.boiteVitesse bv " +
            "JOIN FETCH a.sourceEnergie se " +
            "JOIN FETCH a.suspension su " +
            "LEFT JOIN FETCH a.optionDivertisements od " +
            "LEFT JOIN FETCH a.optionSecurites os " +
            "WHERE " +
            "(:motCle IS NULL OR a.titre LIKE CONCAT('%', :motCle, '%') OR a.description LIKE CONCAT('%', :motCle, '%')) " +
            "AND (:dateMin IS NULL OR a.date >= :dateMin) " +
            "AND (:prixMin IS NULL OR a.prix >= :prixMin) " +
            "AND (:marque IS NULL OR m.nom = :marque) " +
            "AND (:modele IS NULL OR mo.nom = :modele)")
    List<Annonce> rechercheAvancee(
                            @Param("motCle") String motCle,
                            @Param("dateMin") Date dateMin,
                            @Param("prixMin") Double prixMin,
                            @Param("marque") String marque,
                            @Param("modele") String modele);

    @Query(value = "SELECT * FROM annonce WHERE state = ?1", nativeQuery = true)
    List<Annonce> findByState(int state);

    @Query(value = "SELECT * FROM annonce WHERE idutilisateur = ?1", nativeQuery = true)
    List<Annonce> findByIdutilisateur(int idutilisateur);

    @Query(value = "SELECT * FROM annonce WHERE id IN (SELECT idannonce FROM favoris WHERE idutilisateur ?1 )", nativeQuery = true)
    List<Annonce> findAllFavorisUtilisateur(int idutilisateur);
}
