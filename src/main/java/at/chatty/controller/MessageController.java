package at.chatty.controller;

import at.chatty.dto.MessageDTO;
import at.chatty.entity.Message;
import at.chatty.repository.MessageRepository;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    public String createMessage(Message message) {
        if (message.getContent().length() > 500) {
            return "lenght";
        } else if (!(this.messageRepository.save(message) == null)) {
            return "success";
        }
        return "failed";
    }

    public Iterable<MessageDTO> findAllMessages() {
        ArrayList<MessageDTO> msgDTOs = new ArrayList<>();
        if (this.messageRepository.findAll().size() > 0) {
            Iterable<Message> msg = this.messageRepository.findAll();
            for (Message message : msg) {
                MessageDTO dto = new MessageDTO(message);
                msgDTOs.add(dto);
            }
        }
        return msgDTOs;
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
