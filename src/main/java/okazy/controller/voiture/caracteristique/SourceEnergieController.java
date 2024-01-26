package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SourceEnergie;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.SourceEnergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sourceenergies")
public class SourceEnergieController {

    private final SourceEnergieService sourceEnergieService;

    @Autowired
    public SourceEnergieController(SourceEnergieService sourceEnergieService) {
        this.sourceEnergieService = sourceEnergieService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<SourceEnergie> sourceEnergies = sourceEnergieService.findAll();
        return new ResponseEntity<>(new Result("OK", "", sourceEnergies), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<SourceEnergie> sourceEnergie = sourceEnergieService.findById(id);
        return sourceEnergie.map( value -> new ResponseEntity<>(new Result("OK", "", sourceEnergie), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody SourceEnergie sourceEnergie) {
        SourceEnergie m = sourceEnergieService.save(sourceEnergie);
        return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody SourceEnergie sourceEnergie) {
        SourceEnergie m = sourceEnergieService.update(id, sourceEnergie);
        return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<SourceEnergie> sourceEnergie = sourceEnergieService.findById(id);

        if (sourceEnergie.isPresent()) {
            sourceEnergieService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
