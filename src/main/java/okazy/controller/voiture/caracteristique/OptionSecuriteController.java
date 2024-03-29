package okazy.controller.voiture.caracteristique;
;
import okazy.model.voiture.caracteristique.OptionSecurite;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.OptionSecuriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/optionsecurites")
public class OptionSecuriteController {

    private final OptionSecuriteService optionSecuriteService;

    @Autowired
    public OptionSecuriteController(OptionSecuriteService optionSecuriteService) {
        this.optionSecuriteService = optionSecuriteService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        try {
            List<OptionSecurite> optionSecurites = optionSecuriteService.findAll();
            return new ResponseEntity<>(new Result("OK", "", optionSecurites), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        try {
            Optional<OptionSecurite> optionSecurite = optionSecuriteService.findById(id);
            return optionSecurite.map( value -> new ResponseEntity<>(new Result("OK", "", optionSecurite), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));

        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody OptionSecurite optionSecurite) {
        try {
            OptionSecurite m = optionSecuriteService.save(optionSecurite);
            return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody OptionSecurite optionSecurite) {
        try {
            OptionSecurite m = optionSecuriteService.update(id, optionSecurite);
            return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {

        try {
            Optional<OptionSecurite> model = optionSecuriteService.findById(id);

            if (model.isPresent()) {
                optionSecuriteService.delete(id);
                return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

}
