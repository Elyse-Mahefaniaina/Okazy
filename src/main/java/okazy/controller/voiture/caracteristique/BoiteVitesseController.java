package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.BoiteVitesse;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.BoiteVitesseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boitevitesses")
public class BoiteVitesseController {

    private final BoiteVitesseService boiteVitesseService;

    @Autowired
    public BoiteVitesseController(BoiteVitesseService boiteVitesseService) {
        this.boiteVitesseService = boiteVitesseService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<BoiteVitesse> boiteVitesses = boiteVitesseService.findAll();
        return new ResponseEntity<>(new Result("OK", "", boiteVitesses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<BoiteVitesse> boiteVitesse = boiteVitesseService.findById(id);
        return boiteVitesse.map( value -> new ResponseEntity<>(new Result("OK", "", boiteVitesse), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody BoiteVitesse boiteVitesse) {
        BoiteVitesse bv = boiteVitesseService.save(boiteVitesse);
        return new ResponseEntity<>(new Result("CREATED", "", bv), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody BoiteVitesse boiteVitesse) {
        BoiteVitesse bv = boiteVitesseService.update(id, boiteVitesse);
        return new ResponseEntity<>(new Result("UPDATED", "", bv), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<BoiteVitesse> boiteVitesse = boiteVitesseService.findById(id);

        if (boiteVitesse.isPresent()) {
            boiteVitesseService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
