package main.java.chatbot.exceptions;

public class InvalidTaskIndexException extends ChatsyException {
    @Override
    public String toString() {
        return "Oops, invalid index provided.\n";
    }
}
