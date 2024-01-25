package okazy.controller.voiture;

import okazy.model.voiture.Voiture;
import okazy.result.Result;
import okazy.service.voiture.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        List<Voiture> voitures = voitureService.findAll();
        return new ResponseEntity<>(new Result("OK", "", voitures), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getVoitureById(@PathVariable int id) {
        Optional<Voiture> voiture = voitureService.findById(id);
        return voiture.map(value -> new ResponseEntity<>(new Result("OK", "", voiture), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Result("Not Found", "", null), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> createVoiture(@RequestBody Voiture voiture) {
        Voiture createdVoiture = voitureService.insert(voiture);
        return new ResponseEntity<>(new Result("Created", "", createdVoiture), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> updateVoiture(@PathVariable int id, @RequestBody Voiture voiture) {
        Optional<Voiture> existingVoiture = voitureService.findById(id);
        if (existingVoiture.isPresent()) {
            Voiture updatedVoiture = voitureService.update(id, voiture);
            return new ResponseEntity<>(new Result("Updated", "", updatedVoiture), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Result("Not Found", "", null), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> deleteVoiture(@PathVariable int id) {
        Optional<Voiture> existingVoiture = voitureService.findById(id);
        if (existingVoiture.isPresent()) {
            voitureService.delete(id);
            return new ResponseEntity<>(new Result("Deleted", "", null), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Result("Not Found", "", null), HttpStatus.NOT_FOUND);
        }
    }
}
