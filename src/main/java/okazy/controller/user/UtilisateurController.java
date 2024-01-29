package okazy.controller.user;

import okazy.model.user.Utilisateur;
import okazy.result.Result;
import okazy.service.user.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<Result> signup(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur user = this.utilisateurService.save(utilisateur);
            return new ResponseEntity<>(new Result("OK", null, utilisateurService.login(user.getUsername(), user.getPassword())), HttpStatus.OK);
        }catch (Exception e ) {
            return new ResponseEntity<>(new Result("Error occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
