package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.SystemDirection;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.SystemDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/systemdirections")
public class SystemDirectionController {

    private final SystemDirectionService systemDirectionService;

    @Autowired
    public SystemDirectionController(SystemDirectionService suspensionService) {
        this.systemDirectionService = suspensionService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<SystemDirection> systemDirections = systemDirectionService.findAll();
        return new ResponseEntity<>(new Result("OK", "", systemDirections), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<SystemDirection> systemDirection = systemDirectionService.findById(id);
        return systemDirection.map( value -> new ResponseEntity<>(new Result("OK", "", systemDirection), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody SystemDirection systemDirection) {
        SystemDirection m = systemDirectionService.save(systemDirection);
        return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody SystemDirection systemDirection) {
        SystemDirection m = systemDirectionService.update(id, systemDirection);
        return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<SystemDirection> systemDirection = systemDirectionService.findById(id);

        if (systemDirection.isPresent()) {
            systemDirectionService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
