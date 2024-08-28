package main.java.chatbot.exceptions;

public class InvalidTaskStringException extends ChatsyException {
    @Override
    public String toString() {
        return "The task data is in an invalid format.";
    }
}
