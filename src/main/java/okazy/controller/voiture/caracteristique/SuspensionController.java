package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Suspension;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.SuspensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suspensions")
public class SuspensionController {

    private final SuspensionService suspensionService;

    @Autowired
    public SuspensionController(SuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<Suspension> suspensions = suspensionService.findAll();
        return new ResponseEntity<>(new Result("OK", "", suspensions), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<Suspension> suspension = suspensionService.findById(id);
        return suspension.map( value -> new ResponseEntity<>(new Result("OK", "", suspension), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody Suspension suspension) {
        Suspension m = suspensionService.save(suspension);
        return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody Suspension suspension) {
        Suspension m = suspensionService.update(id, suspension);
        return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<Suspension> suspension = suspensionService.findById(id);

        if (suspension.isPresent()) {
            suspensionService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
