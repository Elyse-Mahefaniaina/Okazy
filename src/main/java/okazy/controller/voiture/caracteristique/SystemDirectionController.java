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
        try {
            List<SystemDirection> systemDirections = systemDirectionService.findAll();
            return new ResponseEntity<>(new Result("OK", "", systemDirections), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        try {
            Optional<SystemDirection> systemDirection = systemDirectionService.findById(id);
            return systemDirection.map( value -> new ResponseEntity<>(new Result("OK", "", systemDirection), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody SystemDirection systemDirection) {
        try {
            SystemDirection m = systemDirectionService.save(systemDirection);
            return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody SystemDirection systemDirection) {
        try {
            SystemDirection m = systemDirectionService.update(id, systemDirection);
            return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        try {
            Optional<SystemDirection> systemDirection = systemDirectionService.findById(id);

            if (systemDirection.isPresent()) {
                systemDirectionService.delete(id);
                return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

}
