package okazy.controller.voiture;

import okazy.model.voiture.Voiture;
import okazy.result.Result;
import okazy.service.voiture.VoitureService;
import okazy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voitures")
public class VoitureController {
    private final VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    public ResponseEntity<Result> getAllVoitures() {
        try {
            List<Voiture> voitures = voitureService.findAll();
            return new ResponseEntity<>(new Result("OK", "", voitures), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/advenced")
    public ResponseEntity<Result> getAllVoituresAdvenced(@RequestParam(required = false) String marque,
                                                         @RequestParam(required = false) String model,
                                                         @RequestParam(required = false) Double puissancefiscale,
                                                         @RequestParam(required = false) Double cylindre,
                                                         @RequestParam(required = false) Double puissancemoteur,
                                                         @RequestParam(required = false) String cassis,
                                                         @RequestParam(required = false) Integer nombreporte,
                                                         @RequestParam(required = false) Integer nombreplace,
                                                         @RequestParam(required = false) String miseencirculation) {
        try {
            List<Voiture> voitures = voitureService.findAdvenced(marque, model, puissancefiscale, cylindre, puissancemoteur, cassis, nombreporte, nombreplace, Util.parseToDate(miseencirculation));
            return new ResponseEntity<>(new Result("OK", "", voitures), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("Failed", e.getMessage(), ""), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getVoitureById(@PathVariable int id) {
        try {
            Optional<Voiture> voiture = voitureService.findById(id);
            return voiture.map(value -> new ResponseEntity<>(new Result("OK", "", voiture), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND));
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> createVoiture(@RequestBody Voiture voiture) {
        try {
            Voiture createdVoiture = voitureService.insert(voiture);
            return new ResponseEntity<>(new Result("Created", "", createdVoiture), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> updateVoiture(@PathVariable int id, @RequestBody Voiture voiture) {
        try {
            Optional<Voiture> existingVoiture = voitureService.findById(id);
            if (existingVoiture.isPresent()) {
                Voiture updatedVoiture = voitureService.update(id, voiture);
                return new ResponseEntity<>(new Result("Updated", "", updatedVoiture), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> deleteVoiture(@PathVariable int id) {
        try {
            Optional<Voiture> existingVoiture = voitureService.findById(id);
            if (existingVoiture.isPresent()) {
                voitureService.delete(id);
                return new ResponseEntity<>(new Result("Deleted", "", ""), HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(new Result("Not Found", "", ""), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
