package at.chatty.controller;

import at.chatty.entity.User;
import at.chatty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * The userName can only be used once, irregardless of this user currently being active oer not.
     *
     * @param user
     * @return
     */
    public boolean createUser(User user) {
        Optional<User> currentUser = this.userRepository.findByUserName(user.getUserName());
        if (currentUser.isPresent()) {
            if (currentUser.get().getIsActive()) {
                return false;
            } this.changeIsActive(currentUser.get().getId());
            return true;
        } else {
            this.userRepository.save(user);
            return true;
        }
    }

    public int findByUserName(String userName) {
        Optional<User> currentUser = this.userRepository.findByUserName(userName);
        return currentUser.get().getId();
    }

    public Iterable<User> findAllUser() {
        return this.userRepository.findAll();
    }

    public Optional<User> findUserById(int id) {
        return this.userRepository.findById(id);
    }

    public boolean changeIsActive(int id) {
        try {
            User currentUser = (this.userRepository.findById(id).get());
            currentUser.setIsActive(!currentUser.getIsActive());
            this.userRepository.saveAndFlush(currentUser);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteAllUser() {
        this.userRepository.deleteAll();
        return true;
    }

    public boolean deleteUserById(int id) {
        Optional<User> currentUser = this.userRepository.findById(id);
        if (currentUser.isPresent()) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
