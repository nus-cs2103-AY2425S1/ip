package main.java.chatbot.exceptions;

public class EmptyDescriptionException extends ChatsyException {
    @Override
    public String toString() {
        return "The description cannot be empty.";
    }
}

