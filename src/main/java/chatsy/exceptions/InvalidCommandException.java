package chatsy.exceptions;

public class InvalidCommandException extends ChatsyException {
    @Override
    public String toString() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
