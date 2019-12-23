package at.chatty.api;

import at.chatty.controller.UserController;
import at.chatty.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    private UserController userController;


    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        String result = this.userController.createUser(user);
        switch (result) {
            case "success":
                return new ResponseEntity<>("success", HttpStatus.OK);
            case "already-in-use":
                return new ResponseEntity<>("already-in-use", HttpStatus.FORBIDDEN);
            default:
                return new ResponseEntity<>("something-went-wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Iterable<User> findAllUser() {
        return this.userController.findAllUser();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable int id) {
        return this.userController.findUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity changeIsActive(@PathVariable int id) {
        boolean success = this.userController.changeIsActive(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity deleteAllUser() {
        boolean success = this.userController.deleteAllUser();
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable int id) {
        boolean success = this.userController.deleteUserById(id);
        if (success) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
