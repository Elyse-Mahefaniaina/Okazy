package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Marque;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marques")
public class MarqueController {

    private final MarqueService marqueService;

    @Autowired
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<Marque> casses = marqueService.findAll();
        return new ResponseEntity<>(new Result("OK", "", casses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<Marque> marque = marqueService.findById(id);
        return marque.map( value -> new ResponseEntity<>(new Result("OK", "", marque), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody Marque marque) {
        Marque c = marqueService.save(marque);
        return new ResponseEntity<>(new Result("CREATED", "", c), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody Marque marque) {
        Marque m = marqueService.update(id, marque);
        return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<Marque> marque = marqueService.findById(id);

        if (marque.isPresent()) {
            marqueService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
