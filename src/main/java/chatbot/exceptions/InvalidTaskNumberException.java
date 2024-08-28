package main.java.chatbot.exceptions;

public class InvalidTaskNumberException extends ChatsyException {
    @Override
    public String toString() {
        return "The task number provided is invalid.";
    }
}
