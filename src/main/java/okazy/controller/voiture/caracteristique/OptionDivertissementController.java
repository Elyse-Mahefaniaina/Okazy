package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.OptionDivertissement;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.OptionDivertissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/optiondivertissements")
public class OptionDivertissementController {

    private final OptionDivertissementService optionDivertissementService;

    @Autowired
    public OptionDivertissementController(OptionDivertissementService optionDivertissementService) {
        this.optionDivertissementService = optionDivertissementService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<OptionDivertissement> optionDivertissements = optionDivertissementService.findAll();
        return new ResponseEntity<>(new Result("OK", "", optionDivertissements), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<OptionDivertissement> optionDivertissement = optionDivertissementService.findById(id);
        return optionDivertissement.map( value -> new ResponseEntity<>(new Result("OK", "", optionDivertissement), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody OptionDivertissement optionDivertissement) {
        OptionDivertissement m = optionDivertissementService.save(optionDivertissement);
        return new ResponseEntity<>(new Result("CREATED", "", m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody OptionDivertissement optionDivertissement) {
        OptionDivertissement m = optionDivertissementService.update(id, optionDivertissement);
        return new ResponseEntity<>(new Result("UPDATED", "", m), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<OptionDivertissement> model = optionDivertissementService.findById(id);

        if (model.isPresent()) {
            optionDivertissementService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
