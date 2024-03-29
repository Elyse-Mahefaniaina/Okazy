package okazy.controller.user;

import okazy.model.user.Utilisateur;
import okazy.result.Result;
import okazy.service.user.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomUtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public CustomUtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/count")
    public ResponseEntity<Result> signup() {
        try {
            List<Utilisateur> utilisateurs = this.utilisateurService.findAll();
            return new ResponseEntity<>(new Result("OK", "", utilisateurs.size()-1), HttpStatus.OK);
        }catch (Exception e ) {
            return new ResponseEntity<>(new Result("Error occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
