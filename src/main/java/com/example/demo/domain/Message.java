package com.example.demo.domain;

public class Message {
    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }
    private String sender;
    private String content;
    private MessageType messageType;

    @Override
    public String toString() {

        return "Message{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", messageType=" + messageType +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
