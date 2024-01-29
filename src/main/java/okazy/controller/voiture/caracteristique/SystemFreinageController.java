package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SystemFreinage;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.SystemFreinageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/systemfreinages")
public class SystemFreinageController {

    private final SystemFreinageService systemFreinageService;

    @Autowired
    public SystemFreinageController(SystemFreinageService suspensionService) {
        this.systemFreinageService = suspensionService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        try {
            List<SystemFreinage> systemeFreinages = systemFreinageService.findAll();
            return new ResponseEntity<>(new Result("OK", "", systemeFreinages), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        try {
            Optional<SystemFreinage> systemeFreinage = systemFreinageService.findById(id);
            return systemeFreinage.map( value -> new ResponseEntity<>(new Result("OK", "", systemeFreinage), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody SystemFreinage systemeFreinage) {
        try {
            SystemFreinage m = systemFreinageService.save(systemeFreinage);
            return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody SystemFreinage systemeFreinage) {
        try {
            SystemFreinage m = systemFreinageService.update(id, systemeFreinage);
            return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        try {
            Optional<SystemFreinage> systemeFreinage = systemFreinageService.findById(id);

            if (systemeFreinage.isPresent()) {
                systemFreinageService.delete(id);
                return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

}
