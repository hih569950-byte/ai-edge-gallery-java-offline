package com.example.aiedgegallery;

public class ChatMessage {
    private final String sender;
    private final String text;
    private final boolean isUser;

    public ChatMessage(String sender, String text, boolean isUser) {
        this.sender = sender;
        this.text = text;
        this.isUser = isUser;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }
}