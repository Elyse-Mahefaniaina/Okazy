package okazy.controller;

import okazy.model.Annonce;
import okazy.model.user.Utilisateur;
import okazy.result.Result;
import okazy.service.AnnonceService;
import okazy.service.user.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public AnnonceController(AnnonceService annonceService, UtilisateurService utilisateurService) {
        this.annonceService = annonceService;
        this.utilisateurService = utilisateurService;
    }


    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<Annonce> annonces = this.annonceService.findAll();
        return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<Annonce> annonce = this.annonceService.findById(id);
        return annonce.map( values -> new ResponseEntity<>(new Result("OK", "", annonce), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/valides")
    public ResponseEntity<Result> findAllValide() {
        List<Annonce> annonces = this.annonceService.findAllValide();
        return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
    }

    @GetMapping("/nonvalides")
    public ResponseEntity<Result> findAllNonValide() {
        List<Annonce> annonces = this.annonceService.findAllNonValide();
        return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
    }

    @GetMapping("/utilisateurs/{id}")
    public ResponseEntity<Result> findAllByUser(int id) {
        List<Annonce> annonces = this.annonceService.findAllByUser(id);
        return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
    }

    @GetMapping("/utilisateur")
    public ResponseEntity<Result> findAllByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateur = this.utilisateurService.findByUserName(userDetails.getUsername());

        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();
            List<Annonce> annonces = this.annonceService.findAllByUser(user.getId());
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result("NOT FOUND", "Authenticated user not found", ""), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/validate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> validate(@PathVariable int id){
        try {
            Annonce annonce = this.annonceService.validate(id);
            return new ResponseEntity<>(new Result("OK","", annonce), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("Modification Failure", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/vendre")
    public ResponseEntity<Result> vendre(@PathVariable int id){
        try {
            Annonce annonce = this.annonceService.vendre(id);
            return new ResponseEntity<>(new Result("OK","", annonce), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("Modification Failure", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Result> save(@RequestBody Annonce annonce) {
        Annonce createdAnnonce = annonceService.save(annonce);
        return new ResponseEntity<>(new Result("Created", "", createdAnnonce), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateVoiture(@PathVariable int id, @RequestBody Annonce annonce) {
        Optional<Annonce> existingAnnonce = annonceService.findById(id);

        if (existingAnnonce.isPresent()) {
            Annonce updateAnnonce = annonceService.update(id, annonce);
            return new ResponseEntity<>(new Result("Updated", "", updateAnnonce), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> deleteAnnonce(@PathVariable int id) {
        Optional<Annonce> existingAnnonce = annonceService.findById(id);
        if (existingAnnonce.isPresent()) {
            annonceService.delete(id);
            return new ResponseEntity<>(new Result("Deleted", "", ""), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
        }
    }
}