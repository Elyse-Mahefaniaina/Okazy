package okazy.controller.user;

import okazy.model.user.Authentication;
import okazy.result.Result;
import okazy.service.user.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public AuthenticationController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Authentication user) {
        Result res;
        try {
            res = new Result("OK", null, utilisateurService.login(user.getUsername(), user.getPassword()));
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res = new Result(
                    "Authentication failed",
                    e.getMessage(),
                    null
            );
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
