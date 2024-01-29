package okazy.controller.voiture.caracteristique;

import okazy.model.voiture.caracteristique.Cassis;
import okazy.result.Result;
import okazy.service.voiture.caracteristique.CassisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cassiss")
public class CassisController {

    private final CassisService cassisService;

    @Autowired
    public CassisController(CassisService cassisService) {
        this.cassisService = cassisService;
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        List<Cassis> casses = cassisService.findAll();
        return new ResponseEntity<>(new Result("OK", "", casses), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable int id) {
        Optional<Cassis> cassis = cassisService.findById(id);
        return cassis.map( value -> new ResponseEntity<>(new Result("OK", "", cassis), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@RequestBody Cassis cassis) {
        try{
            Cassis c = cassisService.save(cassis);
            return new ResponseEntity<>(new Result("CREATED", "", c), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> save(@PathVariable int id, @RequestBody Cassis cassis) {
        try{
            Cassis c = cassisService.update(id, cassis);
            return new ResponseEntity<>(new Result("UPDATED", "", c), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> delete(@PathVariable int id) {
        Optional<Cassis> cassis = cassisService.findById(id);

        if (cassis.isPresent()) {
            cassisService.delete(id);
            return new ResponseEntity<>(new Result("DELETED", "", ""), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Result("NOT FOUND", "", ""), HttpStatus.NOT_FOUND);
    }

}
