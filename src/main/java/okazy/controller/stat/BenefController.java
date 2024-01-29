package okazy.controller.stat;

import okazy.model.stat.Benef;
import okazy.result.Result;
import okazy.service.stat.BenefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/benefs")
public class BenefController {

    public final BenefService benefService;

    @Autowired
    public BenefController(BenefService benefService) {
        this.benefService = benefService;
    }

    @GetMapping
    public ResponseEntity<Result> getAll() {
        try {
            List<Benef> benefs = this.benefService.findAll();
            return new ResponseEntity<>(new Result("OK", "", benefs), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new Result("An Error Occured", e.getMessage(), ""), HttpStatus.BAD_REQUEST);
        }
    }
}
