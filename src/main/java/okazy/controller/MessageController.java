package okazy.controller;

import okazy.model.Message;
import okazy.model.user.Utilisateur;
import okazy.repository.MessageRepository;
import okazy.result.Result;
import okazy.service.user.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final UtilisateurService utilisateurService;

    @Autowired
    public MessageController(MessageRepository messageRepository, UtilisateurService utilisateurService) {
        this.messageRepository = messageRepository;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/{receiver}")
    public ResponseEntity<Result> getAllMessage(@PathVariable String receiver) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateur = this.utilisateurService.findByUserName(userDetails.getUsername());

        if (utilisateur.isPresent()) {
            Utilisateur user = utilisateur.get();
            List<Message> messages = this.messageRepository.findBySenderIdAndReceiverIdOrderByTimestampAsc(String.valueOf(user.getId()), receiver);
            return new ResponseEntity<>(new Result("OK","", messages), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result("NOT FOUND", "Authenticated user not found", ""), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Result> save(@RequestBody Message message) {
        Message m = this.messageRepository.save(message);
        return new ResponseEntity<>(new Result("SAVED", "", m), HttpStatus.OK);
    }
}
