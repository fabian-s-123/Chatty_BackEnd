package at.chatty.controller;

import at.chatty.entity.Message;
import at.chatty.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    public boolean createMessage(Message message) {
        this.messageRepository.save(message);
        return true;
    }

    public Iterable<Message> findAllMessages() {
        return this.messageRepository.findAll();
    }

    public Optional<Message> findMessageById(int id) {
        return this.messageRepository.findById(id);
    }

    public boolean deleteAllMessages() {
        this.messageRepository.deleteAll();
        return true;
    }

    public boolean deleteMessageById(int id) {
        Optional<Message> currentMessage = this.messageRepository.findById(id);
        if (currentMessage.isPresent()) {
            this.messageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
