package at.chatty.dto;

import at.chatty.entity.Message;

import java.time.LocalDateTime;

public class MessageDTO {

    private int id;
    private String content;
    private LocalDateTime postedOn;
    private String userName;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.postedOn = message.getPostedOn();
        this.userName = message.getUser().getUserName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
