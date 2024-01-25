package okazy.service.user;

import okazy.config.JWTManager;
import okazy.model.user.Utilisateur;
import okazy.repository.user.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final JWTManager jwt;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, JWTManager jwt) {
        this.utilisateurRepository = utilisateurRepository;
        this.jwt = jwt;
    }

    public Optional<Utilisateur> findByUserName(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    public String login(String username, String password) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findByUsernamePassword(username, password)
                .orElseThrow(() -> new Exception("Identifiants invalides"));

        return jwt.generateToken(utilisateur);
    }

}
