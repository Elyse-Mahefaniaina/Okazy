package okazy.controller.stat;

import okazy.model.stat.VenteAVG;
import okazy.result.Result;
import okazy.service.stat.VenteAVGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venteavgs")
public class VenteAVGController {

    private final VenteAVGService venteAVGService;

    @Autowired
    public VenteAVGController(VenteAVGService venteAVGService) {
        this.venteAVGService = venteAVGService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Result> findAll() {
        try {
            List<VenteAVG> venteAVGS = this.venteAVGService.findAll();
            return new ResponseEntity<>(new Result("OK", "", venteAVGS), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
