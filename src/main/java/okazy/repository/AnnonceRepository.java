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

    @Query(value = """
        SELECT
            a
        FROM Annonce a
            LEFT JOIN a.optionDivertisements od
            LEFT JOIN a.optionSecurites os
        WHERE
            (:prixmin IS NULL OR a.prix <= :prixmin) AND
            (:prixmax IS NULL OR a.prix >= :prixmax) AND
            (:boiteVitesse IS NULL OR a.boiteVitesse.nom = :boiteVitesse) AND
            (:sourceEnergie IS NULL OR a.sourceEnergie.nom = :sourceEnergie) AND
            (:suspension IS NULL OR a.suspension.nom = :suspension) AND
            (:systemDirection IS NULL OR a.systemDirection.nom = :systemDirection) AND
            (:systemeFreinage IS NULL OR a.systemeFreinage.nom = :systemeFreinage) AND
            (:optionDivertisements IS NULL OR od.nom = :optionDivertisements) AND
            (:optionSecurites IS NULL OR os.nom = :optionSecurites) AND
            (:marque IS NULL OR a.voiture.marque.nom = :marque) AND
            (:model IS NULL OR a.voiture.model.nom = :model) AND
            (:puissancefiscale IS NULL OR a.voiture.puissancefiscale = :puissancefiscale) AND
            (:cylindre IS NULL OR a.voiture.cylindre = :cylindre) AND
            (:puissancemoteur IS NULL OR a.voiture.puissancemoteur = :puissancemoteur) AND
            (:cassis IS NULL OR a.voiture.cassis.nom = :cassis) AND
            (:nombreporte IS NULL OR a.voiture.nombreporte = :nombreporte) AND
            (:nombreplace IS NULL OR a.voiture.nombreplace = :nombreplace) AND
            (CAST(:miseencirculation AS CHARACTER ) IS NULL OR a.voiture.miseencirculation = :miseencirculation) AND
            (CAST(:date AS CHARACTER ) IS NULL OR a.date = :date) AND
            (a.state = 11)
    """)
    List<Annonce> findAdvenced(
            @Param("prixmin") Double prixmin,
            @Param("prixmax") Double prixmax,
            @Param("boiteVitesse") String boiteVitesse,
            @Param("sourceEnergie") String sourceEnergie,
            @Param("suspension") String suspension,
            @Param("systemDirection") String systemDirection,
            @Param("systemeFreinage") String systemeFreinage,
            @Param("optionDivertisements") String optionDivertisements,
            @Param("optionSecurites") String optionSecurites,
            @Param("marque") String marque,
            @Param("model") String model,
            @Param("puissancefiscale") Double puissancefiscale,
            @Param("cylindre") Double cylindre,
            @Param("puissancemoteur") Double puissancemoteur,
            @Param("cassis") String cassis,
            @Param("nombreporte") Integer nombreporte,
            @Param("nombreplace") Integer nombreplace,
            @Param("miseencirculation") Date miseencirculation,
            @Param("date") Date date
    );

    @Query(value = "SELECT a FROM Annonce a WHERE a.state = ?1")
    List<Annonce> findByState(int state);

    @Query(value = "SELECT a FROM Annonce a WHERE a.utilisateur.id = ?1")
    List<Annonce> findByIdutilisateur(int idutilisateur);

    @Query(value = """
        SELECT
            a
        FROM Annonce a
            INNER JOIN Favoris f ON f.idannonce = a.id
        WHERE f.idutilisateur = ?1
        
    """
    )
    List<Annonce> findAllFavorisUtilisateur(int idutilisateur);
}
