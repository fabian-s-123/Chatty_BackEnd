package at.chatty.api;

import at.chatty.controller.MessageController;
import at.chatty.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/messages")
public class MessageApi {

    @Autowired
    private MessageController messageController;


    @PostMapping
    public ResponseEntity createMessage(@RequestBody Message message) {
        try {
            boolean success = this.messageController.createMessage(message);
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public Iterable<Message> findAllMessages() {
        return this.messageController.findAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return this.messageController.findMessageById(id);
    }

    @DeleteMapping
    public ResponseEntity deleteAllMessages() {
        boolean success = this.messageController.deleteAllMessages();
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessageById(@PathVariable int id) {
        boolean success = this.messageController.deleteMessageById(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
