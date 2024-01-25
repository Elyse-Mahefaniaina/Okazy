package okazy.repository.user;

import okazy.model.user.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsername(String username);

    @Query( value = "SELECT * FROM utilisateur WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Optional<Utilisateur> findByUsernamePassword(String username, String password);
}
