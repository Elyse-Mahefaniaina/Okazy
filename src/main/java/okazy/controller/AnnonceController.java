package okazy.controller;

import okazy.model.Annonce;
import okazy.model.Favoris;
import okazy.model.Vente;
import okazy.model.user.Utilisateur;
import okazy.result.Result;
import okazy.service.AnnonceService;
import okazy.service.FavorisService;
import okazy.service.VenteService;
import okazy.service.user.UtilisateurService;
import okazy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;
    private final FavorisService favorisService;
    private final UtilisateurService utilisateurService;
    private final VenteService venteService;

    @Autowired
    public AnnonceController(AnnonceService annonceService, FavorisService favorisService, UtilisateurService utilisateurService, VenteService venteService) {
        this.annonceService = annonceService;
        this.favorisService = favorisService;
        this.utilisateurService = utilisateurService;
        this.venteService = venteService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        try {
            List<Annonce> annonces = this.annonceService.findAll();
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An error has occured",e.getMessage(), ""), HttpStatus.OK);
        }
    }

    @GetMapping("/advenced")
    public ResponseEntity<Result> findAllAdvenced(@RequestParam(required = false) Double prixmin,
                                                  @RequestParam(required = false) Double prixmax,
                                                  @RequestParam(required = false) String boiteVitesse,
                                                  @RequestParam(required = false) String sourceEnergie,
                                                  @RequestParam(required = false) String suspension,
                                                  @RequestParam(required = false) String systemDirection,
                                                  @RequestParam(required = false) String systemeFreinage,
                                                  @RequestParam(required = false) String optionDivertisements,
                                                  @RequestParam(required = false) String optionSecurites,
                                                  @RequestParam(required = false) String marque,
                                                  @RequestParam(required = false) String model,
                                                  @RequestParam(required = false) Double puissancefiscale,
                                                  @RequestParam(required = false) Double cylindre,
                                                  @RequestParam(required = false) Double puissancemoteur,
                                                  @RequestParam(required = false) String cassis,
                                                  @RequestParam(required = false) Integer nombreporte,
                                                  @RequestParam(required = false) Integer nombreplace,
                                                  @RequestParam(required = false) String miseencirculation,
                                                  @RequestParam(required = false) String date ) {
        try {
            List<Annonce> annonces = this.annonceService.findAdvenced(prixmin, prixmax, boiteVitesse, sourceEnergie, suspension, systemDirection, systemeFreinage, optionDivertisements, optionSecurites, marque, model, puissancefiscale, cylindre, puissancemoteur, cassis, nombreporte, nombreplace, Util.parseToDate(miseencirculation), Util.parseToDate(date));
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An error has occured",e.getMessage(), ""), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<Annonce> annonce = this.annonceService.findById(id);
        return annonce.map( values -> new ResponseEntity<>(new Result("OK", "", annonce), HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}/addFavorite")
    public ResponseEntity<Result> ajoutFavorite(@PathVariable int id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateur = this.utilisateurService.findByUserName(userDetails.getUsername());

        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();

            Favoris favoris = new Favoris();
            favoris.setIdannonce(id);
            favoris.setIdutilisateur(user.getId());

            this.favorisService.save(favoris);

            return new ResponseEntity<>(new Result("Added to favorite","", ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result("NOT FOUND", "Authenticated user not found", ""), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/removeFavorite")
    public ResponseEntity<Result> deketeFavorite(@PathVariable int id) {
        try {
            this.favorisService.deleteByIdannonce(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/valides")
    public ResponseEntity<Result> findAllValide() {
        try {
            List<Annonce> annonces = this.annonceService.findAllValide();
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/valides/count")
    public ResponseEntity<Result> findAllValideCount() {
        try {
            List<Annonce> annonces = this.annonceService.findAllValide();
            return new ResponseEntity<>(new Result("OK","", annonces.size()), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nonvalides")
    public ResponseEntity<Result> findAllNonValide() {
        try {
            List<Annonce> annonces = this.annonceService.findAllNonValide();
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nonvalides/count")
    public ResponseEntity<Result> findAllNonValideCount() {
        try {
            List<Annonce> annonces = this.annonceService.findAllNonValide();
            return new ResponseEntity<>(new Result("OK","", annonces.size()), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/utilisateur/favoris")
    public ResponseEntity<Result> findAllFavoriteByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateur = this.utilisateurService.findByUserName(userDetails.getUsername());

        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();
            List<Annonce> annonces = this.annonceService.findAllFavoriteByUser(user.getId());
            return new ResponseEntity<>(new Result("OK","", annonces), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result("NOT FOUND", "Authenticated user not found", ""), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/validate/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> validate(@PathVariable int id){
        try {
            Annonce annonce = this.annonceService.validate(id);
            return new ResponseEntity<>(new Result("OK","", annonce), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("Modification Failure", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/vendre/{id}")
    @Transactional
    public ResponseEntity<Result> vendre(@PathVariable int id){
        try {
            Annonce annonce = this.annonceService.vendre(id);

            Vente vente = new Vente();
            vente.setIdannonce(annonce.getId());
            vente.setDate(new Date(new java.util.Date().getTime()));
            this.venteService.save(vente);

            return new ResponseEntity<>(new Result("OK","", annonce), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("Modification Failure", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<Result> save(@RequestBody Annonce annonce) {
        try {
            Annonce createdAnnonce = annonceService.save(annonce);
            return new ResponseEntity<>(new Result("Created", "", createdAnnonce), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An error occured", e.getMessage(), ""), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateVoiture(@PathVariable int id, @RequestBody Annonce annonce) {
        try {
            Optional<Annonce> existingAnnonce = annonceService.findById(id);

            if (existingAnnonce.isPresent()) {
                Annonce updateAnnonce = annonceService.update(id, annonce);
                return new ResponseEntity<>(new Result("Updated", "", updateAnnonce), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> deleteAnnonce(@PathVariable int id) {
        try {
            Optional<Annonce> existingAnnonce = annonceService.findById(id);
            if (existingAnnonce.isPresent()) {
                annonceService.delete(id);
                return new ResponseEntity<>(new Result("Deleted", "", ""), HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
