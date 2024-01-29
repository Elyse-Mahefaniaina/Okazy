package okazy.repository.voiture;

import okazy.model.voiture.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
    @Query("""
        SELECT
        v
        FROM Voiture v
        WHERE
            (:marque IS NULL OR v.marque.nom = :marque) AND
            (:model IS NULL OR v.model.nom = :model) AND
            (:puissancefiscale IS NULL OR v.puissancefiscale = :puissancefiscale) AND
            (:cylindre IS NULL OR v.cylindre = :cylindre) AND
            (:puissancemoteur IS NULL OR v.puissancemoteur = :puissancemoteur) AND
            (:cassis IS NULL OR v.cassis.nom = :cassis) AND
            (:nombreporte IS NULL OR v.nombreporte = :nombreporte) AND
            (:nombreplace IS NULL OR v.nombreplace = :nombreplace) AND
            (CAST(:miseencirculation AS CHARACTER ) IS NULL OR v.miseencirculation=:miseencirculation)
             
    """)
    List<Voiture> rechercheParMotCle(
            @Param("marque") String marque,
            @Param("model") String model,
            @Param("puissancefiscale") Double puissancefiscale,
            @Param("cylindre") Double cylindre,
            @Param("puissancemoteur") Double puissancemoteur,
            @Param("cassis") String cassis,
            @Param("nombreporte") Integer nombreporte,
            @Param("nombreplace") Integer nombreplace,
            @Param("miseencirculation") Date miseencirculation
    );
}
